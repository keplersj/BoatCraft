package k2b6s9j.boatcraft.core

import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.registry.EntityRegistry
import k2b6s9j.boatcraft.api.boat.{EntityCustomBoat, RenderCustomBoat}
import net.minecraftforge.client.MinecraftForgeClient

object Proxy
{
	class CommonProxy
	{
		def registerRenderers {}
		
		def registerEntities
		{
			EntityRegistry.registerModEntity(classOf[EntityCustomBoat],
				"customBoat", 0, BoatCraft, 66, 10, true)
		}
	}
	
	class ClientProxy extends CommonProxy
	{
		override def registerRenderers
		{
			BoatCraft.log info "Registering Renderes"
			
			RenderingRegistry registerEntityRenderingHandler (classOf[EntityCustomBoat],
				new RenderCustomBoat)
			
			MinecraftForgeClient registerItemRenderer (BoatCraft.itemBoat, new RenderCustomBoat)
		}
	}
}