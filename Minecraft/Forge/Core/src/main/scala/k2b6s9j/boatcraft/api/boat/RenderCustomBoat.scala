package k2b6s9j.boatcraft.api.boat

import org.lwjgl.opengl.GL11
import net.minecraftforge.client.IItemRenderer.ItemRenderType
import net.minecraft.util.ResourceLocation
import net.minecraft.entity.Entity
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.client.IItemRenderer
import net.minecraft.client.renderer.entity.RenderBoat
import k2b6s9j.boatcraft.api.Registry
import net.minecraft.client.Minecraft
import net.minecraft.item.ItemStack
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper
import net.minecraft.util.MathHelper
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.RenderBlocks

/**
  * The Render Class used to render dinghies.
  * This includes the deployed entity, the item while held, in inventory, and on display.
  */
class RenderCustomBoat
	extends RenderBoat with IItemRenderer
{

  protected val renderBlocks: RenderBlocks

  override def doRender(entity: Entity, x: Double, y: Double, z: Double, f0: Float, f1: Float) =
		doRender(entity.asInstanceOf[EntityCustomBoat], x, y, z, f0, f1)
	
	def doRender(boat: EntityCustomBoat, x: Double, y: Double, z: Double, f0: Float, f1: Float)
	{
		GL11 glPushMatrix

		GL11 glTranslated (x, y, z)
		GL11 glRotatef (180.0F - f0, 0.0F, 1.0F, 0.0F)

		var f2: Float = boat.getTimeSinceHit - f1
		var f3: Float = boat.getDamageTaken - f1

		if (f3 < 0.0F)
			f3 = 0.0F

		if (f2 > 0.0F)
			GL11 glRotatef (MathHelper.sin(f2) * f2 * f3 / 10.0F * boat.getForwardDirection,
				1.0F, 0.0F, 0.0F)

		val f4 = 0.75F

		val block = boat.getModifier getBlock
		val meta = boat.getModifier getMeta

		if (block.getRenderType != -1) {
			GL11 glPushMatrix

			bindTexture(TextureMap.locationBlocksTexture)
			GL11 glScalef (f4, f4, f4)

			val f5 = boat getBrightness f1

			GL11 glTranslatef (0.0F, 6F / 16.0F, 0.0F)

			GL11 glPushMatrix

			renderBlocks renderBlockAsItem (block, meta, f5)

			GL11 glPopMatrix ()
			GL11 glPopMatrix ()
			GL11 glColor4f (1.0F, 1.0F, 1.0F, 1.0F)
		}
		else if (boat.isInstanceOf[EntityBoatContainer]
			&& boat.asInstanceOf[EntityBoatContainer].getInventory.isInstanceOf[TileEntity]
			&& (TileEntityRendererDispatcher.instance hasSpecialRenderer
				boat.asInstanceOf[EntityBoatContainer].getInventory.asInstanceOf[TileEntity]))
		{
			GL11 glPushMatrix ()
			GL11 glScalef (f4, f4, f4)

			val f5 = boat getBrightness f1

			GL11 glTranslated (-.5, 6 / 16. - .5, 0)
			GL11 glPushMatrix

			TileEntityRendererDispatcher.instance renderTileEntityAt (
				boat.asInstanceOf[EntityBoatContainer].getInventory.asInstanceOf[TileEntity],
				0, 0, 0, f5)

			GL11 glPopMatrix()
			GL11 glPopMatrix()
			GL11 glColor4f (1, 1, 1, 1)
		}

		GL11 glScalef(f4, f4, f4)
		GL11 glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4)
		this bindEntityTexture boat
		GL11 glScalef(-1.0F, -1.0F, 1.0F)
		modelBoat render (boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)
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

  def renderItem(renderType: ItemRenderType, stack: ItemStack, objects: AnyRef*) {
		GL11 glPushMatrix

		GL11 glTranslated (.5, .5, .5)

		if (renderType == ItemRenderType.ENTITY)
		{
			GL11 glScaled (.5, .5, .5)
			GL11 glTranslated (-.5, 0, -.5)
		}

		val f4 = .75F

		val block = Registry getModifier stack getBlock
		val meta = Registry getModifier stack getMeta

		if (block.getRenderType != -1)
		{
			GL11 glPushMatrix

			Minecraft.getMinecraft.getTextureManager bindTexture
				TextureMap.locationBlocksTexture
			GL11 glScalef (f4, f4, f4)

			GL11 glTranslated (0, 6. / 16., 0.)

			GL11 glPushMatrix

      renderBlocks renderBlockAsItem(block, meta, 8)
			GL11 glPopMatrix

			GL11 glPopMatrix

			GL11 glColor4f (1, 1, 1, 1)
		}

		GL11 glScalef (f4, f4, f4)
		GL11 glScalef (1F / f4, 1F / f4, 1F / f4)

		Minecraft.getMinecraft.getTextureManager bindTexture
			(Registry getMaterial stack getTexture)

		GL11 glScalef (-1, -1, 1)
		modelBoat render (null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F)

		GL11 glPopMatrix
	}
}