package boatcraft.api.boat

import boatcraft.api.Registry
import boatcraft.api.getCustomBoat
import boatcraft.api.modifiers
import boatcraft.api.modifiers.ExtendedBoat
import boatcraft.api.modifiers.Mountable
import cpw.mods.fml.common.ObfuscationReflectionHelper
import net.minecraft.block.material.Material
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.item.EntityBoat
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import net.minecraft.inventory.IInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.DamageSource
import net.minecraft.util.MathHelper
import net.minecraft.util.MovingObjectPosition
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants

/**
 * The main Boat entity class
 * @param world The instance of the Minecraft world the Boat Entity exists in.
 * @param x The X position of the Boat Entity in the world.
 * @param y The Y position of the Boat Entity in the world.
 * @param z The Z position of the Boat Entity in the world.
 */
case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double)
	extends EntityBoat(world, x, y, z) {
	
	import EntityCustomBoat._
	
	//TODO var linkedTo: EntityCustomBoat = null

	def this(world: World) = this(world, 0, 0, 0)

	override protected def entityInit {
		super.entityInit

		dataWatcher addObject(MATERIAL, "")
		dataWatcher addObject(BLOCK, "")
		dataWatcher addObject(NAME, "")
	}

	override protected def writeEntityToNBT(tag: NBTTagCompound) {
		tag setString("material", getMaterialName)
		tag setString("block", getBlockName)
		
		var materialTag, blockTag = new NBTTagCompound
		
		getMaterial writeStateToNBT(this, materialTag)
		getBlock writeStateToNBT(this, blockTag)
		
		tag.setTag("materialTag", materialTag)
		tag.setTag("blockTag", blockTag)
	}

	override protected def readEntityFromNBT(tag: NBTTagCompound) {
		setMaterial(tag getString "material")
		setBlock(tag getString "block")
		
		//TODO Legacy, remove in v2.2
		if (!tag.hasKey("materialTag", Constants.NBT.TAG_COMPOUND)) getMaterial readStateFromNBT(this, tag)
		else getMaterial readStateFromNBT(this, tag.getCompoundTag("materialTag"))
		
		//TODO Legacy, remove in v2.2
		if (!tag.hasKey("blockTag", Constants.NBT.TAG_COMPOUND)) getBlock readStateFromNBT(this, tag)
		else getBlock readStateFromNBT(this, tag.getCompoundTag("blockTag"))
	}

	override def attackEntityFrom(source: DamageSource, amount: Float): Boolean =
		if (isEntityInvulnerable)
			false
		else if (!worldObj.isRemote && !isDead) {
			setForwardDirection(-getForwardDirection)
			setTimeSinceHit(10)
			setDamageTaken(getDamageTaken + amount * 10)
			setBeenAttacked()
			val flag = source.getEntity.isInstanceOf[EntityPlayer] &&
				source.getEntity.asInstanceOf[EntityPlayer].capabilities.isCreativeMode
			
			if (flag || getDamageTaken > 40 * getCrashResistance) {
				if (riddenByEntity != null)
					riddenByEntity mountEntity this
				if (!flag)
				{
					if (source.isFireDamage && getBlock.getContent != null)
						entityDropItem(getBlock.getContent, 0)
					else func_145778_a(Items.boat, 1, 0)
				}
				
				setDead
			}
			
			true
		}
		else
			true

	override def onUpdate {
		//		super.onUpdate
		doUpdate
		//TODO linking
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
		
		if (isWet || isImmuneToFire) extinguish

		ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
			getSpeedMultiplier * ObfuscationReflectionHelper.getPrivateValue(
				classOf[EntityBoat], this,
				"speedMultiplier", "field_70276_b").asInstanceOf[Double],
			"speedMultiplier", "field_70276_b")
	}

	private def doUpdate {
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
		if (getDamageTaken > 0)
			setDamageTaken(getDamageTaken - 1)
		prevPosX = posX
		prevPosY = posY
		prevPosZ = posZ
		val b0 = 5
		var d0 = 0.0D
		for (i <- 0 until b0) {
			val d1 = boundingBox.minY +
				(boundingBox.maxY - boundingBox.minY) * (i + 0).toDouble / b0.toDouble - 0.125D
			val d3 = boundingBox.minY +
				(boundingBox.maxY - boundingBox.minY) * (i + 1).toDouble / b0.toDouble - 0.125D
			val axisalignedbb = AxisAlignedBB.getBoundingBox(boundingBox.minX, d1, boundingBox.minZ,
				boundingBox.maxX, d3, boundingBox.maxZ)
			if (isAABBInFluid(worldObj, axisalignedbb))
			{
				d0 += 1.0 / b0.toDouble
				if (isImmuneToFire && !isBoatEmpty && worldObj.isAABBInMaterial(axisalignedbb, Material.lava))
					riddenByEntity.extinguish
			}
		}
		val d10 = Math.sqrt(motionX * motionX + motionZ * motionZ)
		var d2 = 0.0
		var d4 = 0.0
		var j = 0
		if (d10 > 0.26249999999999996D) {
			d2 = Math cos rotationYaw.toDouble * Math.PI / 180.0D
			d4 = Math sin rotationYaw.toDouble * Math.PI / 180.0D
			j = 0
			while (j.toDouble < 1.0D + d10 * 60.0D) {
				val d5 = (rand.nextFloat * 2.0F - 1.0F).toDouble
				val d6 = (rand.nextInt(2) * 2 - 1).toDouble * 0.7D
				var d8 = 0.0
				var d9 = 0.0
				if (rand.nextBoolean) {
					d8 = posX - d2 * d5 * 0.8D + d4 * d6
					d9 = posZ - d4 * d5 * 0.8D - d2 * d6
					worldObj.spawnParticle("splash", d8, posY - 0.125D, d9, motionX, motionY,
						motionZ)
				}
				else {
					d8 = posX + d2 + d4 * d5 * 0.7D
					d9 = posZ + d4 - d2 * d5 * 0.7D
					worldObj.spawnParticle("splash", d8, posY - 0.125D, d9, motionX, motionY,
						motionZ)
				}
				j = j + 1
			}
		}
		var d11 = 0.0
		var d12 = 0.0
		if (worldObj.isRemote && isBoatEmpty) {
			if (boatPosRotationIncrements > 0) {
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
			else {
				d2 = posX + motionX
				d4 = posY + motionY
				d11 = posZ + motionZ
				setPosition(d2, d4, d11)
				if (onGround) {
					motionX *= 0.5
					motionY *= 0.5
					motionZ *= 0.5
				}
				motionX *= 0.9900000095367432
				motionY *= 0.949999988079071
				motionZ *= 0.9900000095367432
			}
		}
		else {
			if (d0 < 1) {
				d2 = d0 * 2.0 - 1.0
				motionY += 0.03999999910593033 * d2
			}
			else {
				if (motionY < 0) {
					motionY /= 2
				}
				motionY += 0.007000000216066837
			}
			if (riddenByEntity != null && riddenByEntity.isInstanceOf[EntityLivingBase]) {
				val entitylivingbase = riddenByEntity.asInstanceOf[EntityLivingBase]
				val f = riddenByEntity.rotationYaw + -entitylivingbase.moveStrafing * 90.0F
				motionX += -Math.sin((f * Math.PI.toFloat / 180.0F).toDouble) * speedMultiplier *
					entitylivingbase.moveForward.toDouble *
					0.05000000074505806
				motionZ += Math.cos((f * Math.PI.toFloat / 180.0F).toDouble) * speedMultiplier *
					entitylivingbase.moveForward.toDouble *
					0.05000000074505806
			}
			d2 = Math.sqrt(motionX * motionX + motionZ * motionZ)
			if (d2 > 0.35D) {
				d4 = 0.35D / d2
				motionX *= d4
				motionZ *= d4
				d2 = 0.35D
			}
			if (d2 > d10 && speedMultiplier < 0.35) {
				ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
					speedMultiplier + (0.35 - speedMultiplier) / 35.0D,
					"speedMultiplier", "field_70276_b")
				if (speedMultiplier > 0.35)
					ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
						0.35,
						"speedMultiplier", "field_70276_b")
			}
			else {
				ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
					speedMultiplier - (speedMultiplier - 0.07) / 35.0,
					"speedMultiplier", "field_70276_b")
				if (speedMultiplier < 0.07)
					ObfuscationReflectionHelper setPrivateValue(classOf[EntityBoat], this,
						0.07,
						"speedMultiplier", "field_70276_b")
			}
			var l: Int = 0
			l = 0
			while (l < 4) {
				val i1 = MathHelper.floor_double(posX + ((l % 2).toDouble - 0.5) * 0.8)
				j = MathHelper.floor_double(posZ + ((l / 2).toDouble - 0.5) * 0.8)
				for (j1 <- 0 until 2) {
					val k = MathHelper.floor_double(posY) + j1
					val block = worldObj.getBlock(i1, k, j)
					if (block == Blocks.snow_layer) {
						worldObj.setBlockToAir(i1, k, j)
						isCollidedHorizontally = false
					}
					else if (block == Blocks.waterlily) {
						worldObj.func_147480_a(i1, k, j, true)
						isCollidedHorizontally = false
					}
				}
				l = l + 1
			}
			if (onGround) {
				motionX *= 0.5
				motionY *= 0.5
				motionZ *= 0.5
			}
			moveEntity(motionX, motionY, motionZ)
			if (isCollidedHorizontally && d10 > (getMaterial.getCrashResistance * 0.2)) {
				if (!worldObj.isRemote && !isDead) {
					setDead()

					for (l <- 0 until 3)
						func_145778_a(Item.getItemFromBlock(Blocks.planks), 1, 0)
					
					for (l <- 0 until 2)
						func_145778_a(Items.stick, 1, 0)
				}
			}
			else {
				motionX *= 0.9900000095367432
				motionY *= 0.949999988079071
				motionZ *= 0.9900000095367432
			}
			rotationPitch = 0
			d4 = rotationYaw.toDouble
			d11 = prevPosX - posX
			d12 = prevPosZ - posZ
			if (d11 * d11 + d12 * d12 > 0.001)
				d4 = (Math.atan2(d12, d11) * 180 / Math.PI).toFloat.toDouble
			var d7 = MathHelper.wrapAngleTo180_double(d4 - rotationYaw.toDouble)
			if (d7 > 20)
				d7 = 20
			else if (d7 < -20)
				d7 = -20
			rotationYaw = (rotationYaw.toDouble + d7).toFloat
			setRotation(rotationYaw, rotationPitch)
			if (!worldObj.isRemote) {
				val list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224,
					0, 0.20000000298023224))
				if (list != null && !list.isEmpty) {
					for (k1 <- 0 until list.size) {
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

	/**
	 * This function is used by the entity's update code to drop item's on death/
	 *
	 * @param item The item being dropped upon death.
	 * @param count How many of `@link item` are being dropped
	 * @param f How far from death spot should `@link item` be dropped
	 * @return The entity of the item
	 */
	override def func_145778_a(item: Item, count: Int, f: Float): EntityItem = {
		var stack: ItemStack = new ItemStack(item, count)

		if (item == Items.boat)
			stack = getCustomBoat(getMaterialName, getBlockName)
		else if (item == Item.getItemFromBlock(Blocks.planks))
			stack = getMaterial.getItem
		else if (item == Items.stick) {
			stack = getMaterial.getBrokenMaterialStack
		
			if (stack == null && rand.nextBoolean) stack = getMaterial.getItem
		}
		else return super.func_145778_a(item, count, f)

		if (stack != null) entityDropItem(stack, f)
		else null
	}

	/**
	 * This function is called when the player (real of fake) interacts with the boat entity.
	 *
	 * @param player The player attempting to interact with the entity.
	 * @return Boolean stating if the player's attempt are successful.
	 */
	override def interactFirst(player: EntityPlayer): Boolean = {
		if (player.getCurrentEquippedItem != null
			&& player.getCurrentEquippedItem.getItem == Items.name_tag
			&& player.getCurrentEquippedItem.hasDisplayName) {
			setName(player.getCurrentEquippedItem.getDisplayName)
			println("Set the name to " + player.getCurrentEquippedItem.getDisplayName)
			return false
		}
		
		if (getBlock isRideable) return super.interactFirst(player)
		
		if (player.isSneaking || !getBlock.interact(player, this)) return getMaterial.interact(player, this)
		
		return true
	}

	/**
	 * This function is called when the player attempts to pick block the entity.
	 * As well when mods like WAILA (What am I Looking At?) retrieve information about the entity.
	 *
	 * @param target The entity being talked about.
	 * @return The ItemStack the entity represents.
	 */
	override def getPickedResult(target: MovingObjectPosition) =
		getCustomBoat(getMaterialName, getBlockName)
	
	/**
	 * A setter for the boat's material
	 * @param material the new material
	 */
	def setMaterial(material: String) {
		dataWatcher updateObject(MATERIAL, material)
		isImmuneToFire = Registry.findOfType[modifiers.Material](material) hasAbility "fireResistance"
	}
	
	/**
	 * A setter for the boat's modifier
	 * @param block the new modifier
	 */
	def setBlock(block: String) {
		dataWatcher updateObject(BLOCK, block)
		//Reset it so it gets updated when getBlockData is called again
		blockData = null
	}
	
	def setMount(mount: String, pos: Mountable.Position) =
		getExtendedProperties(ExtendedBoat.NAME).asInstanceOf[ExtendedBoat].setMount(pos, mount)

	def setName(name: String) =
		dataWatcher updateObject(NAME, name)
	
	def hasName = !(dataWatcher getWatchableObjectString NAME).isEmpty

	/**
	 * A getter for the boat's material
	 * @return the boat's material
	 */
	def getMaterial = Registry.findOfType[modifiers.Material](getMaterialName)
	
	/**
	 * A getter for the boat's block
	 * @return the boat's block
	 */
	def getBlock = Registry.findOfType[modifiers.Block](getBlockName)
	
	def getMount(pos: Mountable.Position) = Registry.findOfType[Mountable](getMountName(pos))
	
	def hasMount(pos: Mountable.Position) = Registry.isRegisteredMountable(getMountName(pos))

	/**
	 * A getter for the name of the boat's material
	 * @return the name of the boat's material
	 */
	def getMaterialName = dataWatcher getWatchableObjectString MATERIAL

	/**
	 * A getter for the name of the boat's block
	 * @return the name of the boat's block
	 */
	def getBlockName = dataWatcher getWatchableObjectString BLOCK
	
	def getMountName(pos: Mountable.Position) =
		getExtendedProperties(ExtendedBoat.NAME).asInstanceOf[ExtendedBoat].getMount(pos)

	def getName = dataWatcher getWatchableObjectString NAME

	private var blockData: AnyRef = null

	def getBlockData: AnyRef = {
		if (blockData == null) blockData = getBlock getBlockData this
		return blockData
	}
	
	def getBlockDataWithType[T] = getBlockData.asInstanceOf[T]
	
	def getSpeedMultiplier = getMaterial.getSpeedMultiplier * getBlock.getSpeedMultiplier
	
	def getCrashResistance = getMaterial.getCrashResistance * getBlock.getCrashResistance
}

object EntityCustomBoat {
	
	private val MATERIAL = 20
	private val BLOCK = 21
	private val NAME = 22

	private[api] object NoInventory extends IInventory {
		override def getSizeInventory = 0

		override def getStackInSlot(slot: Int): ItemStack = null

		override def decrStackSize(slot: Int, count: Int): ItemStack = null

		override def getStackInSlotOnClosing(slot: Int): ItemStack = null

		override def setInventorySlotContents(slot: Int, stack: ItemStack) {}

		override def getInventoryName = ""

		override def hasCustomInventoryName = false

		override def getInventoryStackLimit = 0

		override def markDirty() {}

		override def isUseableByPlayer(player: EntityPlayer) = false

		override def openInventory() {}

		override def closeInventory() {}

		override def isItemValidForSlot(slot: Int, stack: ItemStack) = false
	}

	private[EntityCustomBoat] def isAABBInFluid(world: World, aabb: AxisAlignedBB): Boolean = {
		val i = MathHelper.floor_double(aabb.minX)
		val j = MathHelper.floor_double(aabb.maxX + 1)
		val k = MathHelper.floor_double(aabb.minY)
		val l = MathHelper.floor_double(aabb.maxY + 1)
		val i1 = MathHelper.floor_double(aabb.minZ)
		val j1 = MathHelper.floor_double(aabb.maxZ + 1)
		
		for (k1 <- i until j)
		{
			for (l1 <- k until l)
			{
				for (i2 <- i1 until j1)
				{
					val block = world.getBlock(k1, l1, i2)

					if (block.getMaterial isLiquid)
					{
						val j2 = world.getBlockMetadata(k1, l1, i2)
						var d0 = (l1 + 1).toDouble

						if (j2 < 8)
						{
							d0 = l1 + 1.0 - j2.toDouble / 8.0D
						}

						if (d0 >= aabb.minY)
						{
							return true
						}
					}
				}
			}
		}
		
		return false
	}
}