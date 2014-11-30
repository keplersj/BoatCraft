package boatcraft.core.blocks.tileentites

import java.util.ArrayList
import scala.reflect.ClassTag
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{IInventory, ISidedInventory}
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ChatComponentText

class TileItemLoader extends TileDockAddon with ISidedInventory
{
	override def getName = getClass.getSimpleName
	
	override def getInventoryStackLimit =
		chooseInventoryOrElse(
				_.getInventoryStackLimit,
				0)
	
	override def getSizeInventory =
		chooseInventoryOrElse(
				_.getSizeInventory,
				0)
	
	override def getStackInSlot(slot: Int): ItemStack =
		chooseInventoryOrElse(
				_.getStackInSlot(slot),
				null)
	
	override def getStackInSlotOnClosing(slot: Int): ItemStack =
		chooseInventoryOrElse(
				_.getStackInSlotOnClosing(slot),
				null)
	
	override def hasCustomName = false
	
	override def isItemValidForSlot(slot: Int, stack: ItemStack) =
		chooseInventoryOrElse(
				_.isItemValidForSlot(slot, stack),
				false)
	
	override def isUseableByPlayer(player: EntityPlayer) = false
	
	override def openInventory(player: EntityPlayer)
		= doIfInventory(_.openInventory(player))
	
	override def closeInventory(player: EntityPlayer)
		= doIfInventory(_.closeInventory(player))
	
	override def setInventorySlotContents(slot: Int, stack: ItemStack) =
		doIfInventory(_.setInventorySlotContents(slot, stack))
	
	override def decrStackSize(slot: Int, amount: Int): ItemStack =
		chooseInventoryOrElse(
				_.decrStackSize(slot, amount),
				null)
	
	override def canExtractItem(slot: Int, stack: ItemStack, side: EnumFacing) = ???
	
	//TODO Check
	override def canInsertItem(slot: Int, stack: ItemStack, side: EnumFacing) =
		chooseRegularOrSided(
				_.canInsertItem(slot, stack, side),
				_.isItemValidForSlot(slot, stack),
				false)
	
	//TODO Check
	override def getSlotsForFace(side: EnumFacing) =
		chooseRegularOrSided(
				_.getSlotsForFace(side),
				(inventory: IInventory) => {
					var list = new ArrayList[Int]
					for (slot <- 0 until inventory.getSizeInventory) list.add(slot)
					list.toArray.asInstanceOf[Array[Int]]
					},
				Array[Int]())
	
	override def clear() = doIfInventory(_.clear())
	
	override def getFieldCount = 0
	override def getField(field: Int) = 0
	override def setField(field: Int, value: Int) {}
	
	override def getDisplayName = new ChatComponentText("Dock Item Loader")
	
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