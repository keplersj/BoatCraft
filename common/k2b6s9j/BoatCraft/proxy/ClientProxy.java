package k2b6s9j.BoatCraft.proxy;

import java.util.logging.Level;

import k2b6s9j.BoatCraft.entity.item.EntityBirchWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityJungleWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityOakWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntitySpruceWoodBoat;
import k2b6s9j.BoatCraft.render.RenderBirchWoodBoat;
import k2b6s9j.BoatCraft.render.RenderJungleWoodBoat;
import k2b6s9j.BoatCraft.render.RenderOakWoodBoat;
import k2b6s9j.BoatCraft.render.RenderSpruceWoodBoat;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLLog;


public class ClientProxy extends CommonProxy {
	
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityOakWoodBoat.class, new RenderOakWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpruceWoodBoat.class, new RenderSpruceWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBirchWoodBoat.class, new RenderBirchWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityJungleWoodBoat.class, new RenderJungleWoodBoat());
	}

}
