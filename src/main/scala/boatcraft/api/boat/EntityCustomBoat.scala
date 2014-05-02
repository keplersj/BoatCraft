package boatcraft.api.boat

import cpw.mods.fml.common.ObfuscationReflectionHelper
import boatcraft.api.{Registry, getCustomBoat, traits}
import net.minecraft.block.material.Material
import net.minecraft.entity.{Entity, EntityLivingBase}
import net.minecraft.entity.item.{EntityBoat, EntityItem}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.{Blocks, Items}
import net.minecraft.inventory.IInventory
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.{AxisAlignedBB, MathHelper, MovingObjectPosition}
import net.minecraft.world.World
import net.minecraft.util.DamageSource

//TODO: Fill Documentation
/**
  *
  * @param world
  * @param x
  * @param y
  * @param z
  */
case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double)
	extends EntityBoat(world, x, y, z) with IInventory
{
	//TODO var linkedTo: EntityCustomBoat = null
	
	def this(world: World) = this(world, 0, 0, 0)
	
	override protected def entityInit
	{
		super.entityInit
		
		dataWatcher addObject (20, "")
		dataWatcher addObject (21, "")
		dataWatcher addObject (22, "")
	}
	
	override protected def writeEntityToNBT(tag: NBTTagCompound)
	{
		tag setString ("material", getMaterialName)
		tag setString ("modifier", getBlockName)
		
		getMaterial writeStateToNBT (this, tag)
    getBlock writeStateToNBT(this, tag)
	}
	
	override protected def readEntityFromNBT(tag: NBTTagCompound)
	{
		setMaterial(tag getString "material")
		setModifier(tag getString "block")
		
		getMaterial readStateFromNBT (this, tag)
    getBlock readStateFromNBT(this, tag)
	}
	
	override def attackEntityFrom(par1DamageSource: DamageSource, par2: Float): Boolean =
	{
		if (isEntityInvulnerable)
			return false
		else if (!worldObj.isRemote && !isDead)
		{
			setForwardDirection(-getForwardDirection)
			setTimeSinceHit(10)
			setDamageTaken(getDamageTaken + par2 * 10)
			setBeenAttacked
			val flag = par1DamageSource.getEntity.isInstanceOf[EntityPlayer] &&
			(par1DamageSource.getEntity.asInstanceOf[EntityPlayer]).capabilities.isCreativeMode
			
			if (flag || getDamageTaken > 40)
			{
				if (riddenByEntity != null)
					riddenByEntity mountEntity this
				
				if (!flag)
					func_145778_a(Items.boat, 1, 0)
				
				setDead
			}

			return true
		}
		else
			return true
	}
	
	override def onUpdate
	{
//		super.onUpdate
		doUpdate
//TODO  
//		val vX = posX - linkedTo.posX
//		val vY = posY - linkedTo.posY
//		val vZ = posZ - linkedTo.posZ
//		val magV = Math sqrt (vX * vX + vY * vY + vZ * vZ)
//		val aX = linkedTo.posX + vX / magV
//		val aY = linkedTo.posY + vY / magV
//		val aZ = linkedTo.posZ + vZ / magV
//		var fix = 0
//		if (vX < 0) fix = 180
//		else if (vY < 0) fix = 360
//		val jaw = Math.atan(vY / vX) + fix
//		setPositionAndRotation2(aX, aY, aZ, jaw toFloat, rotationPitch, 3)
		
		getMaterial update this
    getBlock update this
		
		ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
			getMaterial.getSpeedMultiplier * ObfuscationReflectionHelper.getPrivateValue(
				classOf[EntityBoat], this,
				"speedMultiplier", "field_70276_b").asInstanceOf[Double],
				"speedMultiplier", "field_70276_b")
	}

	private def doUpdate
	{
		val isBoatEmpty: Boolean = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"isBoatEmpty", "field_70279_a")
		val boatPosRotationIncrements: Int = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"boatPosRotationIncrements", "field_70277_c")
		val boatX: Double = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"boatX", "field_70274_d")
		val boatY: Double = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"boatY", "field_70275_e")
		val boatZ: Double = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"boatZ", "field_70272_f")
		val boatYaw: Double = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"boatYaw", "field_70273_g")
		val boatPitch: Double = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"boatPitch", "field_70281_h")
		val speedMultiplier: Double = ObfuscationReflectionHelper getPrivateValue(
				classOf[EntityBoat], this,
				"speedMultiplier", "field_70276_b")
		
		if (getTimeSinceHit > 0)
			setTimeSinceHit(getTimeSinceHit - 1)
		if (getDamageTaken > 0.0F)
			setDamageTaken(getDamageTaken - 1.0F)
		prevPosX = posX
		prevPosY = posY
		prevPosZ = posZ
		val b0 = 5
		var d0 = 0.0D
		for (i <- 0 until b0)
		{
			val d1 = boundingBox.minY +
				(boundingBox.maxY - boundingBox.minY) * (i + 0).toDouble /
				b0.toDouble -
				0.125D
			val d3 = boundingBox.minY +
				(boundingBox.maxY - boundingBox.minY) * (i + 1).toDouble /
				b0.toDouble -
				0.125D
			val axisalignedbb = AxisAlignedBB.getAABBPool.getAABB(boundingBox.minX, d1, boundingBox.minZ,
				boundingBox.maxX, d3, boundingBox.maxZ)
			if (worldObj.isAABBInMaterial(axisalignedbb, Material.water))
				d0 += 1.0D / b0.toDouble
		}
		val d10 = Math.sqrt(motionX * motionX + motionZ * motionZ)
		var d2: Double = 0.0
		var d4: Double = 0.0
		var j: Int = 0
		if (d10 > 0.26249999999999996D)
		{
			d2 = Math cos rotationYaw.toDouble * Math.PI / 180.0D
			d4 = Math sin rotationYaw.toDouble * Math.PI / 180.0D
			j = 0
			while (j.toDouble < 1.0D + d10 * 60.0D)
			{
				val d5 = (rand.nextFloat() * 2.0F - 1.0F).toDouble
				val d6 = (rand.nextInt(2) * 2 - 1).toDouble * 0.7D
				var d8: Double = 0.0
				var d9: Double = 0.0
				if (rand nextBoolean)
				{
					d8 = posX - d2 * d5 * 0.8D + d4 * d6
					d9 = posZ - d4 * d5 * 0.8D - d2 * d6
					worldObj.spawnParticle("splash", d8, posY - 0.125D, d9, motionX, motionY,
						motionZ)
				}
				else
				{
					d8 = posX + d2 + d4 * d5 * 0.7D
					d9 = posZ + d4 - d2 * d5 * 0.7D
					worldObj.spawnParticle("splash", d8, posY - 0.125D, d9, motionX, motionY,
						motionZ)
				}
				j = j + 1
			}
		}
		var d11: Double = 0.0
		var d12: Double = 0.0
		if (worldObj.isRemote && isBoatEmpty)
		{
			if (boatPosRotationIncrements > 0)
			{
				d2 = posX +
					(boatX - posX) / boatPosRotationIncrements.toDouble
				d4 = posY +
					(boatY - posY) / boatPosRotationIncrements.toDouble
				d11 = posZ +
					(boatZ - posZ) / boatPosRotationIncrements.toDouble
				d12 = MathHelper.wrapAngleTo180_double(boatYaw - rotationYaw.toDouble)
				rotationYaw = (rotationYaw.toDouble + d12 / boatPosRotationIncrements.toDouble).toFloat
				rotationPitch = (rotationPitch.toDouble +
					(boatPitch - rotationPitch.toDouble) / boatPosRotationIncrements.toDouble).toFloat
				ObfuscationReflectionHelper setPrivateValue(
						classOf[EntityBoat], this,
						boatPosRotationIncrements - 1,
						"boatPosRotationIncrements", "field_70277_c")
				setPosition(d2, d4, d11)
				setRotation(rotationYaw, rotationPitch)
			}
			else
			{
				d2 = posX + motionX
				d4 = posY + motionY
				d11 = posZ + motionZ
				setPosition(d2, d4, d11)
				if (onGround)
				{
					motionX *= 0.5D
					motionY *= 0.5D
					motionZ *= 0.5D
				}
				motionX *= 0.9900000095367432D
				motionY *= 0.949999988079071D
				motionZ *= 0.9900000095367432D
			}
		}
		else
		{
			if (d0 < 1.0D)
			{
				d2 = d0 * 2.0D - 1.0D
				motionY += 0.03999999910593033D * d2
			}
			else
			{
				if (motionY < 0.0D)
				{
					motionY /= 2.0D
				}
				motionY += 0.007000000216066837D
			}
			if (riddenByEntity != null && riddenByEntity.isInstanceOf[EntityLivingBase])
			{
				val entitylivingbase = riddenByEntity.asInstanceOf[EntityLivingBase]
				val f = riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F
				motionX += -Math.sin((f * Math.PI.toFloat / 180.0F).toDouble) * speedMultiplier *
					entitylivingbase.moveForward.toDouble *
					0.05000000074505806D
				motionZ += Math.cos((f * Math.PI.toFloat / 180.0F).toDouble) * speedMultiplier *
					entitylivingbase.moveForward.toDouble *
					0.05000000074505806D
			}
			d2 = Math.sqrt(motionX * motionX + motionZ * motionZ)
			if (d2 > 0.35D)
			{
				d4 = 0.35D / d2
				motionX *= d4
				motionZ *= d4
				d2 = 0.35D
			}
			if (d2 > d10 && speedMultiplier < 0.35D)
			{
				ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
						speedMultiplier + (0.35D - speedMultiplier) / 35.0D,
						"speedMultiplier", "field_70276_b")
				if (speedMultiplier > 0.35D)
					ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
						0.35D,
						"speedMultiplier", "field_70276_b")
			}
			else
			{
				ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
						speedMultiplier - (speedMultiplier - 0.07D) / 35.0D,
						"speedMultiplier", "field_70276_b")
				if (speedMultiplier < 0.07D)
					ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
						0.07D,
						"speedMultiplier", "field_70276_b")
			}
			var l: Int = 0
			l = 0
			while (l < 4)
			{
				val i1 = MathHelper.floor_double(posX + ((l % 2).toDouble - 0.5D) * 0.8D)
				j = MathHelper.floor_double(posZ + ((l / 2).toDouble - 0.5D) * 0.8D)
				for (j1 <- 0 until 2)
				{
					val k = MathHelper.floor_double(posY) + j1
					val block = worldObj.getBlock(i1, k, j)
					if (block == Blocks.snow_layer)
					{
						worldObj.setBlockToAir(i1, k, j)
						isCollidedHorizontally = false
					}
					else if (block == Blocks.waterlily)
					{
						worldObj.func_147480_a(i1, k, j, true)
						isCollidedHorizontally = false
					}
				}
				l = l + 1
			}
			if (onGround)
			{
				motionX *= 0.5D
				motionY *= 0.5D
				motionZ *= 0.5D
			}
			moveEntity(motionX, motionY, motionZ)
			if (isCollidedHorizontally && d10 > (getMaterial.getCrashResistance * 0.2D))
			{
				if (!worldObj.isRemote && !isDead)
				{
					setDead
					
					for (l <- 0 until 3)
						func_145778_a(Item.getItemFromBlock(Blocks.planks), 1, 0.0F)
					
					for (l <- 0 until 2)
						func_145778_a(Items.stick, 1, 0.0F)
				}
			}
			else
			{
				motionX *= 0.9900000095367432D
				motionY *= 0.949999988079071D
				motionZ *= 0.9900000095367432D
			}
			rotationPitch = 0.0F
			d4 = rotationYaw.toDouble
			d11 = prevPosX - posX
			d12 = prevPosZ - posZ
			if (d11 * d11 + d12 * d12 > 0.001D)
				d4 = (Math.atan2(d12, d11) * 180.0D / Math.PI).toFloat.toDouble
			var d7 = MathHelper.wrapAngleTo180_double(d4 - rotationYaw.toDouble)
			if (d7 > 20.0D)
				d7 = 20.0D
			else if (d7 < -20.0D)
				d7 = -20.0D
			rotationYaw = (rotationYaw.toDouble + d7).toFloat
			setRotation(rotationYaw, rotationPitch)
			if (!worldObj.isRemote)
			{
				val list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D,
					0.0D, 0.20000000298023224D))
				if (list != null && !list.isEmpty)
				{
					for (k1 <- 0 until list.size)
					{
						val entity = list.get(k1).asInstanceOf[Entity]
						if (entity != riddenByEntity && entity.canBePushed &&
							entity.isInstanceOf[EntityBoat])
							entity applyEntityCollision this
					}
				}
				if (riddenByEntity != null && riddenByEntity.isDead)
					riddenByEntity = null
			}
		}
	}
	
	override def func_145778_a(item: Item, count: Int, f: Float): EntityItem =
	{
		var stack: ItemStack = new ItemStack(item, count)

		if (item == Items.boat)
			stack = getCustomBoat(getMaterialName, getBlockName)
		else if (item == Item.getItemFromBlock(Blocks.planks))
			stack = getMaterial getItem
		else if (item == Items.stick)
		{
			stack = getMaterial getStick

			if (stack == null && rand.nextBoolean) stack = getMaterial getItem
		}
		else return super.func_145778_a(item, count, f)

		if (stack != null) entityDropItem(stack, f)
		else null
	}
	
	override def interactFirst(player: EntityPlayer): Boolean =
	{
		if (player.getCurrentEquippedItem.getItem == Items.name_tag
			&& player.getCurrentEquippedItem.hasDisplayName)
		{
			setName(player.getCurrentEquippedItem.getDisplayName)
			println("Set the name to " + player.getCurrentEquippedItem.getDisplayName)
			return false
		}
		if (getBlock isRideable) return super.interactFirst(player)
		getBlock interact (player, this)
		return true
	}
	
	override def getPickedResult(target: MovingObjectPosition) =
		getCustomBoat(getMaterialName, getBlockName)
	
	/**
	  * A setter for the boat's material
	  * @param material the new material
	  */
	def setMaterial(material: String) =
		dataWatcher updateObject (20, material)

	/**
	  * A setter for the boat's modifier
	  * @param modifier the new modifier
	  */
	def setModifier(modifier: String) =
		dataWatcher updateObject (21, modifier)
	
	def setName(name: String) =
		dataWatcher updateObject(22, name)
	
	def hasName = !(dataWatcher getWatchableObjectString 22).isEmpty

	/**
	  * A getter for the boat's material
	  * @return the boat's material
	  */
	def getMaterial: traits.Material =
			(Registry find (dataWatcher getWatchableObjectString 20))
			.asInstanceOf[traits.Material]

	/**
	  * A getter for the boat's block
	  * @return the boat's block
	  */
	def getBlock: traits.Block =
		(Registry find (dataWatcher getWatchableObjectString 21))
		.asInstanceOf[traits.Block]

	/**
	  * A getter for the name of the boat's material
	  * @return the name of the boat's material
	  */
	def getMaterialName =
		dataWatcher getWatchableObjectString 20

	/**
	  * A getter fo the name of the boat's block
	  * @return the name of the boat's block
	  */
	def getBlockName =
		dataWatcher getWatchableObjectString 21
	
	def getName =
		dataWatcher getWatchableObjectString 22
	
	//IInventory implementation
	override def getSizeInventory =
		getInventory getSizeInventory
	
	override def getStackInSlot(slot: Int) =
		getInventory getStackInSlot slot
	
	override def decrStackSize(slot: Int, count: Int) =
		getInventory decrStackSize (slot, count)
	
	override def getStackInSlotOnClosing(slot: Int) =
		getInventory getStackInSlotOnClosing slot
	
	override def setInventorySlotContents(slot: Int, stack: ItemStack) =
		getInventory setInventorySlotContents (slot, stack)
	
	override def getInventoryName =
		getInventory getInventoryName
	
	override def hasCustomInventoryName =
		getInventory hasCustomInventoryName
	
	override def getInventoryStackLimit =
		getInventory getInventoryStackLimit
	
	override def markDirty =
		getInventory markDirty
	
	override def isUseableByPlayer(player: EntityPlayer) =
		getDistanceSqToEntity(player) <= 64
	
	override def openInventory = getInventory.openInventory
	
	override def closeInventory = getInventory.closeInventory
	
	override def isItemValidForSlot(slot: Int, stack: ItemStack) =
		getInventory.isItemValidForSlot(slot, stack)
	
	private var inventory: IInventory = null
	
	protected[boatcraft] def getInventory =
	{
		if (inventory == null) inventory = getBlock getInventory this
		inventory
	}
}

object EntityCustomBoat
{
	private[api] object NoInventory extends IInventory
	{
		override def getSizeInventory = 0
		
		override def getStackInSlot(slot: Int): ItemStack = null
		
		override def decrStackSize(slot: Int, count: Int): ItemStack = null
		
		override def getStackInSlotOnClosing(slot: Int): ItemStack = null
		
		override def setInventorySlotContents(slot: Int, stack: ItemStack) {}
		
		override def getInventoryName = ""
		
		override def hasCustomInventoryName = false
		
		override def getInventoryStackLimit = 0
		
		override def markDirty {}
		
		override def isUseableByPlayer(player: EntityPlayer) = false
		
		override def openInventory {}
		
		override def closeInventory {}
		
		override def isItemValidForSlot(slot: Int, stack: ItemStack) = false
	}
}