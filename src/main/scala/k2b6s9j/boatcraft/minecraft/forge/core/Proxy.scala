package k2b6s9j.boatcraft.core

import cpw.mods.fml.client.registry.RenderingRegistry
import net.minecraftforge.client.MinecraftForgeClient

object Proxy {
	class CommonProxy {
		def registerRenderers() {
		}
	}

	class ClientProxy extends CommonProxy
	{
		override def registerRenderers()
		{
			RenderingRegistry registerEntityRenderingHandler(classOf[Boat.EntityCustomBoat],
					new Boat.RenderCustomBoat())
			MinecraftForgeClient registerItemRenderer(BoatCraft.itemBoat, new Boat.RenderCustomBoat)
		}
	}
}