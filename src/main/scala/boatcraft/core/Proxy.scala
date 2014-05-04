package boatcraft.core

import cpw.mods.fml.client.registry.RenderingRegistry
import cpw.mods.fml.common.registry.EntityRegistry
import boatcraft.api.boat.{EntityCustomBoat, RenderCustomBoat}
import net.minecraftforge.client.MinecraftForgeClient
import boatcraft.api.boat.ItemCustomBoat

object Proxy {

  class CommonProxy {
    def registerRenders() {}

    def registerEntities() {
      EntityRegistry.registerModEntity(classOf[EntityCustomBoat],
        "customBoat", 0, BoatCraft, 66, 10, true)
    }
  }

  class ClientProxy extends CommonProxy {
    override def registerRenders() {
      BoatCraft.log info "Registering Renders"

      RenderingRegistry registerEntityRenderingHandler(classOf[EntityCustomBoat],
        new RenderCustomBoat)

      MinecraftForgeClient registerItemRenderer(ItemCustomBoat, new RenderCustomBoat)
    }
  }

}