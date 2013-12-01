package k2b6s9j.BoatCraft.boat

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

object Boat {
  trait ItemCustomBoat extends ItemBoat {

    def ItemCustomBoat( par1: Int) {
      super.par1
    }

    @Override
    def onItemRightClick (par1ItemStack: ItemStack, par2World: World, par3EntityPlayer: EntityPlayer)
    {
      float.f = 1.0F
      float.f1 = par3EntityPlayer.prevRotationPitch + (par3EntityPlayer.rotationPitch - par3EntityPlayer.prevRotationPitch) * f
      float.f2 = par3EntityPlayer.prevRotationYaw + (par3EntityPlayer.rotationYaw - par3EntityPlayer.prevRotationYaw) * f
      double.d0 = (par3EntityPlayer.prevPosX + (par3EntityPlayer.posX - par3EntityPlayer.prevPosX) * double).f
      double.d1 = (par3EntityPlayer.prevPosY + (par3EntityPlayer.posY - par3EntityPlayer.prevPosY) * double f +1.62D - double).par3EntityPlayer.yOffset
      double.d2 = (par3EntityPlayer.prevPosZ + (par3EntityPlayer.posZ - par3EntityPlayer.prevPosZ) * double).f
      Vec3.vec3 = par2World.getWorldVec3Pool.getVecFromPool(d0, d1, d2)
      float.f3 = MathHelper.cos(-f2 * 0.017453292F - lang.Math.PI)
      float.f4 = MathHelper.sin(-f2 * 0.017453292F - lang.Math.PI)
      float.f5 = -MathHelper.cos(-f1 * 0.017453292F)
      float.f6 = MathHelper.sin(-f1 * 0.017453292F)
      float.f7 = f4 * f5
      float.f8 = f3 * f5
      double.d3 = 5.0D
      Vec3.vec31 = vec3.addVector(double f7 * d3, double f6 * d3, double f8 * d3)
      MovingObjectPosition.movingobjectposition = par2World.clip(vec3, vec31, true)

      if (movingobjectposition == null)
      {
        par1ItemStack
      }
      else
      {
        Vec3.vec32 = par3EntityPlayer.getLook(f)
        boolean.flag = false
        float.f9 = 1.0F
        val list: List = par2World.getEntitiesWithinAABBExcludingEntity(par3EntityPlayer, par3EntityPlayer.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(double f9, double f9, double f9))
        Int.i

        for (i < list.size() <- ++.i)
        {
          Entity.entity = Entity.list.get(i)

          if (entity.canBeCollidedWith())
          {
            float.f10 = entity.getCollisionBorderSize()
            AxisAlignedBB.axisalignedbb = entity.boundingBox.expand(double f10, double f10, double f10)

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
            i = movingobjectposition.blockX
            int.j = movingobjectposition.blockY
            int.k = movingobjectposition.blockZ

            if (par2World.getBlockId(i, j, k) == Block.snow.blockID)
            {
              --.j
            }

            EntityCustomBoat.entityboat = getEntity(par2World, i, j, k)
            entityboat.rotationYaw = float(((MathHelper.floor_double(double(par3EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90)

            if (!par2World.getCollidingBoundingBoxes(entityboat, entityboat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty) par1ItemStack

            if (!par2World.isRemote)
            {
              par2World.spawnEntityInWorld(entityboat)
            }

            if (!par3EntityPlayer.capabilities.isCreativeMode)
            {
              --.par1ItemStack.stackSize
            }
          }

          par1ItemStack
        }
      }
    }

    def getEntity(world: World, x: Int, y: Int, z: Int) {
      EntityBoatOak.entity = new EntityBoatOak(world, double(float x + 0.5F), double(float y + 1.0F), double(float z + 0.5F))
      entity
    }
  }

  trait EntityCustomBoat extends EntityBoat {
    def field_70279_a: Boolean
    def speedMultiplier: Double
    def boatPosRotationIncrements: Int
    def boatX: Double
    def boatY: Double
    def boatZ: Double
    def boatYaw: Double
    def boatPitch: Double
    @SideOnly(Side.CLIENT)
    def velocityX: Double
    @SideOnly(Side.CLIENT)
    def velocityY: Double
    @SideOnly(Side.CLIENT)
    def velocityZ: Double

    def EntityCustomBoat(par1World: World)
    {
      super.par1World
      this.field_70279_a = true
      this.speedMultiplier = 0.07D
      this.preventEntitySpawning = true
      this.setSize(1.5F, 0.6F)
      this.yOffset = this.height / 2.0F
    }

    def EntityCustomBoat(par1World: World, par2: Double, par4: Double, par6: Double)
    {
      this(par1World)
      this.setPosition(par2, par4 + this.yOffset, par6)
      this.motionX = 0.0D
      this.motionY = 0.0D
      this.motionZ = 0.0D
      this.prevPosX = par2
      this.prevPosY = par4
      this.prevPosZ = par6
    }

    def isCustomBoat()
    {
      false
    }

    def customBoatItem()
    {
      new ItemStack(Item.boat, 1, 0)
    }

    def useItemID()
    {
      false
    }

    def customBoatItemID()
    {
      Item.boat.itemID
    }

    def customPlank()
    {
      new ItemStack(Block.planks, 1, 0)
    }

    def customStick()
    {
      new ItemStack(Item.stick, 1, 0)
    }

    def doesBoatContainBlock()
    {
      false
    }

    def blockInBoat()
    {
      null
    }

    def crashedDrops()
    {
      int.k

      for (k < 3 <- ++.k)
      {
        if (isCustomBoat())
        {
          this.entityDropItem(customPlank(), 0.0F)
        }
        else
        {
          this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F)
        }
      }

      for ( k < 2 <- ++.k)
      {
        if (isCustomBoat())
        {
          this.entityDropItem(customStick(), 0.0F)
        }
        else
        {
          this.dropItemWithOffset(Item.stick.itemID, 1, 0.0F)
        }
      }

      if (doesBoatContainBlock())
      {
        this.entityDropItem(blockInBoat(), 0.0F)
      }
    }

    /**
     * Called to update the entity's position/logic.
     */
    def onUpdate()
    {
      super.onUpdate()

      if (this.getTimeSinceHit > 0)
      {
        this.setTimeSinceHit(this.getTimeSinceHit - 1)
      }

      if (this.getDamageTaken > 0.0F)
      {
        this.setDamageTaken(this.getDamageTaken - 1.0F)
      }

      this.prevPosX = this.posX
      this.prevPosY = this.posY
      this.prevPosZ = this.posZ
      byte.b0 = 5
      double.d0 = 0.0D

      for ( i < b0 <- ++.i)
      {
        double.d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * double(i + 0) / double b0 - 0.125D
        double.d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * double(i + 1) / double b0 - 0.125D
        AxisAlignedBB.axisalignedbb = AxisAlignedBB.getAABBPool.getAABB(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ)

        if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
        {
          d0.+=
        }
      }

      double.d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)
      double d4;
      double.d5

      if (d3 > 0.26249999999999996D)
      {
        d4 = Math.cos(this.rotationYaw * Math.PI / 180.0D)
        d5 = Math.sin(this.rotationYaw * Math.PI / 180.0D)

        for (j < 1.0D + d3 * 60.0D <- ++.j)
        {
          double.d6 = double(this.rand.nextFloat() * 2.0F - 1.0F)
          double.d7 = double(this.rand.nextInt(2) * 2 - 1) * 0.7D
          double d8;
          double.d9

          if (this.rand.nextBoolean())
          {
            d8 = this.posX - d4 * d6 * 0.8D + d5 * d7
            d9 = this.posZ - d5 * d6 * 0.8D - d4 * d7
            this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ)
          }
          else
          {
            d8 = this.posX + d4 + d5 * d6 * 0.7D
            d9 = this.posZ + d5 - d4 * d6 * 0.7D
            this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ)
          }
        }
      }

      double d10;
      double.d11

      if (this.worldObj.isRemote && this.field_70279_a)
      {
        if (this.boatPosRotationIncrements > 0)
        {
          d4 = this.posX + (this.boatX - this.posX) / this.boatPosRotationIncrements
          d5 = this.posY + (this.boatY - this.posY) / this.boatPosRotationIncrements
          d11 = this.posZ + (this.boatZ - this.posZ) / this.boatPosRotationIncrements
          d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - this.rotationYaw)
          this.rotationYaw = float(this.rotationYaw + d10 / this.boatPosRotationIncrements)
          this.rotationPitch = float(this.rotationPitch + (this.boatPitch - this.rotationPitch) / this.boatPosRotationIncrements)
          this.boatPosRotationIncrements
          this.setPosition(d4, d5, d11)
          this.setRotation(this.rotationYaw, this.rotationPitch)
        }
        else
        {
          d4 = this.posX + this.motionX
          d5 = this.posY + this.motionY
          d11 = this.posZ + this.motionZ
          this.setPosition(d4, d5, d11)

          if (this.onGround)
          {
            this.motionX *= 0.5D
            this.motionY *= 0.5D
            this.motionZ *= 0.5D
          }

          this.motionX *= 0.9900000095367432D
          this.motionY *= 0.949999988079071D
          this.motionZ *= 0.9900000095367432D
        }
      }
      else
      {
        if (d0 < 1.0D)
        {
          d4 = d0 * 2.0D - 1.0D
          this.motionY += 0.03999999910593033D * d4
        }
        else
        {
          if (this.motionY < 0.0D)
          {
            this.motionY /= 2.0D
          }

          this.motionY += 0.007000000216066837D
        }

        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)
          if (d4 > 0.0D)
          {
            d5 = -Math.sin(double(this.riddenByEntity.rotationYaw * Math.PI / 180.0F))
            d11 = Math.cos(double(this.riddenByEntity.rotationYaw * Math.PI / 180.0F))
            this.motionX += d5 * this.speedMultiplier * 0.05000000074505806D
            this.motionZ += d11 * this.speedMultiplier * 0.05000000074505806D
          }

        d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ)

        if (d4 > 0.35D)
        {
          d5 = 0.35D / d4
          this.motionX *= d5
          this.motionZ *= d5
          d4 = 0.35D
        }

        if (d4 > d3 && this.speedMultiplier < 0.35D)
        {
          this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D

          if (this.speedMultiplier > 0.35D)
          {
            this.speedMultiplier = 0.35D
          }
        }
        else
        {
          this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D
          if (this.speedMultiplier < 0.07D)
            this.speedMultiplier = 0.07D
        }

        if (this.onGround)
        {
          this.motionX *= 0.5D
          this.motionY *= 0.5D
          this.motionZ *= 0.5D
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ)

        if (this.isCollidedHorizontally && d3 > 0.2D)
        {
          if (!this.worldObj.isRemote && !this.isDead)
          {
            this.setDead()
            this.crashedDrops()
          }
        }
        else
        {
          this.motionX *= 0.9900000095367432D
          this.motionY *= 0.949999988079071D
          this.motionZ *= 0.9900000095367432D
        }

        this.rotationPitch = 0.0F
        d5 = this.rotationYaw
        d11 = this.prevPosX - this.posX
        d10 = this.prevPosZ - this.posZ

        if (d11 * d11 + d10 * d10 > 0.001D)
        {
          d5 = double(float(Math.atan2(d10, d11) * 180.0D / Math.PI))
        }

        double.d12 = MathHelper wrapAngleTo180_double d5 - this.rotationYaw

        if (d12 > 20.0D)
        {
          d12 = 20.0D
        }

        if (d12 < -20.0D)
        {
          d12 = -20.0D
        }

        this.rotationYaw = float(this.rotationYaw + d12)
        this.setRotation(this.rotationYaw, this.rotationPitch)

        if (!this.worldObj.isRemote)
        {
          val list: List = this.worldObj getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D))
          int.l

          if (list != null && !list.isEmpty())
          {
            for (l < list.size() <- ++.l)
            {
              Entity.entity = Entity.list.get(l)

              if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityCustomBoat)
              {
                entity.applyEntityCollision(this)
              }
            }
          }

          for (l < 4 <- ++.l)
          {
            int.i1 = MathHelper.floor_double(this.posX + (double(l % 2) - 0.5D) * 0.8D)
            int.j1 = MathHelper.floor_double(this.posZ + (double(l / 2) - 0.5D) * 0.8D)

            for (k1 < 2 <- ++.k1)
            {
              int.l1 = MathHelper.floor_double(this.posY) + k1
              int.i2 = this.worldObj.getBlockId(i1, l1, j1)

              if (i2 == Block.snow.blockID)
              {
                this.worldObj.setBlockToAir(i1, l1, j1)
              }
              else if (i2 == Block.waterlily.blockID)
              {
                this.worldObj.destroyBlock(i1, l1, j1, true)
              }
            }
          }

          if (this.riddenByEntity != null && this.riddenByEntity.isDead)
          {
            this.riddenByEntity = null
          }
        }
      }
    }

    def func_130002_c(player: EntityPlayer)
    {
      //Do not mount if the player is shift clicking
      if (player.isSneaking)
        false
      if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
        true
      else
      {
        if (!this.worldObj.isRemote)
          player.mountEntity(this)
        true
      }
    }

    def getDisplayTile()
    {
      if (!this.hasDisplayTile())
      {
        this.getDefaultDisplayTile()
      }
      else
      {
        val i: Int = this.getDataWatcher.getWatchableObjectInt(20) & 65535
        return i > 0 && i < Block.blocksList.length ? Block.blocksList[i]
      }
    }

    def getDefaultDisplayTile()
    {
      null
    }

    def getDisplayTileData()
    {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTileData(); this.getDataWatcher.getWatchableObjectInt(20) >> 16
    }

    def getDefaultDisplayTileData()
    {
      0
    }

    def getDisplayTileOffset()
    {
      return !this.hasDisplayTile() ? this.getDefaultDisplayTileOffset(); this.getDataWatcher.getWatchableObjectInt(21)
    }

    def getDefaultDisplayTileOffset()
    {
      6
    }

    def setDisplayTile(par1: Int)
    {
      this.getDataWatcher.updateObject(20, Integer.valueOf(par1 & 65535 | this.getDisplayTileData() << 16))
      this.setHasDisplayTile(par1 = true)
    }

    def setDisplayTileData(par1: Int)
    {
      val block: Block = this.getDisplayTile()
      def j: Int = block == null ? 0 : block.blockID
      this.getDataWatcher.updateObject(20, Integer.valueOf(j & 65535 | par1 << 16))
      this.setHasDisplayTile(par1 = true)
    }

    def setDisplayTileOffset(par1: Int)
    {
      this.getDataWatcher.updateObject(21, Integer.valueOf(par1))
      this.setHasDisplayTile(par1 = true)
    }

    def hasDisplayTile()
    {
      false
    }

    def setHasDisplayTile(par1: Boolean)
    {
      this.getDataWatcher.updateObject(22, Byte.==(par1))
    }
  }

  trait EntityBoatContainer extends EntityCustomBoat with IInventory {
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

  trait RenderBoat extends Render with IItemRenderer {

  }

}