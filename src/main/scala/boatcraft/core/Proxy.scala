package boatcraft.core

import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.registry.EntityRegistry
import boatcraft.api.boat.{EntityCustomBoat, RenderCustomBoat}
import net.minecraftforge.client.MinecraftForgeClient
import boatcraft.api.boat.ItemCustomBoat
import cpw.mods.fml.common.registry.GameRegistry
import boatcraft.core.blocks.BlockDock
import boatcraft.core.blocks.BlockDockAddon
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