package k2b6s9j.boatcraft.core

import java.lang.String
import java.util.List
import cpw.mods.fml.relauncher.SideOnly
import k2b6s9j.boatcraft.core.traits.Material
import k2b6s9j.boatcraft.core.traits.Modifier
import k2b6s9j.boatcraft.core.registry.MaterialRegistry
import k2b6s9j.boatcraft.core.registry.ModifierRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderBoat
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemBoat
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.MathHelper
import net.minecraft.util.MovingObjectPosition
import net.minecraft.util.Vec3
import net.minecraft.world.World
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.ItemRenderType
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper
import cpw.mods.fml.relauncher.Side
import org.lwjgl.opengl.GL11

object Boat {
	class ItemCustomBoat extends ItemBoat
	{
		hasSubtypes = true;
		
		@SideOnly(Side.CLIENT)
		override def func_150895_a(item: Item, tab: CreativeTabs, list: List[_])
		{
			for (i: Int <- 0 until MaterialRegistry.materials.length)
				for (j: Int <- 0 until ModifierRegistry.modifiers.length)
					list.asInstanceOf[List[ItemStack]] add new ItemStack(item, 1, i << 8 | j)
		}

		override def getUnlocalizedName(stack: ItemStack): String =
			"boat." +
				MaterialRegistry.getMaterial(stack.getItemDamage()) +
				"." +
				ModifierRegistry.getModifier(stack.getItemDamage())

		override def getItemStackDisplayName(stack: ItemStack): String =
			MaterialRegistry.getMaterial(stack.getItemDamage()).name + " " +
				ModifierRegistry.getModifier(stack.getItemDamage()).name +
				" Boat"

		override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack =
			{
				val f: Float = 1.0F
				val f1: Float = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f
				val f2: Float = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f
				val d0: Double = player.prevPosX + (player.posX - player.prevPosX) * f.asInstanceOf[Double]
				val d1: Double = player.prevPosY + (player.posY - player.prevPosY) * f.asInstanceOf[Double] + 1.62D - player.yOffset.asInstanceOf[Double]
				val d2: Double = player.prevPosZ + (player.posZ - player.prevPosZ) * f.asInstanceOf[Double]
				val vec3: Vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2)
				val f3: Float = MathHelper.cos(-f2 * 0.017453292F - Math.PI.asInstanceOf[Float])
				val f4: Float = MathHelper.sin(-f2 * 0.017453292F - Math.PI.asInstanceOf[Float])
				val f5: Float = -MathHelper.cos(-f1 * 0.017453292F)
				val f6: Float = MathHelper.sin(-f1 * 0.017453292F)
				val f7: Float = f4 * f5
				val f8: Float = f3 * f5
				val d3: Double = 5.0D
				val vec31: Vec3 = vec3.addVector(f7.asInstanceOf[Double] * d3, f6.asInstanceOf[Double] * d3, f8.asInstanceOf[Double] * d3)
				val movingobjectposition: MovingObjectPosition = world.clip(vec3, vec31, true)
				if (movingobjectposition == null)
					return stack
				else {
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

							val entityboat: EntityCustomBoat = EntityCustomBoat(world, i + 0.5, j + 1.0, k + 0.5,
								MaterialRegistry.getMaterial(stack.getItemDamage()), ModifierRegistry.getModifier(stack.getItemDamage()))
							entityboat.rotationYaw = ((MathHelper.floor_double(player.rotationYaw * 4.0 / 360.0 + 0.5) & 3) - 1) * 90

							if (!world.getCollidingBoundingBoxes(entityboat, entityboat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
								return stack

							if (!world.isRemote)
								world.spawnEntityInWorld(entityboat)

							if (!player.capabilities.isCreativeMode)
								stack.stackSize = stack.stackSize - 1
						}

						return stack
					}
				}
			}
	}

	case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double,
		material: Material, modifier: Modifier) extends EntityBoat(world, x, y, z)

	class EntityBoatContainer(world: World, x: Double, y: Double, z: Double,
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
	}

	class RenderCustomBoat
		extends RenderBoat with IItemRenderer
	{
		override def doRender(entity: Entity, x: Double, y: Double, z: Double, p5: Float, p6: Float) =
			doRender(entity.asInstanceOf[EntityCustomBoat], x, y, z, p5, p6)

		def doRender(boat: EntityCustomBoat, x: Double, y: Double, z: Double, par8: Float, par9: Float)
		{
			BoatCraft.log info "rendering"
			
			GL11 glPushMatrix()
			GL11 glTranslatef(x.asInstanceOf[Float], y.asInstanceOf[Float], z.asInstanceOf[Float])
			GL11 glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F)
			var f2: Float = boat.getTimeSinceHit() - par9
			var f3: Float = boat.getDamageTaken() - par9

			if (f3 < 0.0F)
				f3 = 0.0F

			if (f2 > 0.0F)
				GL11 glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection(), 1.0F, 0.0F, 0.0F)

			val f4 = 0.75F
			GL11 glScalef(f4, f4, f4)
			GL11 glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
			this bindEntityTexture boat
			GL11 glScalef(-1.0F, -1.0F, 1.0F)
			modelBoat render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
			GL11 glPopMatrix
		}

		override def getEntityTexture(entity: Entity): ResourceLocation =
			entity.asInstanceOf[EntityCustomBoat].material texture

		def handleRenderType(stack: ItemStack, t: ItemRenderType): Boolean = true

		def shouldUseRenderHelper(renderType: ItemRenderType, stack: ItemStack, helper: ItemRendererHelper): Boolean = true

		def renderItem(renderType: ItemRenderType, stack: ItemStack, objects: AnyRef*)
		{
			GL11 glPushMatrix
			val f4 = 0.75F
			GL11 glScalef(f4, f4, f4)
			GL11 glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
			Minecraft.getMinecraft.getTextureManager bindTexture
				MaterialRegistry.getMaterial(stack.getItemDamage()).texture
			GL11.glScalef(-1.0F, -1.0F, 1.0F)
			modelBoat render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
			GL11 glPopMatrix
		}
	}

}