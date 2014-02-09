package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.core.traits.Modifier
import net.minecraft.item.ItemStack
import net.minecraft.inventory.IInventory
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.entity.player.EntityPlayer

trait Chest extends Modifier
{
	override def getBlock: Block = Blocks.chest
	override def getMeta: Int = 0
	override def getInventory: IInventory = new IInventory
  {
    var containedItems: Array[ItemStack] = new Array[ItemStack](36)

    override def func_145825_b(): String = "Boat"
    override def func_145818_k_(): Boolean = true

    override def getSizeInventory: Int = 27
    override def getStackInSlot(i: Int) : ItemStack = containedItems(i)
    override def decrStackSize(par1: Int, par2: Int): ItemStack =
    {
      if (this.containedItems(par1) != null)
      {
        var itemstack: ItemStack = null

        if (this.containedItems(par1).stackSize <= par2)
        {
          itemstack = this.containedItems(par1)
          this.containedItems(par1) = null
          itemstack
        }
        else
        {
          itemstack = this.containedItems(par1).splitStack(par2)

          if (this.containedItems(par1).stackSize == 0)
          {
            this.containedItems(par1) = null
          }

          itemstack
        }
      }
      else
      {
        null
      }
    }
    override def getInventoryStackLimit: Int = 64
    override def getStackInSlotOnClosing(i: Int): ItemStack =
    {
      if (this.containedItems(i) != null)
      {
        var itemstack: ItemStack = this.containedItems(i)
        this.containedItems(i) = null
        itemstack
      }
      else
      {
        null
      }
    }

    override def isItemValidForSlot(i: Int, stack: ItemStack): Boolean = true
    override def isUseableByPlayer(player: EntityPlayer): Boolean = true

    override def openChest: Unit = null
    override def closeChest: Unit = null
    override def onInventoryChanged: Unit = null
    override def setInventorySlotContents(i: Int, stack: ItemStack): Unit =
    {
      this.containedItems(i) = stack

      if (stack != null && stack.stackSize > this.getInventoryStackLimit())
      {
        stack.stackSize = this.getInventoryStackLimit()
      }
    }
  }
	override def getName: String = "Chest"
	override def getContent: ItemStack = new ItemStack(Blocks.chest)
	override def openGUI(player: EntityPlayer) =
		player displayGUIChest getInventory
}