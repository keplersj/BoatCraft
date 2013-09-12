package k2b6s9j.BoatCraft.proxy;

import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirch;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungle;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakChest;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOak;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruce;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakChest;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakHopper;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOak;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakTNT;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruce;
import k2b6s9j.BoatCraft.render.boat.wood.birch.RenderBirchWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.RenderJungleWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakChestBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakFurnaceBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakHopperBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakTNTBoat;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.RenderSpruceWoodBoat;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		//Regular Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOak.class, new RenderOakWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakFurnace.class, new RenderOakFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakTNT.class, new RenderOakTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakHopper.class, new RenderOakHopperBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruce.class, new RenderSpruceWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirch.class, new RenderBirchWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle.class, new RenderJungleWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakChest.class, new RenderOakChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderOakWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruce.shiftedID, new RenderSpruceWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirch.shiftedID, new RenderBirchWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungle.shiftedID, new RenderJungleWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakChest.shiftedID, new RenderOakChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakFurnace.shiftedID, new RenderOakFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakTNT.shiftedID, new RenderOakTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakHopper.shiftedID, new RenderOakHopperBoat());
	}

}
