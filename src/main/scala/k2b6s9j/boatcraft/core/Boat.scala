package k2b6s9j.boatcraft.core

import java.lang.String
import java.util.List

import scala.collection.JavaConversions.mapAsScalaMap

import org.lwjgl.opengl.GL11

import cpw.mods.fml.relauncher.{Side, SideOnly}
import k2b6s9j.boatcraft.core.registry._
import k2b6s9j.boatcraft.core.traits._
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderBoat
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityBoat
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init._
import net.minecraft.item.Item
import net.minecraft.item.ItemBoat
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util._
import net.minecraft.world.World
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer._

object Boat
{
	class ItemCustomBoat extends ItemBoat
	{
		hasSubtypes = true;
		
		@SideOnly(Side.CLIENT)
		override def func_150895_a(item: Item, tab: CreativeTabs, list: List[_])
		{
			var stack: ItemStack = new ItemStack(item)
			stack.stackTagCompound = new NBTTagCompound
			for ((nameMat: String, material: Material) <-
					MaterialRegistry.materials)
			{
				stack.stackTagCompound.setString("material", nameMat)
				for ((nameMod: String, modifier: Modifier) <-
						ModifierRegistry.modifiers)
				{
					stack.stackTagCompound.setString("modifier", nameMod)
					list.asInstanceOf[List[ItemStack]] add stack.copy
				}
			}
		}

		override def getUnlocalizedName(stack: ItemStack): String =
			"boat." +
				MaterialRegistry.getMaterial(stack) +
				"." +
				ModifierRegistry.getModifier(stack)

		override def getItemStackDisplayName(stack: ItemStack): String =
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
			val movingobjectposition: MovingObjectPosition = world clip(vec3, vec31, true)
			if (movingobjectposition == null)
				return stack
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

						if (axisalignedbb.isVecInside(vec3))
							flag = true
					}
				}
				
				if (flag)
					return stack
				else
				{
					if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
						i = movingobjectposition.blockX
						var j: Int = movingobjectposition.blockY
						var k: Int = movingobjectposition.blockZ
						
						if (world.func_147439_a(i, j, k) == Blocks.snow_layer)
							j = j - 1
							
						val boat: EntityCustomBoat = EntityCustomBoat(world, i + 0.5, j + 1.0, k + 0.5)
						boat setMaterial(MaterialRegistry getMaterial stack toString)
						boat setModifier(ModifierRegistry getModifier stack toString)
						boat.rotationYaw =
							((MathHelper.floor_double(player.rotationYaw * 4.0 / 360.0 + 0.5) & 3) - 1) * 90
						
						if (!world.getCollidingBoundingBoxes(boat,
								boat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
							return stack
						
						if (!world.isRemote)
							world spawnEntityInWorld boat
							
						if (!player.capabilities.isCreativeMode)
							stack.stackSize = stack.stackSize - 1
					}
					return stack
				}
			}
		}
	}
	
	case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double)
		extends EntityBoat(world, x, y, z)
	{
		def this(world: World) = this(world, 0, 0, 0/*, null, null*/)
		
		override protected def entityInit()
		{
			super.entityInit
			dataWatcher addObject(20, "")
			dataWatcher addObject(21, "")
		}
		
		override protected def writeEntityToNBT(tag: NBTTagCompound)
		{
			tag setString("material", dataWatcher getWatchableObjectString 20)
			tag setString("modifier", dataWatcher getWatchableObjectString 21)
		}

		override protected def readEntityFromNBT(tag: NBTTagCompound)
		{
			dataWatcher updateObject(20, tag getString("material"))
			dataWatcher updateObject(21, tag getString("modifier"))
		}
		
		override def func_145778_a(item: Item, count: Int, f: Float): EntityItem =
		{
			var stack: ItemStack = new ItemStack(item, count)
			
			if (item == Items.boat)
			{
				stack = new ItemStack(BoatCraft.itemBoat, count)
				stack.stackTagCompound = new NBTTagCompound
				stack.stackTagCompound setString("material", dataWatcher getWatchableObjectString 20)
				stack.stackTagCompound setString("modifier", dataWatcher getWatchableObjectString 21)
			}
			else if (item == Item.func_150898_a(Blocks.planks))
				stack = getMaterial getItem
			else if (item == Items.stick)
				stack = getMaterial getStick
			else return super.func_145778_a(item, count, f)
			
			if (stack != null) entityDropItem(stack, f)
			else null
		}
		
		override def interactFirst(player: EntityPlayer): Boolean =
		{
			if (getModifier containsBlock) true
			super.interactFirst(player)
		}
		
		def setMaterial(material: String)
		{
			dataWatcher updateObject(20, material)
		}
		
		def setModifier(modifier: String)
		{
			dataWatcher updateObject(21, modifier)
		}
		
		def getMaterial(): Material =
			MaterialRegistry getMaterial(dataWatcher getWatchableObjectString 20)
		
		def getModifier(): Modifier =
			ModifierRegistry getModifier(dataWatcher getWatchableObjectString 21)
	}

	/*class EntityBoatContainer(world: World, x: Double, y: Double, z: Double,
		material: Material, modifier: Modifier)
		extends EntityCustomBoat(world, x, y, z, material, modifier) with IInventory {
		def getSizeInventory: Int = 0

		def getStackInSlot(p1: Int): ItemStack = null

		def decrStackSize(p1: Int, p2: Int): ItemStack = null

		def getStackInSlotOnClosing(p1: Int): ItemStack = null

		def setInventorySlotContents(p1: Int, p2: ItemStack) {}

		def func_145825_b(): String = "something"

		def func_145818_k_(): Boolean = true

		def getInventoryStackLimit: Int = 0

		def onInventoryChanged() {}

		def isUseableByPlayer(p1: EntityPlayer): Boolean = true

		def openChest() {}

		def closeChest() = {}

		def isItemValidForSlot(p1: Int, p2: ItemStack): Boolean = true
	}*/

	class RenderCustomBoat
		extends RenderBoat with IItemRenderer
	{
		override def doRender(entity: Entity, x: Double, y: Double, z: Double, f0: Float, f1: Float) =
			doRender(entity.asInstanceOf[EntityCustomBoat], x, y, z, f0, f1)

		def doRender(boat: EntityCustomBoat, x: Double, y: Double, z: Double, f0: Float, f1: Float)
		{
			GL11 glPushMatrix()
			GL11 glTranslatef(x.asInstanceOf[Float], y.asInstanceOf[Float], z.asInstanceOf[Float])
			GL11 glRotatef(180.0F - f0, 0.0F, 1.0F, 0.0F)
			var f2: Float = boat.getTimeSinceHit - f1
			var f3: Float = boat.getDamageTaken - f1

			if (f3 < 0.0F)
				f3 = 0.0F

			if (f2 > 0.0F)
				GL11 glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection,
						1.0F, 0.0F, 0.0F)

			val f4 = 0.75F
			GL11 glScalef(f4, f4, f4)
			GL11 glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
			this bindEntityTexture boat
			GL11 glScalef(-1.0F, -1.0F, 1.0F)
			modelBoat render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
			GL11 glPopMatrix
		}

		override def getEntityTexture(entity: Entity): ResourceLocation =
			entity.asInstanceOf[EntityCustomBoat] getMaterial() getTexture

		def handleRenderType(stack: ItemStack, t: ItemRenderType): Boolean = true

		def shouldUseRenderHelper(renderType: ItemRenderType, stack: ItemStack,
				helper: ItemRendererHelper): Boolean = true

		def renderItem(renderType: ItemRenderType, stack: ItemStack, objects: AnyRef*)
		{
			GL11 glPushMatrix()
			GL11 glTranslatef(.5F, .5F, .5F)
			val f4 = 0.75F
			GL11 glScalef(f4, f4, f4)
			GL11 glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
			Minecraft.getMinecraft.getTextureManager bindTexture
				(MaterialRegistry getMaterial stack getTexture)
			GL11 glScalef(-1.0F, -1.0F, 1.0F)
			modelBoat render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
			GL11 glPopMatrix
		}
	}

}