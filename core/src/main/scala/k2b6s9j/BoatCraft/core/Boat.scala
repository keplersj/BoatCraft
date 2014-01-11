package k2b6s9j.BoatCraft.core

import net.minecraft.item.ItemBoat
import net.minecraft.util._
import net.minecraft.entity.item.EntityItem
import net.minecraft.inventory.IInventory
import java.lang.String
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraftforge.client.IItemRenderer
import net.minecraft.client.renderer.entity.RenderBoat
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Boat {

  class ItemCustomBoat extends ItemBoat

  case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double) extends EntityBoat(world, x, y, z)

  abstract class EntityBoatContainer(par1World: World) extends EntityCustomBoat(par1World) with IInventory {

    private var boatContainerItems: Array[ItemStack] = new Array[ItemStack](36)

    private var dropContentsWhenDead: Boolean = true

    def getStackInSlot(par1: Int): ItemStack = this.boatContainerItems(par1)

    def decrStackSize(par1: Int, par2: Int): ItemStack = {
      if (this.boatContainerItems(par1) != null) {
        var itemstack: ItemStack = null
        if (this.boatContainerItems(par1).stackSize <= par2) {
          itemstack = this.boatContainerItems(par1)
          this.boatContainerItems(par1) = null
          itemstack
        } else {
          itemstack = this.boatContainerItems(par1).splitStack(par2)
          if (this.boatContainerItems(par1).stackSize == 0) {
            this.boatContainerItems(par1) = null
          }
          itemstack
        }
      } else {
        null
      }
    }

    def getStackInSlotOnClosing(par1: Int): ItemStack = {
      if (this.boatContainerItems(par1) != null) {
        val itemstack = this.boatContainerItems(par1)
        this.boatContainerItems(par1) = null
        itemstack
      } else {
        null
      }
    }

    def setInventorySlotContents(par1: Int, par2ItemStack: ItemStack) {
      this.boatContainerItems(par1) = par2ItemStack
      if (par2ItemStack != null &&
        par2ItemStack.stackSize > this.getInventoryStackLimit) {
        par2ItemStack.stackSize = this.getInventoryStackLimit
      }
    }

    def onInventoryChanged() {
    }

    def isUseableByPlayer(par1EntityPlayer: EntityPlayer): Boolean = {
      if (this.isDead) false else par1EntityPlayer.getDistanceSqToEntity(this) <= 64.0D
    }

    def openChest() {
    }

    def closeChest() {
    }

    def isItemValidForSlot(par1: Int, par2ItemStack: ItemStack): Boolean = true

    def getInvName(): String = "container.boat"

    def getInventoryStackLimit(): Int = 64

    override def travelToDimension(par1: Int) {
      this.dropContentsWhenDead = false
      super.travelToDimension(par1)
    }

    override protected def writeEntityToNBT(par1NBTTagCompound: NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound)
      val nbttaglist = new NBTTagList()
      for (i <- 0 until this.boatContainerItems.length if this.boatContainerItems(i) != null) {
        val nbttagcompound1 = new NBTTagCompound()
        nbttagcompound1.setByte("Slot", i.toByte)
        this.boatContainerItems(i).writeToNBT(nbttagcompound1)
        nbttaglist.appendTag(nbttagcompound1)
      }
      par1NBTTagCompound.setTag("Items", nbttaglist)
    }

    override protected def readEntityFromNBT(par1NBTTagCompound: NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound)
      val nbttaglist = par1NBTTagCompound.getTagList("Items")
      this.boatContainerItems = Array.ofDim[ItemStack](this.getSizeInventory)
      for (i <- 0 until nbttaglist.tagCount()) {
        val nbttagcompound1 = nbttaglist.tagAt(i).asInstanceOf[NBTTagCompound]
        val j = nbttagcompound1.getByte("Slot") & 255
        if (j >= 0 && j < this.boatContainerItems.length) {
          this.boatContainerItems(j) = ItemStack.loadItemStackFromNBT(nbttagcompound1)
        }
      }
    }

    override def interactFirst(player: EntityPlayer): Boolean = {
      if (player.isSneaking) {
        return false
      }
      if (!this.worldObj.isRemote) {
        player.displayGUIChest(this)
      }
      true
    }

    def dropContents() {
      for (i <- 0 until this.getSizeInventory) {
        val itemstack = this.getStackInSlot(i)
        if (itemstack != null) {
          val f = this.rand.nextFloat() * 0.8F + 0.1F
          val f1 = this.rand.nextFloat() * 0.8F + 0.1F
          val f2 = this.rand.nextFloat() * 0.8F + 0.1F
          while (itemstack.stackSize > 0) {
            var j = this.rand.nextInt(21) + 10
            if (j > itemstack.stackSize) {
              j = itemstack.stackSize
            }
            itemstack.stackSize -= j
            val entityitem = new EntityItem(this.worldObj, this.posX + f.toDouble, this.posY + f1.toDouble,
              this.posZ + f2.toDouble, new ItemStack(itemstack.itemID, j, itemstack.getItemDamage))
            val f3 = 0.05F
            entityitem.motionX = (this.rand.nextGaussian().toFloat * f3).toDouble
            entityitem.motionY = (this.rand.nextGaussian().toFloat * f3 + 0.2F).toDouble
            entityitem.motionZ = (this.rand.nextGaussian().toFloat * f3).toDouble
            this.worldObj.spawnEntityInWorld(entityitem)
          }
        }
      }
    }

    override def crashedDrops() {
      dropContents()
      var k: Int = 0
      k = 0
      while (k < 3) {
        if (isCustomBoat) {
          this.entityDropItem(customPlank(), 0.0F)
        } else {
          this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F)
        }
        k
      }
      k = 0
      while (k < 2) {
        if (isCustomBoat) {
          this.entityDropItem(customStick(), 0.0F)
        } else {
          this.dropItemWithOffset(Item.stick.itemID, 1, 0.0F)
        }
        k
      }
      if (doesBoatContainBlock()) {
        this.entityDropItem(blockInBoat(), 0.0F)
      }
    }
  }

  class RenderCustomBoat extends RenderBoat with IItemRenderer {

    def doRender(p1: Entity, p2: Double, p3: Double, p4: Double, p5: Float, p6: Float): Unit = {

    }

    def getEntityTexture(p1: Entity): ResourceLocation = new ResourceLocation("textures/entity/boat.png")

    def handleRenderType(p1: ItemStack, p2: ItemRenderType): Boolean = true

    def shouldUseRenderHelper(p1: ItemRenderType, p2: ItemStack, p3: ItemRendererHelper): Boolean = true

    def renderItem(p1: ItemRenderType, p2: ItemStack, p3: Array[AnyRef]): Unit = {

    }
  }

}