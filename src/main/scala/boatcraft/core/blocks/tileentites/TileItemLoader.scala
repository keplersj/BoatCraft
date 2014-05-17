package boatcraft.core.blocks.tileentites

import java.util.ArrayList

import scala.reflect.ClassTag

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{IInventory, ISidedInventory}
import net.minecraft.item.ItemStack

class TileItemLoader extends TileDockAddon with ISidedInventory {
	
	def getInventoryName = getClass.getSimpleName
	
	def getInventoryStackLimit =
		chooseInventoryOrElse(
				(inventory: IInventory) => inventory.getInventoryStackLimit,
				0)
	
	def getSizeInventory =
		chooseInventoryOrElse(
				(inventory: IInventory) => inventory.getSizeInventory,
				0)
	
	def getStackInSlot(slot: Int): ItemStack =
		chooseInventoryOrElse(
				(inventory: IInventory) => inventory.getStackInSlot(slot),
				null)
	
	def getStackInSlotOnClosing(slot: Int): ItemStack =
		chooseInventoryOrElse(
				(inventory: IInventory) => inventory.getStackInSlotOnClosing(slot),
				null)
	
	def hasCustomInventoryName = false
	
	def isItemValidForSlot(slot: Int, stack: ItemStack) =
		chooseInventoryOrElse(
				(inventory: IInventory) => inventory.isItemValidForSlot(slot, stack),
				false)
	
	def isUseableByPlayer(player: EntityPlayer) = false
	
	def openInventory = doIfInventory((inventory: IInventory) => inventory.openInventory)
	
	def closeInventory = doIfInventory((inventory: IInventory) => inventory.closeInventory)
	
	def setInventorySlotContents(slot: Int, stack: ItemStack) =
		doIfInventory((inventory: IInventory) => inventory.setInventorySlotContents(slot, stack))
	
	def decrStackSize(slot: Int, amount: Int): ItemStack =
		chooseInventoryOrElse(
				(inventory: IInventory) => inventory.decrStackSize(slot, amount),
				null)
	
	def canExtractItem(slot: Int, stack: ItemStack, side: Int) = ???
	
	//TODO Check
	def canInsertItem(slot: Int, stack: ItemStack, side: Int) =
		chooseRegularOrSided(
				(inventory: ISidedInventory) => inventory.canInsertItem(slot, stack, side),
				(inventory: IInventory) => inventory.isItemValidForSlot(slot, stack),
				false)
	
	//TODO Check
	def getAccessibleSlotsFromSide(side: Int) =
		chooseRegularOrSided(
				(inventory: ISidedInventory) => inventory.getAccessibleSlotsFromSide(side),
				(inventory: IInventory) => {
					var list = new ArrayList[Int]
					for (slot <- 0 until inventory.getSizeInventory) list.add(slot)
					list.toArray.asInstanceOf[Array[Int]]
					},
				Array[Int]())
	
	private def doIfInventory(f: IInventory => Unit) =
		parent.docked.getBlockData match
		{
			case inventory: IInventory => f(inventory)
		}
	
	private def chooseInventoryOrElse[T](yes: IInventory => T, no: T): T =
		parent.docked.getBlockData match
		{
			case inventory: IInventory => yes(inventory)
			case _ => no
		}
	
	private def chooseRegularOrSided[T](sided: ISidedInventory => T, regular: IInventory => T, no: T): T =
		parent.docked.getBlockData match
		{
			case inventory: ISidedInventory => sided(inventory)
			case inventory: IInventory => regular(inventory)
			case _ => no
		}
}