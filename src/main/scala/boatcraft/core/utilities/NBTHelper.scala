package boatcraft.core.utilities

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants

object NBTHelper {
  def writeChestToNBT(inv: IInventory, tag: NBTTagCompound) {
    val list = new NBTTagList
    for (i <- 0 until inv.getSizeInventory) {
      if (inv.getStackInSlot(i) != null) {
        val _tag = new NBTTagCompound
        (inv getStackInSlot i) writeToNBT _tag
        _tag setByte("Slot", i.toByte)
        list appendTag _tag
      }
    }
    tag.setTag("Items", list)
  }

  def readChestFromNBT(inv: IInventory, tag: NBTTagCompound) {
    val list = tag getTagList("Items", Constants.NBT.TAG_COMPOUND)

    for (i <- 0 until list.tagCount) {
      val _tag = list getCompoundTagAt i
      inv setInventorySlotContents(_tag getByte "Slot", ItemStack loadItemStackFromNBT _tag)
    }
  }
}