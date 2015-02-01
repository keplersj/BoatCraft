package boatcraft.core

import boatcraft.api.boat.{EntityCustomBoat, ItemCustomBoat, RenderCustomBoat}
import boatcraft.core.blocks.{BlockDock, BlockDockAddon}
import boatcraft.core.blocks.items.ItemDockAddon

object Proxy {

	class CommonProxy {
		def registerBlocks {
            GameRegistry.registerBlock(BlockDock, "dock")
            GameRegistry.registerBlock(BlockDockAddon, classOf[ItemDockAddon], "dockAddon")
		}

		def registerEntities {
			EntityRegistry.registerModEntity(classOf[EntityCustomBoat],
				"customBoat", 0, BoatCraft, 66, 10, true)
		}
		
		def registerRenders {}
	}

	class ClientProxy extends CommonProxy {
		override def registerRenders {
			BoatCraft.log info "Registering Renders"

			RenderingRegistry registerEntityRenderingHandler(classOf[EntityCustomBoat],
				new RenderCustomBoat)

			MinecraftForgeClient registerItemRenderer(ItemCustomBoat, new RenderCustomBoat)
		}
	}

}