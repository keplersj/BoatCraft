package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import k2b6s9j.boatcraft.core.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

class Chest extends Modifier
{
	override def getBlock: Block = Blocks.chest
	override def getMeta: Int = 0

	override def getInventory: IInventory = new Inventory
	
	override def getName: String = "Chest"
	override def getContent: ItemStack = new ItemStack(Blocks.chest)

	override def openGUI(player: EntityPlayer, boat: EntityBoatContainer) =
		//player openGui(Vanilla, 0, player.worldObj, 0, 0, 0)
		player displayGUIChest (boat)

	private class Inventory extends IInventory
	{
		private var inv: Array[ItemStack] = new Array[ItemStack](36)
		
		override def getSizeInventory: Int =
			27
		
		override def getStackInSlot(slot: Int): ItemStack =
			inv(slot)
		
		override def decrStackSize(slot: Int, count: Int): ItemStack =
		{
			if (inv(slot) != null)
			{
				var itemstack: ItemStack = null
				
				if (inv(slot).stackSize <= count)
				{
					itemstack = inv(slot)
					inv(slot) = null
					markDirty
					itemstack
				}
				else
				{
					itemstack = inv(slot).splitStack(count);
					
					if (inv(slot).stackSize == 0)
						inv(slot) = null
					
					markDirty
					itemstack
				}
			}
			else null
		}
		
		override def getStackInSlotOnClosing(slot: Int): ItemStack =
		{
			if (inv(slot) != null)
			{
				val itemstack = inv(slot)
				inv(slot) = null
				itemstack
			}
			else null
		}
		
		override def setInventorySlotContents(slot: Int, stack: ItemStack)
		{
			inv(slot) = stack
			
			if (stack != null && stack.stackSize > getInventoryStackLimit)
				stack.stackSize = getInventoryStackLimit
				
			markDirty
		}
		
		override def getInventoryName: String = "Chest Boat"
		
		override def hasCustomInventoryName: Boolean = false
		
		override def getInventoryStackLimit: Int = 64
		
		override def markDirty {}
		
		override def isUseableByPlayer(player: EntityPlayer): Boolean =
			true
		
		override def openInventory {}
		
		override def closeInventory {}
		
		override def isItemValidForSlot(slot: Int, stack: ItemStack): Boolean =
			true
	}
}