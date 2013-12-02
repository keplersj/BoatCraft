package k2b6s9j.BoatCraft.boat

import k2b6s9j.BoatCraft.boat.EntityCustomBoat
import net.minecraft.item.{Item, ItemStack, ItemBoat}
import net.minecraft.world.World
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util._
import net.minecraft.entity.{EntityLivingBase, Entity}
import java.lang
import net.minecraft.block.Block
import net.minecraft.entity.item.{EntityItem, EntityBoat}
import cpw.mods.fml.relauncher.{SideOnly, Side}
import net.minecraft.block.material.Material
import net.minecraft.inventory.IInventory
import java.lang.String
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}
import net.minecraftforge.client.IItemRenderer
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.model.ModelBoat
import net.minecraft.client.renderer.RenderBlocks
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import org.lwjgl.opengl.GL11
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureMap

object Boat {

  class ItemCustomBoat(id: Int) extends ItemBoat(id) {

    @Override
    def onItemRightClick (par1ItemStack: ItemStack, par2World: World, par3EntityPlayer: EntityPlayer): ItemStack = {
      val f: Float = 1.0F
      val f1: Float = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f
      val f2: Float = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f
      val d0: Double = par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * f.toDouble
      val d1: Double = (par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * f.toDouble +1.62D - par3EntityPlayer.yOffset.toDouble)
      val d2: Double = par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * f.toDouble
      val vec3: Vec3 = par2World.getWorldVec3Pool.getVecFromPool(d0, d1, d2)
      val f3: Float = MathHelper.cos(-f2 * 0.017453292F - lang.Math.PI.toFloat)
      val f4: Float = MathHelper.sin(-f2 * 0.017453292F - lang.Math.PI.toFloat)
      val f5: Float = -MathHelper.cos(-f1 * 0.017453292F)
      val f6: Float = MathHelper.sin(-f1 * 0.017453292F)
      val f7: Float = f4 * f5
      val f8: Float = f3 * f5
      val d3: Double = 5.0D
      val vec31: Vec3 = vec3.addVector(f7.toDouble * d3,  f6.toDouble * d3, f8.toDouble * d3)
      val movingobjectposition: MovingObjectPosition = par2World.clip(vec3, vec31, true)

      if (movingobjectposition == null)
      {
        par1ItemStack
      }
      else
      {
        val vec32: Vec3 = par3EntityPlayer.getLook(f)
        var flag: Boolean = false
        val f9: Float = 1.0F
        val list = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(f9.toDouble, f9.toDouble, f9.toDouble))
        val i: Int = 0

        for (i < list.size() <- i.to(list.size()))
        {
          val entity: Entity = Entity.list.get(i)

          if (entity.canBeCollidedWith)
          {
            float.f10 = entity.getCollisionBorderSize
            AxisAlignedBB.axisalignedbb = entity.boundingBox.expand(f10: Double, f10: Double, f10: Double)

            if (axisalignedbb.isVecInside(vec3))
            {
              flag = true
            }
          }
        }

        if (flag)
        {
          par1ItemStack
        }
        else
        {
          if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
          {
            val i: Int = movingobjectposition.blockX
            val j: Int = movingobjectposition.blockY
            val k: Int = movingobjectposition.blockZ

            if (par2World.getBlockId(i, j, k) == Block.snow.blockID)
            {
              j - 1
            }

            val entityboat: EntityCustomBoat = getEntity(par2World, i, j, k)
            entityboat.rotationYaw = (((MathHelper.floor_double((par3EntityPlayer.rotationYaw * 4.0F / 360.0F).toDouble + 0.5D) & 3) - 1) * 90).toFloat

            if (!par2World.getCollidingBoundingBoxes(entityboat, entityboat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty) par1ItemStack

            if (!par2World.isRemote)
            {
              par2World.spawnEntityInWorld(entityboat)
            }

            if (!par3EntityPlayer.capabilities.isCreativeMode)
            {
              par1ItemStack.stackSize - 1
            }
          }
          par1ItemStack
        }
      }
    }

    def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: EntityCustomBoat = new EntityCustomBoat(world, (x.toFloat + 0.5F).toDouble, (y.toFloat + 1.0F).toDouble, (z.toFloat + 0.5F).toDouble)
      entity
    }
  }

  class EntityBoatContainer extends EntityCustomBoat with IInventory {
    def this(par1World: World) {
      this()
      `super`(par1World)
    }

    def this(par1World: World, par2: Double, par4: Double, par6: Double) {
      this()
      `super`(par1World, par2, par4, par6)
    }

    /**
     * Returns the stack in slot i
     */
    def getStackInSlot(par1: Int): ItemStack = {
      this.boatContainerItems(par1)
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    def decrStackSize(par1: Int, par2: Int): ItemStack = {
      if (this.boatContainerItems(par1) != null) {
        var itemstack: ItemStack = null
        if (this.boatContainerItems(par1).stackSize <= par2) {
          itemstack = this.boatContainerItems(par1)
          this.boatContainerItems(par1) = null
          itemstack
        }
        else {
          itemstack = this.boatContainerItems(par1).splitStack(par2)
          if (this.boatContainerItems(par1).stackSize == 0) {
            this.boatContainerItems(par1) = null
          }
          itemstack
        }
      }
      else {
        null
      }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    def getStackInSlotOnClosing(par1: Int): ItemStack = {
      if (this.boatContainerItems(par1) != null) {
        val itemstack: ItemStack = this.boatContainerItems(par1)
        this.boatContainerItems(par1) = null
        itemstack
      }
      else {
        null
      }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    def setInventorySlotContents(par1: Int, par2ItemStack: ItemStack) {
      this.boatContainerItems(par1) = par2ItemStack
      if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit) {
        par2ItemStack.stackSize = this.getInventoryStackLimit
      }
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    def onInventoryChanged() {
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    def isUseableByPlayer(par1EntityPlayer: EntityPlayer): Boolean = {
      if (this.isDead) false else par1EntityPlayer.getDistanceSqToEntity(this) <= 64.0D
    }

    def openChest() {
    }

    def closeChest() {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    def isItemValidForSlot(par1: Int, par2ItemStack: ItemStack): Boolean = {
      true
    }

    /**
     * Returns the name of the inventory.
     */
    def getInvName: String = {
      "container.boat"
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    def getInventoryStackLimit: Int = {
      64
    }

    /**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    def travelToDimension(par1: Int) {
      this.dropContentsWhenDead = false
      super.travelToDimension(par1)
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected def writeEntityToNBT(par1NBTTagCompound: NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound)
      val nbttaglist: NBTTagList = new NBTTagList
      {
        var i: Int = 0
        while (i < this.boatContainerItems.length) {
          {
            if (this.boatContainerItems(i) != null) {
              val nbttagcompound1: NBTTagCompound = new NBTTagCompound
              nbttagcompound1.setByte("Slot", i.asInstanceOf[Byte])
              this.boatContainerItems(i).writeToNBT(nbttagcompound1)
              nbttaglist.appendTag(nbttagcompound1)
            }
          }
          {
            i += 1
            i
          }
        }
      }
      par1NBTTagCompound.setTag("Items", nbttaglist)
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected def readEntityFromNBT(par1NBTTagCompound: NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound)
      val nbttaglist: NBTTagList = par1NBTTagCompound.getTagList("Items")
      this.boatContainerItems = new Array[ItemStack](this.getSizeInventory)
      {
        var i: Int = 0
        while (i < nbttaglist.tagCount) {
          {
            val nbttagcompound1: NBTTagCompound = nbttaglist.tagAt(i).asInstanceOf[NBTTagCompound]
            val j: Int = nbttagcompound1.getByte("Slot") & 255
            if (j >= 0 && j < this.boatContainerItems.length) {
              this.boatContainerItems(j) = ItemStack.loadItemStackFromNBT(nbttagcompound1)
            }
          }
          {
            i += 1
            i
          }
        }
      }
    }

    def func_130002_c(player: EntityPlayer): Boolean = {
      if (player.isSneaking) {
        return false
      }
      if (!this.worldObj.isRemote) {
        player.displayGUIChest(this)
      }
      true
    }

    def dropContents() {
      {
        var i: Int = 0
        while (i < this.getSizeInventory) {
          {
            val itemstack: ItemStack = this.getStackInSlot(i)
            if (itemstack != null) {
              val f: Float = this.rand.nextFloat * 0.8F + 0.1F
              val f1: Float = this.rand.nextFloat * 0.8F + 0.1F
              val f2: Float = this.rand.nextFloat * 0.8F + 0.1F
              while (itemstack.stackSize > 0) {
                var j: Int = this.rand.nextInt(21) + 10
                if (j > itemstack.stackSize) {
                  j = itemstack.stackSize
                }
                itemstack.stackSize -= j
                val entityitem: EntityItem = new EntityItem(this.worldObj, this.posX + f.asInstanceOf[Double], this.posY + f1.asInstanceOf[Double], this.posZ + f2.asInstanceOf[Double], new ItemStack(itemstack.itemID, j, itemstack.getItemDamage))
                val f3: Float = 0.05F
                entityitem.motionX = (this.rand.nextGaussian.asInstanceOf[Float] * f3).asInstanceOf[Double]
                entityitem.motionY = (this.rand.nextGaussian.asInstanceOf[Float] * f3 + 0.2F).asInstanceOf[Double]
                entityitem.motionZ = (this.rand.nextGaussian.asInstanceOf[Float] * f3).asInstanceOf[Double]
                this.worldObj.spawnEntityInWorld(entityitem)
              }
            }
          }
          {
            i += 1
            i
          }
        }
      }
    }

    def crashedDrops() {
      dropContents()
      var k: Int = 0
      {
        k = 0
        while (k < 3) {
          {
            if (isCustomBoat()) {
              this.entityDropItem(customPlank(), 0.0F)
            }
            else {
              this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F)
            }
          }
          {
            k += 1
            k
          }
        }
      }
      {
        k = 0
        while (k < 2) {
          {
            if (isCustomBoat()) {
              this.entityDropItem(customStick(), 0.0F)
            }
            else {
              this.dropItemWithOffset(Item.stick.itemID, 1, 0.0F)
            }
          }
          {
            k += 1
            k
          }
        }
      }
      if (doesBoatContainBlock()) {
        this.entityDropItem(blockInBoat(), 0.0F)
      }
    }

    private var boatContainerItems: Array[ItemStack] = new Array[ItemStack](36)
    /**
     * When set to true, the boat will drop all items when setDead() is called. When false (such as when travelling
     * dimensions) it preserves its contents.
     */
    private var dropContentsWhenDead: Boolean = true
  }

  class RenderBoat extends Render with IItemRenderer {

    var texture: ResourceLocation

    /** instance of ModelBoat for rendering */
    var model: ModelBoat

    /** instanse of RenderBlocks for rendering the blocks */
    var blocks: RenderBlocks

    def RenderBoat() {
      this.shadowSize = 0.5F
      this.model = new ModelBoat
      this.blocks = new RenderBlocks
    }

    override def handleRenderType(item: ItemStack, kind: ItemRenderType): Boolean = {
      kind match {
        case ENTITY => false
        case EQUIPPED => false
        case EQUIPPED_FIRST_PERSON => false
        case INVENTORY => false
        case FIRST_PERSON_MAP => false
        case default => false
      }
    }

    def renderBoat(boat: EntityCustomBoat, par2: Double, par4: Double, par6: Double, par8: Float, par9: Float) {
      GL11.glPushMatrix()
      GL11.glTranslatef(par2: Float, par4: Float, par6: Float)
      GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F)
      val f2: Float = boat.getTimeSinceHit - par9
      val f3: Float = boat.getDamageTaken - par9

      if (f3 < 0.0F)
      {
        val f3 = 0.0F
      }

      if (f2 > 0.0F)
      {
        GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection, 1.0F, 0.0F, 0.0F)
      }

      val j: Int = boat.getDisplayTileOffset()
      val block: Block = boat.getDisplayTile()
      val k: Int = boat.getDisplayTileData()

      if (block != null)
      {
        GL11.glPushMatrix()
        this.func_110776_a(TextureMap.field_110575_b)
        val f8: Float = 0.75F
        GL11.glScalef(f8, f8, f8)
        GL11.glTranslatef(0.0F, j / 16.0F, 0.0F)
        this.renderBlockInBoat(boat, par9, block, k)
        GL11.glPopMatrix()
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
        this.func_110777_b(boat)
      }

      val f4: Float = 0.75F
      GL11.glScalef(f4, f4, f4)
      GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
      this.func_110777_b(boat)
      GL11.glScalef(-1.0F, -1.0F, 1.0F)
      this.modelBoat.render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
      GL11.glPopMatrix()
    }

    /**
     * Renders the block that is inside the boat.
     */
    def renderBlockInBoat(boat: EntityCustomBoat, par2: Float, block: Block, par4: Int) {
      val fl: Float = boat.getBrightness(par2)
      GL11.glPushMatrix()
      this.field_94145_f.renderBlockAsItem(par3Block, par4, f1)
      GL11.glPopMatrix()
    }

    def func_110781_a(entity: Entity): ResourceLocation = {
      getTexture()
    }

    override def func_110775_a(entity: Entity): ResourceLocation = {
      this.func_110781_a((Entity)entity)
    }

    override def doRender(entity: Entity, d0: Double, d1: Double, d2: Double, f: Float, f1: Float) {
      this.renderBoat((EntityCustomBoat)entity, d0, d1, d2, f, f1)
    }

    override def getEntityTexture(entity: Entity): ResourceLocation = {
      null
    }

    override def shouldUseRenderHelper(kind: ItemRenderType, item: ItemStack, helper: ItemRendererHelper): Boolean = {
      false
    }

    override def renderItem(kind: ItemRenderType, item: ItemStack, var3: Object*) {
      kind match {
        case default => {
          GL11.glPushMatrix()
          Minecraft.getMinecraft.renderEngine.func_110577_a(func_110781_a(getEntity()))

          val defaultScale: Float = 1F
          GL11.glScalef(defaultScale, defaultScale, defaultScale)
          GL11.glRotatef(90, -1, 0, 0)
          GL11.glRotatef(90, 0, 0, 1)
          GL11.glRotatef(180, 0, 1, 0)
          GL11.glRotatef(90, 1, 0, 0)
          GL11.glTranslatef(-0.1F, -0.5F, 0F) // Left-Right
          // Forward-Backwards Up-Down
          model.render(getEntity(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F)

          GL11.glPopMatrix()
        }
      }
    }

    def getTexture(): ResourceLocation = {
      new ResourceLocation("textures/entity/boat.png")
    }

    def getEntity(): EntityCustomBoat = {
      val entity = null
      entity
    }

  }

}