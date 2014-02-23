package k2b6s9j.boatcraft.api

import java.lang.String
import java.util.List
import scala.collection.JavaConversions.mapAsScalaMap
import org.lwjgl.opengl.GL11
import cpw.mods.fml.relauncher.{Side, SideOnly}
import k2b6s9j.boatcraft.api.registry.{MaterialRegistry, ModifierRegistry}
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderBoat
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.item.{EntityBoat, EntityItem}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.{Blocks, Items}
import net.minecraft.inventory.IInventory
import net.minecraft.item.{Item, ItemBoat, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.{AxisAlignedBB, MathHelper, MovingObjectPosition, ResourceLocation, Vec3}
import net.minecraft.world.World
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.{ItemRenderType, ItemRendererHelper}
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.tileentity.TileEntity

object Boat
{
	class ItemCustomBoat extends ItemBoat
	{
		hasSubtypes = true;
		
		@SideOnly(Side.CLIENT)
		override def getSubItems(item: Item, tab: CreativeTabs, list: List[_])
		{
			var stack = new ItemStack(item)
			stack.stackTagCompound = new NBTTagCompound
			for ((nameMat, material) <- MaterialRegistry.materials)
			{
				stack.stackTagCompound setString("material", nameMat)
				for ((nameMod, modifier) <- ModifierRegistry.modifiers)
				{
					stack.stackTagCompound setString("modifier", nameMod)
					list.asInstanceOf[List[ItemStack]] add stack.copy
				}
			}
		}

		override def getUnlocalizedName(stack: ItemStack) =
			"boat." +
				MaterialRegistry.getMaterial(stack) +
				"." +
				ModifierRegistry.getModifier(stack)

		override def getItemStackDisplayName(stack: ItemStack) =
			MaterialRegistry.getMaterial(stack).getName + " " +
				ModifierRegistry.getModifier(stack).getName +
				" Dinghy"

		override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack =
		{
			val f: Float = 1.0F
			val f1: Float = player.prevRotationPitch +
							(player.rotationPitch - player.prevRotationPitch) * f
			val f2: Float = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f
			val d0: Double = player.prevPosX + (player.posX - player.prevPosX) * f.asInstanceOf[Double]
			val d1: Double = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset
			val d2: Double = player.prevPosZ + (player.posZ - player.prevPosZ) * f.asInstanceOf[Double]
			val vec3: Vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2)
			val f3: Float = MathHelper.cos(-f2 * 0.017453292F - Math.PI.asInstanceOf[Float])
			val f4: Float = MathHelper.sin(-f2 * 0.017453292F - Math.PI.asInstanceOf[Float])
			val f5: Float = -MathHelper.cos(-f1 * 0.017453292F)
			val f6: Float = MathHelper.sin(-f1 * 0.017453292F)
			val f7: Float = f4 * f5
			val f8: Float = f3 * f5
			val d3: Double = 5.0D
			val vec31: Vec3 = vec3.addVector(f7.asInstanceOf[Double] * d3,
											f6.asInstanceOf[Double] * d3,
											f8.asInstanceOf[Double] * d3)
			val movingobjectposition: MovingObjectPosition =
				world rayTraceBlocks(vec3, vec31, true)
			if (movingobjectposition == null)
				stack
			else
			{
				val vec32: Vec3 = player.getLook(f)
				var flag: Boolean = false
				val f9: Float = 1.0F
				val list: List[_] = world.getEntitiesWithinAABBExcludingEntity(player,
					player.boundingBox addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3)
					expand(f9.asInstanceOf[Double], f9.asInstanceOf[Double], f9.asInstanceOf[Double]))
				var i: Int = 0
				for (i <- 0 until list.size())
				{
					val entity: Entity = list.get(i).asInstanceOf[Entity]

					if (entity.canBeCollidedWith())
					{
						val f10: Float = entity.getCollisionBorderSize()
						val axisalignedbb: AxisAlignedBB = entity.boundingBox.
						expand(f10.asInstanceOf[Double], f10.asInstanceOf[Double], f10.asInstanceOf[Double])

						if (axisalignedbb isVecInside vec3)
							flag = true
					}
				}
				
				if (flag)
					stack
				else
				{
					if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
					{
						i = movingobjectposition.blockX
						var j = movingobjectposition.blockY
						var k = movingobjectposition.blockZ
						
						if (world.getBlock(i, j, k) == Blocks.snow_layer)
							j = j - 1
						
						var boat: EntityCustomBoat = null
						val material = MaterialRegistry getMaterial stack
						val modifier = ModifierRegistry getModifier stack
						
						if (modifier hasInventory)
							boat = new EntityBoatContainer(world, i + 0.5, j + 1.0, k + 0.5)
						else boat = EntityCustomBoat(world, i + 0.5, j + 1.0, k + 0.5)
						boat setMaterial(material toString)
						
						boat setModifier(modifier toString)
						boat.rotationYaw =
							((MathHelper.floor_double(player.rotationYaw * 4.0 / 360.0 + 0.5) & 3) - 1) * 90
						
						if (!world.getCollidingBoundingBoxes(boat,
								boat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty)
							return stack
						
						if (!world.isRemote)
							world spawnEntityInWorld boat
							
						if (!player.capabilities.isCreativeMode)
							stack.stackSize = stack.stackSize - 1
					}
					stack
				}
			}
		}
	}
	
	case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double)
		extends EntityBoat(world, x, y, z)
	{
		def this(world: World) = this(world, 0, 0, 0/*, null, null*/)
		
		override protected def entityInit
		{
			super.entityInit
			dataWatcher addObject(20, "")
			dataWatcher addObject(21, "")
		}
		
		override protected def writeEntityToNBT(tag: NBTTagCompound)
		{
			tag setString("material", getMaterialName)
			tag setString("modifier", getModifierName)
		}

		override protected def readEntityFromNBT(tag: NBTTagCompound)
		{
			setMaterial(tag getString "material")
			setModifier(tag getString "modifier")
		}
		
		override def onUpdate
		{
			super.onUpdate
			
			getModifier update this
		}
		
		override def func_145778_a(item: Item, count: Int, f: Float): EntityItem =
		{
			var stack: ItemStack = new ItemStack(item, count)
			
			if (item == Items.boat)
			{
				stack = new ItemStack(getItemCustomBoat, count)
				stack.stackTagCompound = new NBTTagCompound
				stack.stackTagCompound setString("material", getMaterialName)
				stack.stackTagCompound setString("modifier", getModifierName)
			}
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
		
		override def interactFirst(player: EntityPlayer) =
		{
			if (getModifier isRideable) super.interactFirst(player)
			getModifier interact(player, this)
			true
		}		
		
		override def setDead
		{
			if (!world.isRemote && getModifier.getContent != null)
				entityDropItem(getModifier getContent, 0F)
			super.setDead
		}
		
		def setMaterial(material: String)
		{
			dataWatcher updateObject(20, material)
		}
		
		def setModifier(modifier: String)
		{
			dataWatcher updateObject(21, modifier)
		}
		
		def getMaterial =
			MaterialRegistry getMaterial(dataWatcher getWatchableObjectString 20)
		
		def getModifier =
			ModifierRegistry getModifier(dataWatcher getWatchableObjectString 21)
		
		def getMaterialName =
			dataWatcher getWatchableObjectString 20
		
		def getModifierName =
			dataWatcher getWatchableObjectString 21
	}

	class EntityBoatContainer(world: World, x: Double, y: Double, z: Double)
		extends EntityCustomBoat(world, x, y, z) with IInventory
	{
		def this(world: World) = this(world, 0, 0, 0)
		
		override def getSizeInventory =
			getInventory getSizeInventory
		
		override def getStackInSlot(slot: Int) =
			getInventory getStackInSlot slot
		
		override def decrStackSize(slot: Int, count: Int) =
			getInventory decrStackSize(slot, count)
		
		override def getStackInSlotOnClosing(slot: Int) =
			getInventory getStackInSlotOnClosing slot
		
		override def setInventorySlotContents(slot: Int, stack: ItemStack) =
			getInventory setInventorySlotContents(slot, stack)
		
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
		
		override def setDead
		{
			for (i: Int <- 0 until getSizeInventory)
				if (getStackInSlot(i) != null)
					entityDropItem(getStackInSlotOnClosing(i), 0F)
			super.setDead
		}
		
		override def setModifier(modifier: String)
		{
			val prev = getModifierName
			super.setModifier(modifier)
			
			if (prev == null || prev != modifier || inventory == null)
				inventory = getModifier getInventory this
		}
		
		override protected def writeEntityToNBT(tag: NBTTagCompound)
		{
			super.writeEntityToNBT(tag)
			
			getModifier writeStateToNBT(this, tag)
		}

		override protected def readEntityFromNBT(tag: NBTTagCompound)
		{
			super.readEntityFromNBT(tag)
			
			getModifier readStateFromNBT(this, tag)
		}
		
		private var inventory: IInventory = null
		
		protected[boatcraft] def getInventory =
		{
			if (inventory == null) inventory = getModifier getInventory this
			inventory
		}
		
	}

	class RenderCustomBoat
		extends RenderBoat with IItemRenderer
	{
		override def doRender(entity: Entity, x: Double, y: Double, z: Double, f0: Float, f1: Float) =
			doRender(entity.asInstanceOf[EntityCustomBoat], x, y, z, f0, f1)

		def doRender(boat: EntityCustomBoat, x: Double, y: Double, z: Double, f0: Float, f1: Float)
		{
			GL11 glPushMatrix
			
			GL11 glTranslated(x, y, z)
			GL11 glRotatef(180.0F - f0, 0.0F, 1.0F, 0.0F)
			
			var f2: Float = boat.getTimeSinceHit - f1
			var f3: Float = boat.getDamageTaken - f1
			
			if (f3 < 0.0F)
				f3 = 0.0F
			
			if (f2 > 0.0F)
				GL11 glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection,
						1.0F, 0.0F, 0.0F)
			
			val f4 = 0.75F
			
			val block = boat.getModifier getBlock
			val meta = boat.getModifier getMeta
			
			if (block.getRenderType != -1)
			{
				GL11 glPushMatrix
				
				bindTexture(TextureMap.locationBlocksTexture)
				GL11 glScalef(f4, f4, f4)
				
				val f5 = boat getBrightness f1
				
				GL11 glTranslatef(0.0F, 6F / 16.0F, 0.0F)
				
				GL11 glPushMatrix
				
				field_147909_c renderBlockAsItem(block, meta, f5)
				
        		GL11 glPopMatrix()
				GL11 glPopMatrix()
				GL11 glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
			}
			else if (boat.isInstanceOf[EntityBoatContainer]
				&& boat.asInstanceOf[EntityBoatContainer].getInventory.isInstanceOf[TileEntity]
				&& (TileEntityRendererDispatcher.instance hasSpecialRenderer
					boat.asInstanceOf[EntityBoatContainer].getInventory.asInstanceOf[TileEntity]))
			{
				GL11 glPushMatrix()
				GL11 glScalef(f4, f4, f4)
				
				val f5 = boat getBrightness f1
				
				GL11 glTranslated(-.5, 6/16.-.5, 0)
				GL11 glPushMatrix
				
				TileEntityRendererDispatcher.instance renderTileEntityAt(
						boat.asInstanceOf[EntityBoatContainer].getInventory.asInstanceOf[TileEntity],
						0, 0, 0, f5)
				
        		GL11 glPopMatrix()
				GL11 glPopMatrix()
				GL11 glColor4f(1, 1, 1, 1)
			}
			
			GL11 glScalef(f4, f4, f4)
			GL11 glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
			this bindEntityTexture boat
			GL11 glScalef(-1.0F, -1.0F, 1.0F)
			modelBoat render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
			GL11 glPopMatrix
		}
		
		override def getEntityTexture(entity: Entity) =
			if (!entity.isInstanceOf[EntityCustomBoat] ||
					entity.asInstanceOf[EntityCustomBoat].getMaterial == null)
				new ResourceLocation("missingno")
			else entity.asInstanceOf[EntityCustomBoat].getMaterial.getTexture

		def handleRenderType(stack: ItemStack, t: ItemRenderType) = true

		def shouldUseRenderHelper(renderType: ItemRenderType, stack: ItemStack,
				helper: ItemRendererHelper) = true

		def renderItem(renderType: ItemRenderType, stack: ItemStack, objects: AnyRef*)
		{
			GL11 glPushMatrix
			
			GL11 glTranslated(.5, .5, .5)
			
			if (renderType == ItemRenderType.ENTITY)
			{
				GL11 glScaled(.5, .5, .5)
				GL11 glTranslated(-.5, 0, -.5)
			}
			
			val f4 = .75F
			
			val block = ModifierRegistry getModifier stack getBlock
			val meta = ModifierRegistry getModifier stack getMeta
			
			if (block.getRenderType != -1)
			{
				GL11 glPushMatrix()
				
				Minecraft.getMinecraft.getTextureManager bindTexture
					TextureMap.locationBlocksTexture
				GL11 glScalef(f4, f4, f4)
				
				GL11 glTranslated(0, 6. / 16., 0.)
				
				GL11 glPushMatrix
				
				field_147909_c renderBlockAsItem(block, meta, 8)
        		GL11 glPopMatrix
				
				GL11 glPopMatrix
				
				GL11 glColor4f(1, 1, 1, 1)
			}
			
			GL11 glScalef(f4, f4, f4)
			GL11 glScalef(1F / f4, 1F / f4, 1F / f4)
			
			Minecraft.getMinecraft.getTextureManager bindTexture
				(MaterialRegistry getMaterial stack getTexture)
			
			GL11 glScalef(-1, -1, 1)
			modelBoat render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
			
			GL11 glPopMatrix
		}
	}

}