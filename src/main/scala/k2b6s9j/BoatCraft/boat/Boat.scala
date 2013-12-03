package k2b6s9j.BoatCraft.boat

import net.minecraft.item.ItemBoat
import net.minecraft.util._
import net.minecraft.entity.item.EntityItem
import net.minecraft.inventory.IInventory
import java.lang.String
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraftforge.client.IItemRenderer
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.model.{ModelBase, ModelBoat}
import net.minecraft.client.renderer.RenderBlocks
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import org.lwjgl.opengl.GL11
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureMap
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.MathHelper
import net.minecraft.world.World

object Boat {

  class ItemCustomBoat(par1: Int) extends ItemBoat(par1) {

    override def onItemRightClick(par1ItemStack: ItemStack, par2World: World, par3EntityPlayer: EntityPlayer): ItemStack = {
      val f = 1.0F
      val f1 = par3EntityPlayer.prevRotationPitch +
        (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) *
          f
      val f2 = par3EntityPlayer.prevRotationYaw +
        (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) *
          f
      val d0 = par3EntityPlayer.prevPosX +
        (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) *
          f.toDouble
      val d1 = par3EntityPlayer.prevPosY +
        (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) *
          f.toDouble +
        1.62D -
        par3EntityPlayer.yOffset.toDouble
      val d2 = par3EntityPlayer.prevPosZ +
        (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) *
          f.toDouble
      val vec3 = par2World.getWorldVec3Pool.getVecFromPool(d0, d1, d2)
      val f3 = MathHelper.cos(-f2 * 0.017453292F - Math.PI.toFloat)
      val f4 = MathHelper.sin(-f2 * 0.017453292F - Math.PI.toFloat)
      val f5 = -MathHelper.cos(-f1 * 0.017453292F)
      val f6 = MathHelper.sin(-f1 * 0.017453292F)
      val f7 = f4 * f5
      val f8 = f3 * f5
      val d3 = 5.0D
      val vec31 = vec3.addVector(f7.toDouble * d3, f6.toDouble * d3, f8.toDouble * d3)
      val movingobjectposition = par2World.clip(vec3, vec31, true)
      if (movingobjectposition == null) {
        par1ItemStack
      } else {
        val vec32 = par3EntityPlayer.getLook(f)
        var flag = false
        val f9 = 1.0F
        val list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.addCoord(vec32.xCoord * d3,
          vec32.yCoord * d3, vec32.zCoord * d3)
          .expand(f9.toDouble, f9.toDouble, f9.toDouble))
        var i: Int = 0
        i = 0
        while (i < list.size) {
          val entity = list.get(i).asInstanceOf[Entity]
          if (entity.canBeCollidedWith()) {
            val f10 = entity.getCollisionBorderSize
            val axisalignedbb = entity.boundingBox.expand(f10.toDouble, f10.toDouble, f10.toDouble)
            if (axisalignedbb.isVecInside(vec3)) {
              flag = true
            }
          }
          i
        }
        if (flag) {
          par1ItemStack
        } else {
          if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
            i = movingobjectposition.blockX
            val j = movingobjectposition.blockY
            val k = movingobjectposition.blockZ
            if (par2World.getBlockId(i, j, k) == Block.snow.blockID) {
              j
            }
            val entityboat = getEntity(par2World, i, j, k)
            entityboat.rotationYaw = (((MathHelper.floor_double((par3EntityPlayer.rotationYaw * 4.0F / 360.0F).toDouble +
              0.5D) &
              3) -
              1) *
              90).toFloat
            if (!par2World.getCollidingBoundingBoxes(entityboat, entityboat.boundingBox.expand(-0.1D, -0.1D,
              -0.1D))
              .isEmpty) {
              return par1ItemStack
            }
            if (!par2World.isRemote) {
              par2World.spawnEntityInWorld(entityboat)
            }
            if (!par3EntityPlayer.capabilities.isCreativeMode) {
              par1ItemStack.stackSize
            }
          }
          par1ItemStack
        }
      }
    }

    def getEntity(world: World,
                  x: Int,
                  y: Int,
                  z: Int): EntityCustomBoat = {
      val entity = new EntityCustomBoat(world, (x.toFloat + 0.5F).toDouble, (y.toFloat + 1.0F).toDouble, (z.toFloat + 0.5F).toDouble)
      entity
    }
  }

  class EntityCustomBoat(par1World: World) extends EntityBoat(par1World) {

    private var field_70279_a: Boolean = true

    private var speedMultiplier: Double = 0.07D

    private var boatPosRotationIncrements: Int = _

    private var boatX: Double = _

    private var boatY: Double = _

    private var boatZ: Double = _

    private var boatYaw: Double = _

    private var boatPitch: Double = _

    @SideOnly(Side.CLIENT)
    private var velocityX: Double = _

    @SideOnly(Side.CLIENT)
    private var velocityY: Double = _

    @SideOnly(Side.CLIENT)
    private var velocityZ: Double = _

    this.preventEntitySpawning = true

    this.setSize(1.5F, 0.6F)

    this.yOffset = this.height / 2.0F

    def this(par1World: World,
             par2: Double,
             par4: Double,
             par6: Double) {
      this(par1World)
      this.setPosition(par2, par4 + this.yOffset.toDouble, par6)
      this.motionX = 0.0D
      this.motionY = 0.0D
      this.motionZ = 0.0D
      this.prevPosX = par2
      this.prevPosY = par4
      this.prevPosZ = par6
    }

    def isCustomBoat(): Boolean = false

    def customBoatItem(): ItemStack = new ItemStack(Item.boat, 1, 0)

    def useItemID(): Boolean = false

    def customBoatItemID(): Int = Item.boat.itemID

    def customPlank(): ItemStack = new ItemStack(Block.planks, 1, 0)

    def customStick(): ItemStack = new ItemStack(Item.stick, 1, 0)

    def doesBoatContainBlock(): Boolean = false

    def blockInBoat(): ItemStack = null

    def crashedDrops() {
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

    override def onUpdate() {
      super.onUpdate()
      if (this.getTimeSinceHit > 0) {
        this.setTimeSinceHit(this.getTimeSinceHit - 1)
      }
      if (this.getDamageTaken > 0.0F) {
        this.setDamageTaken(this.getDamageTaken - 1.0F)
      }
      this.prevPosX = this.posX
      this.prevPosY = this.posY
      this.prevPosZ = this.posZ
      val b0 = 5
      var d0 = 0.0D
      for (i <- 0 until b0) {
        val d1 = this.boundingBox.minY +
          (this.boundingBox.maxY - this.boundingBox.minY) * (i + 0).toDouble /
            b0.toDouble -
          0.125D
        val d2 = this.boundingBox.minY +
          (this.boundingBox.maxY - this.boundingBox.minY) * (i + 1).toDouble /
            b0.toDouble -
          0.125D
        val axisalignedbb = AxisAlignedBB.getAABBPool.getAABB(this.boundingBox.minX, d1, this.boundingBox.minZ,
          this.boundingBox.maxX, d2, this.boundingBox.maxZ)
        if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water)) {
          d0 += 1.0D / b0.toDouble
        }
      }
      val d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)
      var d4: Double = 0.0
      var d5: Double = 0.0
      if (d3 > 0.26249999999999996D) {
        d4 = Math.cos(this.rotationYaw.toDouble * Math.PI / 180.0D)
        d5 = Math.sin(this.rotationYaw.toDouble * Math.PI / 180.0D)
        for (j <- 0 until 1.0D + d3 * 60.0D) {
          val d6 = (this.rand.nextFloat() * 2.0F - 1.0F).toDouble
          val d7 = (this.rand.nextInt(2) * 2 - 1).toDouble * 0.7D
          var d8: Double = 0.0
          var d9: Double = 0.0
          if (this.rand.nextBoolean()) {
            d8 = this.posX - d4 * d6 * 0.8D + d5 * d7
            d9 = this.posZ - d5 * d6 * 0.8D - d4 * d7
            this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY,
              this.motionZ)
          } else {
            d8 = this.posX + d4 + d5 * d6 * 0.7D
            d9 = this.posZ + d5 - d4 * d6 * 0.7D
            this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY,
              this.motionZ)
          }
        }
      }
      var d10: Double = 0.0
      var d11: Double = 0.0
      if (this.worldObj.isRemote && this.field_70279_a) {
        if (this.boatPosRotationIncrements > 0) {
          d4 = this.posX +
            (this.boatX - this.posX) / this.boatPosRotationIncrements.toDouble
          d5 = this.posY +
            (this.boatY - this.posY) / this.boatPosRotationIncrements.toDouble
          d11 = this.posZ +
            (this.boatZ - this.posZ) / this.boatPosRotationIncrements.toDouble
          d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - this.rotationYaw.toDouble)
          this.rotationYaw = (this.rotationYaw.toDouble + d10 / this.boatPosRotationIncrements.toDouble).toFloat
          this.rotationPitch = (this.rotationPitch.toDouble +
            (this.boatPitch - this.rotationPitch.toDouble) / this.boatPosRotationIncrements.toDouble).toFloat
          this.boatPosRotationIncrements
          this.setPosition(d4, d5, d11)
          this.setRotation(this.rotationYaw, this.rotationPitch)
        } else {
          d4 = this.posX + this.motionX
          d5 = this.posY + this.motionY
          d11 = this.posZ + this.motionZ
          this.setPosition(d4, d5, d11)
          if (this.onGround) {
            this.motionX *= 0.5D
            this.motionY *= 0.5D
            this.motionZ *= 0.5D
          }
          this.motionX *= 0.9900000095367432D
          this.motionY *= 0.949999988079071D
          this.motionZ *= 0.9900000095367432D
        }
      } else {
        if (d0 < 1.0D) {
          d4 = d0 * 2.0D - 1.0D
          this.motionY += 0.03999999910593033D * d4
        } else {
          if (this.motionY < 0.0D) {
            this.motionY /= 2.0D
          }
          this.motionY += 0.007000000216066837D
        }
        if (this.riddenByEntity != null && this.riddenByEntity.isInstanceOf[EntityLivingBase]) {
          d4 = this.riddenByEntity.asInstanceOf[EntityLivingBase].moveForward.toDouble
          if (d4 > 0.0D) {
            d5 = -Math.sin((this.riddenByEntity.rotationYaw * Math.PI.toFloat / 180.0F).toDouble)
            d11 = Math.cos((this.riddenByEntity.rotationYaw * Math.PI.toFloat / 180.0F).toDouble)
            this.motionX += d5 * this.speedMultiplier * 0.05000000074505806D
            this.motionZ += d11 * this.speedMultiplier * 0.05000000074505806D
          }
        }
        d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)
        if (d4 > 0.35D) {
          d5 = 0.35D / d4
          this.motionX *= d5
          this.motionZ *= d5
          d4 = 0.35D
        }
        if (d4 > d3 && this.speedMultiplier < 0.35D) {
          this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D
          if (this.speedMultiplier > 0.35D) {
            this.speedMultiplier = 0.35D
          }
        } else {
          this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D
          if (this.speedMultiplier < 0.07D) {
            this.speedMultiplier = 0.07D
          }
        }
        if (this.onGround) {
          this.motionX *= 0.5D
          this.motionY *= 0.5D
          this.motionZ *= 0.5D
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ)
        if (this.isCollidedHorizontally && d3 > 0.2D) {
          if (!this.worldObj.isRemote && !this.isDead) {
            this.setDead()
            this.crashedDrops()
          }
        } else {
          this.motionX *= 0.9900000095367432D
          this.motionY *= 0.949999988079071D
          this.motionZ *= 0.9900000095367432D
        }
        this.rotationPitch = 0.0F
        d5 = this.rotationYaw.toDouble
        d11 = this.prevPosX - this.posX
        d10 = this.prevPosZ - this.posZ
        if (d11 * d11 + d10 * d10 > 0.001D) {
          d5 = (Math.atan2(d10, d11) * 180.0D / Math.PI).toFloat.toDouble
        }
        var d12 = MathHelper.wrapAngleTo180_double(d5 - this.rotationYaw.toDouble)
        if (d12 > 20.0D) {
          d12 = 20.0D
        }
        if (d12 < -20.0D) {
          d12 = -20.0D
        }
        this.rotationYaw = (this.rotationYaw.toDouble + d12).toFloat
        this.setRotation(this.rotationYaw, this.rotationPitch)
        if (!this.worldObj.isRemote) {
          val list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D,
            0.0D, 0.20000000298023224D))
          var l: Int = 0
          if (list != null && !list.isEmpty) {
            l = 0
            while (l < list.size) {
              val entity = list.get(l).asInstanceOf[Entity]
              if (entity != this.riddenByEntity && entity.canBePushed() &&
                entity.isInstanceOf[EntityCustomBoat]) {
                entity.applyEntityCollision(this)
              }
              l
            }
          }
          l = 0
          while (l < 4) {
            val i1 = MathHelper.floor_double(this.posX + ((l % 2).toDouble - 0.5D) * 0.8D)
            val j1 = MathHelper.floor_double(this.posZ + ((l / 2).toDouble - 0.5D) * 0.8D)
            for (k1 <- 0 until 2) {
              val l1 = MathHelper.floor_double(this.posY) + k1
              val i2 = this.worldObj.getBlockId(i1, l1, j1)
              if (i2 == Block.snow.blockID) {
                this.worldObj.setBlockToAir(i1, l1, j1)
              } else if (i2 == Block.waterlily.blockID) {
                this.worldObj.destroyBlock(i1, l1, j1, true)
              }
            }
            l
          }
          if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
            this.riddenByEntity = null
          }
        }
      }
    }

    override def func_130002_c(player: EntityPlayer): Boolean = {
      if (player.isSneaking) {
        return false
      }
      if (this.riddenByEntity != null && this.riddenByEntity.isInstanceOf[EntityPlayer] &&
        this.riddenByEntity != player) {
        true
      } else {
        if (!this.worldObj.isRemote) {
          player.mountEntity(this)
        }
        true
      }
    }

    def getDisplayTile(): Block = {
      if (!this.hasDisplayTile()) {
        this.getDefaultDisplayTile
      } else {
        val i = this.getDataWatcher.getWatchableObjectInt(20) & 65535
        if (i > 0 && i < Block.blocksList.length) Block.blocksList(i) else null
      }
    }

    def getDefaultDisplayTile(): Block = null

    def getDisplayTileData(): Int = {
      if (!this.hasDisplayTile()) this.getDefaultDisplayTileData else this.getDataWatcher.getWatchableObjectInt(20) >> 16
    }

    def getDefaultDisplayTileData(): Int = 0

    def getDisplayTileOffset(): Int = {
      if (!this.hasDisplayTile()) this.getDefaultDisplayTileOffset else this.getDataWatcher.getWatchableObjectInt(21)
    }

    def getDefaultDisplayTileOffset(): Int = 6

    def setDisplayTile(par1: Int) {
      this.getDataWatcher.updateObject(20, java.lang.Integer.valueOf(par1 & 65535 | this.getDisplayTileData << 16))
      this.setHasDisplayTile(true)
    }

    def setDisplayTileData(par1: Int) {
      val block = this.getDisplayTile
      val j = if (block == null) 0 else block.blockID
      this.getDataWatcher.updateObject(20, java.lang.Integer.valueOf(j & 65535 | par1 << 16))
      this.setHasDisplayTile(true)
    }

    def setDisplayTileOffset(par1: Int) {
      this.getDataWatcher.updateObject(21, java.lang.Integer.valueOf(par1))
      this.setHasDisplayTile(true)
    }

    def hasDisplayTile(): Boolean = false

    def setHasDisplayTile(par1: Boolean) {
      this.getDataWatcher.updateObject(22, java.lang.Byte.valueOf((if (par1) 1 else 0).toByte))
    }
  }

  abstract class EntityBoatContainer(par1World: World) extends EntityCustomBoat(par1World) with IInventory {

    private var boatContainerItems: Array[ItemStack] = new Array[ItemStack](36)

    private var dropContentsWhenDead: Boolean = true

    def this(par1World: World, par2: Double, par4: Double, par6: Double) {
      this(par1World, par2, par4, par6)
    }

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

    override def func_130002_c(player: EntityPlayer): Boolean = {
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

  object RenderBoat {

    private var texture: ResourceLocation = _
  }

  class RenderBoat extends Render with IItemRenderer {

    protected var modelBoat: ModelBase = new ModelBoat()

    protected val field_94145_f = new RenderBlocks()

    this.shadowSize = 0.5F

    def renderBoat(boat: EntityCustomBoat,
                   par2: Double,
                   par4: Double,
                   par6: Double,
                   par8: Float,
                   par9: Float) {
      GL11.glPushMatrix()
      GL11.glTranslatef(par2.toFloat, par4.toFloat, par6.toFloat)
      GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F)
      val f2 = boat.getTimeSinceHit.toFloat - par9
      var f3 = boat.getDamageTaken - par9
      if (f3 < 0.0F) {
        f3 = 0.0F
      }
      if (f2 > 0.0F) {
        GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection.toFloat, 1.0F, 0.0F,
          0.0F)
      }
      val j = boat.getDisplayTileOffset
      val block = boat.getDisplayTile
      val k = boat.getDisplayTileData
      if (block != null) {
        GL11.glPushMatrix()
        this.func_110776_a(TextureMap.locationBlocksTexture)
        val f8 = 0.75F
        GL11.glScalef(f8, f8, f8)
        GL11.glTranslatef(0.0F, j.toFloat / 16.0F, 0.0F)
        this.renderBlockInBoat(boat, par9, block, k)
        GL11.glPopMatrix()
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
        this.func_110777_b(boat)
      }
      val f4 = 0.75F
      GL11.glScalef(f4, f4, f4)
      GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
      this.func_110777_b(boat)
      GL11.glScalef(-1.0F, -1.0F, 1.0F)
      this.modelBoat.render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
      GL11.glPopMatrix()
    }

    protected def renderBlockInBoat(boat: EntityCustomBoat,
                                    par2: Float,
                                    par3Block: Block,
                                    par4: Int) {
      val f1 = boat.getBrightness(par2)
      GL11.glPushMatrix()
      this.field_94145_f.renderBlockAsItem(par3Block, par4, f1)
      GL11.glPopMatrix()
    }

    protected def func_110781_a(par1Entity: Entity): ResourceLocation = getTexture

    protected def func_110775_a(par1Entity: Entity): ResourceLocation = {
      this.func_110781_a(par1Entity.asInstanceOf[Entity])
    }

    override def doRender(entity: Entity, d0: Double, d1: Double, d2: Double, f: Float, f1: Float) {
      this.renderBoat(entity.asInstanceOf[EntityCustomBoat], d0, d1, d2, f, f1)
    }

    def handleRenderType(item: ItemStack, kind: ItemRenderType): Boolean = kind match {
      case ItemRenderType.ENTITY => true
      case ItemRenderType.EQUIPPED => true
      case ItemRenderType.EQUIPPED_FIRST_PERSON => true
      case ItemRenderType.INVENTORY => false
      case ItemRenderType.FIRST_PERSON_MAP => false
      case _ => false
    }

    def shouldUseRenderHelper(`type`: ItemRenderType, item: ItemStack, helper: ItemRendererHelper): Boolean = {
      false
    }

    def renderItem(kind: ItemRenderType, item: ItemStack, var3: AnyRef*) = kind match {
      case _ =>
        GL11.glPushMatrix()
        Minecraft.getMinecraft.renderEngine.func_110577_a(func_110781_a(getEntity))
        var defaultScale = 1F
        GL11.glScalef(defaultScale, defaultScale, defaultScale)
        GL11.glRotatef(90, -1, 0, 0)
        GL11.glRotatef(90, 0, 0, 1)
        GL11.glRotatef(180, 0, 1, 0)
        GL11.glRotatef(90, 1, 0, 0)
        GL11.glTranslatef(-0.1F, -0.5F, 0F)
        modelBoat.render(getEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F)
        GL11.glPopMatrix()
    }

    def getTexture(): ResourceLocation = {
      new ResourceLocation("textures/entity/boat.png")
    }

    def getEntity(): EntityCustomBoat = {
      val entity: EntityCustomBoat = null
      entity
    }
  }
}