package k2b6s9j.BoatCraft.proxy;

import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBirchWoodBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityJungleWoodBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatChest;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityOakWoodBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntitySpruceWoodBoat;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakChest;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakHopper;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOak;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakTNT;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruce;
import k2b6s9j.BoatCraft.render.RenderBirchWoodBoat;
import k2b6s9j.BoatCraft.render.RenderChestBoat;
import k2b6s9j.BoatCraft.render.RenderFurnaceBoat;
import k2b6s9j.BoatCraft.render.RenderHopperBoat;
import k2b6s9j.BoatCraft.render.RenderJungleWoodBoat;
import k2b6s9j.BoatCraft.render.RenderOakWoodBoat;
import k2b6s9j.BoatCraft.render.RenderSpruceWoodBoat;
import k2b6s9j.BoatCraft.render.RenderTNTBoat;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		//Regular Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityOakWoodBoat.class, new RenderOakWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpruceWoodBoat.class, new RenderSpruceWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBirchWoodBoat.class, new RenderBirchWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityJungleWoodBoat.class, new RenderJungleWoodBoat());
		
		//Special Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatChest.class, new RenderChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, new RenderFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatTNT.class, new RenderTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatHopper.class, new RenderHopperBoat());
		
		//Register Item Renderers
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderOakWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruce.shiftedID, new RenderSpruceWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirch.shiftedID, new RenderBirchWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungle.shiftedID, new RenderJungleWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakChest.shiftedID, new RenderChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakFurnace.shiftedID, new RenderFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakTNT.shiftedID, new RenderTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakHopper.shiftedID, new RenderHopperBoat());
	}

}
