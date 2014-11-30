package boatcraft.api.boat

import org.lwjgl.opengl.GL11
import boatcraft.api.Registry
import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.RenderBoat
import net.minecraft.client.renderer.texture.TextureMap
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.entity.Entity
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.MathHelper
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.IItemRenderer
import net.minecraftforge.client.IItemRenderer.ItemRenderType
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper
import boatcraft.api.modifiers.Mountable
import net.minecraft.client.renderer.entity.RenderManager

/**
 * The Render Class used to render dinghies.
 * This includes the deployed entity, the item while held, in inventory, and on display.
 */
class RenderCustomBoat(renderManager: RenderManager) extends RenderBoat(renderManager)
{
	override def doRender(entity: Entity, x: Double, y: Double, z: Double, f0: Float, f1: Float) =
		doRender(entity.asInstanceOf[EntityCustomBoat], x, y, z, f0, f1)

	private def doRender(boat: EntityCustomBoat, x: Double, y: Double, z: Double, f0: Float, f1: Float)
	{
        val blockrendererdispatcher = Minecraft.getMinecraft.getBlockRendererDispatcher
		GL11 glPushMatrix()

		GL11 glTranslated(x, y, z)
		GL11 glRotatef(180F - f0, 0F, 1F, 0F)

		val f2 = boat.getTimeSinceHit - f1
		var f3 = boat.getDamageTaken - f1

		if (f3 < 0)
			f3 = 0
		
		if (f2 > 0)
			GL11 glRotatef(MathHelper.sin(f2) * f2 * f3 / 10F * boat.getForwardDirection,
				1F, 0F, 0F)
		
		val f4 = 0.875F
		
		val blockState = boat.getBlock.getBlock
		
		if (blockState.getBlock.getRenderType != -1)
		{
			GL11 glPushMatrix()
			
			bindTexture(TextureMap.locationBlocksTexture)
			GL11 glScalef(f4, f4, f4)
			
			val f5 = boat getBrightness f1
			
			GL11 glTranslatef(-8F / 16F, -1F / 16F, 8F / 16F)
			
			GL11 glPushMatrix()
			//println("Hi!")
			blockrendererdispatcher.func_175016_a(blockState, f5)
			
			GL11 glPopMatrix()
			GL11 glPopMatrix()
			GL11 glColor4f(1, 1, 1, 1)
		}
		else boat.getBlockData match
		{
			case entity: TileEntity if TileEntityRendererDispatcher.instance hasSpecialRenderer entity =>
				GL11 glPushMatrix()
				GL11 glScalef(f4, f4, f4)
				
				val f5 = boat getBrightness f1
				
				GL11 glTranslatef(-x.toFloat - 0.5F, -y.toFloat + 6F / 16F - 0.5F, -z.toFloat - 0.5F)
				
				TileEntityRendererDispatcher.instance renderTileEntityAt(
					entity,
					x, y, z, f5)
				
				GL11 glPopMatrix()
				GL11 glColor4f(1, 1, 1, 1)
			case _ =>
		}

		GL11 glScalef(f4, f4, f4)
		GL11 glScalef(1F / f4, 1F / f4, 1F / f4)
		this bindEntityTexture boat
		GL11 glScalef(-1, -1, 1)
		modelBoat render(boat, 0, 0, -0.1F, 0, 0, 0.0625F)

		//Render the name
		if (boat hasName) {
			val d0 = boat.lastTickPosX + (boat.posX - boat.lastTickPosX) * f1
			val d1 = boat.lastTickPosY + (boat.posY - boat.lastTickPosY) * f1
			val d2 = boat.lastTickPosZ + (boat.posZ - boat.lastTickPosZ) * f1
			
			println("Rendered name " + boat.getName + " at " + d0 + ", " + d1 + ", " + d2)
			println(boat.getDistanceSqToEntity(renderManager.livingPlayer) + " away from player")
			
			renderLivingLabel(boat, boat.getName, d0, d1, d2, 64)
		}
		
		for (pos <- Mountable.Position.values if boat hasMount pos)
			boat.getMount(pos).getModel render(boat, 0, 0, -0.1F, 0, 0, 0.0625F)

		GL11 glPopMatrix
	}

	override def getEntityTexture(entity: Entity) =
		if (!entity.isInstanceOf[EntityCustomBoat] ||
			entity.asInstanceOf[EntityCustomBoat].getMaterial == null)
			new ResourceLocation("missingno")
		else entity.asInstanceOf[EntityCustomBoat].getMaterial.getTexture
}