package k2b6s9j.BoatCraft.proxy;

import k2b6s9j.BoatCraft.entity.item.EntityBirchWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityBoatChest;
import k2b6s9j.BoatCraft.entity.item.EntityBoatFurnace;
import k2b6s9j.BoatCraft.entity.item.EntityBoatHopper;
import k2b6s9j.BoatCraft.entity.item.EntityBoatTNT;
import k2b6s9j.BoatCraft.entity.item.EntityJungleWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityOakWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntitySpruceWoodBoat;
import k2b6s9j.BoatCraft.item.boat.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.BoatOak;
import k2b6s9j.BoatCraft.item.boat.BoatSpruce;
import k2b6s9j.BoatCraft.render.RenderChestBoat;
import k2b6s9j.BoatCraft.render.RenderCustomBoat;
import k2b6s9j.BoatCraft.render.RenderFurnaceBoat;
import k2b6s9j.BoatCraft.render.RenderHopperBoat;
import k2b6s9j.BoatCraft.render.RenderTNTBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	public static void registerRenderers() {
		//Regular Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityOakWoodBoat.class, new RenderCustomBoat(new ResourceLocation("textures/entity/boat.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntitySpruceWoodBoat.class, new RenderCustomBoat(new ResourceLocation("boatcraft:textures/boats/spruce.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityBirchWoodBoat.class, new RenderCustomBoat(new ResourceLocation("boatcraft:textures/boats/birch.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityJungleWoodBoat.class, new RenderCustomBoat(new ResourceLocation("boatcraft:textures/boats/jungle.png")));
		
		//Special Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatChest.class, new RenderChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, new RenderFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatTNT.class, new RenderTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatHopper.class, new RenderHopperBoat());
		
		//Register Item Renderers
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderCustomBoat(new ResourceLocation("textures/entity/boat.png")));
		MinecraftForgeClient.registerItemRenderer(BoatSpruce.shiftedID, new RenderCustomBoat(new ResourceLocation("textures/entity/boat.png")));
		MinecraftForgeClient.registerItemRenderer(BoatBirch.shiftedID, new RenderCustomBoat(new ResourceLocation("textures/entity/boat.png")));
		MinecraftForgeClient.registerItemRenderer(BoatJungle.shiftedID, new RenderCustomBoat(new ResourceLocation("textures/entity/boat.png")));
	}

}
