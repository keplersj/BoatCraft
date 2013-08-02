package k2b6s9j.BoatCraft.proxy;

import k2b6s9j.BoatCraft.entity.item.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderCustomBoat;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomBoat.class, new RenderCustomBoat());
	}

}
