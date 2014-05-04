package boatcraft.api.boat

import org.lwjgl.opengl.GL11
import net.minecraftforge.client.IItemRenderer.ItemRenderType
import net.minecraft.util.ResourceLocation
import net.minecraft.entity.Entity
import net.minecraft.tileentity.TileEntity
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraftforge.client.IItemRenderer
import net.minecraft.client.renderer.entity.RenderBoat
import boatcraft.api.Registry
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper
import net.minecraft.util.MathHelper
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.block.Block
import net.minecraft.init.Blocks

/**
 * The Render Class used to render dinghies.
 * This includes the deployed entity, the item while held, in inventory, and on display.
 */
class RenderCustomBoat
	extends RenderBoat with IItemRenderer {
	override def doRender(entity: Entity, x: Double, y: Double, z: Double, f0: Float, f1: Float) =
		doRender(entity.asInstanceOf[EntityCustomBoat], x, y, z, f0, f1)

	def doRender(boat: EntityCustomBoat, x: Double, y: Double, z: Double, f0: Float, f1: Float) {
		GL11 glPushMatrix()

		GL11 glTranslated(x, y, z)
		GL11 glRotatef(180.0F - f0, 0.0F, 1.0F, 0.0F)

		val f2: Float = boat.getTimeSinceHit - f1
		var f3: Float = boat.getDamageTaken - f1

		if (f3 < 0.0F)
			f3 = 0.0F

		if (f2 > 0.0F)
			GL11 glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection,
				1.0F, 0.0F, 0.0F)

		val f4 = 0.75F

		val block = boat.getBlock.getBlock
		val meta = boat.getBlock.getMeta

		if (block.getRenderType != -1) {
			GL11 glPushMatrix()

			bindTexture(TextureMap.locationBlocksTexture)
			GL11 glScalef(f4, f4, f4)

			val f5 = boat getBrightness f1

			GL11 glTranslatef(0, 6F / 16F, 0)

			GL11 glPushMatrix()

			field_147909_c renderBlockAsItem(block, meta, f5)

			GL11 glPopMatrix()
			GL11 glPopMatrix()
			GL11 glColor4f(1, 1, 1, 1)
		}
		else boat.getInventory match {
			case entity: TileEntity if TileEntityRendererDispatcher.instance hasSpecialRenderer
				entity =>
				GL11 glPushMatrix()
				GL11 glScalef(f4, f4, f4)

				val f5 = boat getBrightness f1

				GL11 glTranslatef(-0.5F, 6F / 16F - 0.5F, 0)
				GL11 glPushMatrix()

				TileEntityRendererDispatcher.instance renderTileEntityAt(
					entity,
					0, 0, 0, f5)

				GL11 glPopMatrix()
				GL11 glPopMatrix()
				GL11 glColor4f(1, 1, 1, 1)
			case _ =>
		}

		GL11 glScalef(f4, f4, f4)
		GL11 glScalef(1F / f4, 1F / f4, 1F / f4)
		this bindEntityTexture boat
		GL11 glScalef(-1, -1, 1)
		modelBoat render(boat, 0, 0, -0.1F, 0, 0F, 0.0625F)

		//Render the name
		if (boat.hasName) {
			val d0 = boat.lastTickPosX + (boat.posX - boat.lastTickPosX) * f1
			val d1 = boat.lastTickPosY + (boat.posY - boat.lastTickPosY) * f1
			val d2 = boat.lastTickPosZ + (boat.posZ - boat.lastTickPosZ) * f1

			println("Rendered name " + boat.getName + " at " + d0 + ", " + d1 + ", " + d2)
			println(boat.getDistanceSqToEntity(renderManager.livingPlayer) + " away from player")

			func_147906_a(boat, boat.getName, d0, d1, d2, 64)
		}

		GL11 glPopMatrix()
	}

	override def getEntityTexture(entity: Entity) =
		if (!entity.isInstanceOf[EntityCustomBoat] ||
			entity.asInstanceOf[EntityCustomBoat].getMaterial == null)
			new ResourceLocation("missingno")
		else entity.asInstanceOf[EntityCustomBoat].getMaterial.getTexture

	def handleRenderType(stack: ItemStack, t: ItemRenderType) = true

	def shouldUseRenderHelper(renderType: ItemRenderType, stack: ItemStack,
														helper: ItemRendererHelper) = true

	def renderItem(renderType: ItemRenderType, stack: ItemStack, objects: AnyRef*) {
		GL11 glPushMatrix()

		GL11 glTranslatef(0.5F, 0.5F, 0.5F)

		if (renderType == ItemRenderType.ENTITY) {
			GL11 glScalef(0.5F, 0.5F, 0.5F)
			GL11 glTranslatef(-0.5F, 0, -0.5F)
		}

		val f4 = 0.75F

		var block: Block = null
		var meta: Int = 0

		try {
			block = (Registry getBlock stack).getBlock
		} catch {
			case e: Exception =>
				e.printStackTrace()
				block = Blocks.air
		}

		try {
			meta = (Registry getBlock stack).getMeta
		} catch {
			case e: Exception =>
				e.printStackTrace()
				meta = 0
		}

		if (block.getRenderType != -1) {
			GL11 glPushMatrix()

			Minecraft.getMinecraft.getTextureManager bindTexture
				TextureMap.locationBlocksTexture
			GL11 glScalef(f4, f4, f4)

			GL11 glTranslatef(0, 6F / 16F, 0)

			GL11 glPushMatrix()

			field_147909_c renderBlockAsItem(block, meta, 8)
			GL11 glPopMatrix()

			GL11 glPopMatrix()

			GL11 glColor4f(1, 1, 1, 1)
		}

		GL11 glScalef(f4, f4, f4)
		GL11 glScalef(1F / f4, 1F / f4, 1F / f4)

		Minecraft.getMinecraft.getTextureManager bindTexture (
			try {
				(Registry getMaterial stack).getTexture
			} catch {
				case e: Exception => new ResourceLocation("minecraft", "textures/entity/boat.png")
			}
			)

		GL11 glScalef(-1, -1, 1)
		modelBoat render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)

		GL11 glPopMatrix()
	}
}