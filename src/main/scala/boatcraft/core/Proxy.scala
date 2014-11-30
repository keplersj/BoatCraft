package boatcraft.core

import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.registry.EntityRegistry
import boatcraft.api.boat.{EntityCustomBoat, RenderCustomBoat}
import net.minecraftforge.client.MinecraftForgeClient
import boatcraft.api.boat.ItemCustomBoat
import net.minecraftforge.fml.common.registry.GameRegistry
import boatcraft.core.blocks.BlockDock
import boatcraft.core.blocks.BlockDockAddon
import boatcraft.core.blocks.items.ItemDockAddon
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.Minecraft

object Proxy
{
	class CommonProxy
	{
		def registerBlocks
		{
            GameRegistry.registerBlock(BlockDock, "dock")
            GameRegistry.registerBlock(BlockDockAddon, classOf[ItemDockAddon], "dockAddon")
		}
		
		def registerItems
		{
			GameRegistry.registerItem(ItemCustomBoat, "customBoat")
		}

		def registerEntities
		{
			EntityRegistry.registerModEntity(classOf[EntityCustomBoat],
				"customBoat", 0, BoatCraft, 66, 10, true)
		}
		
		def registerRenders {}
	}

	class ClientProxy extends CommonProxy
	{
		override def registerRenders
		{
			BoatCraft.log info "Registering Renders"

			RenderingRegistry registerEntityRenderingHandler(classOf[EntityCustomBoat],
				new RenderCustomBoat(Minecraft.getMinecraft.getRenderManager))

			//MinecraftForgeClient registerItemRenderer(ItemCustomBoat, new RenderCustomBoat)
		}
	}

}