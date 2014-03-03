package k2b6s9j.boatcraft.core.utilities

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList

//TODO: Fill Documentation
/**
  *
  */
object NBTHelper
{
	//TODO: Fill Documentation
	/**
	  *
	  * @param inv
	  * @param tag
	  */
	def writeChestToNBT(inv: IInventory, tag: NBTTagCompound)
	{
		var list = new NBTTagList
		for (i <- 0 until inv.getSizeInventory) {
			if (inv.getStackInSlot(i) != null) {
				var _tag = new NBTTagCompound
				(inv getStackInSlot i) writeToNBT _tag
				_tag setByte ("Slot", i.toByte)
				list appendTag _tag
			}
		}
		tag.setTag("Items", list)
	}

	//TODO: Fill Documentation
	/**
	  *
	  * @param inv
	  * @param tag
	  */
	def readChestFromNBT(inv: IInventory, tag: NBTTagCompound)
	{
		var list = tag getTagList ("Items", Constants.NBT.TAG_COMPOUND)

		for (i <- 0 until list.tagCount) {
			val _tag = list getCompoundTagAt i
			inv setInventorySlotContents (_tag getByte "Slot", ItemStack loadItemStackFromNBT _tag)
		}
	}
}