package k2b6s9j.BoatCraft.proxy;

import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirch;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchChest;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungle;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleChest;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOak;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakChest;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakTNT;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruce;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceChest;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceFurnace;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceHopper;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceTNT;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirch;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchChest;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchHopper;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirchTNT;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungle;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleChest;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleHopper;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.BoatJungleTNT;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOak;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakChest;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakHopper;
import k2b6s9j.BoatCraft.item.boat.wood.oak.BoatOakTNT;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruce;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceChest;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceFurnace;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceHopper;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceTNT;
import k2b6s9j.BoatCraft.render.boat.wood.birch.RenderBirchChestBoat;
import k2b6s9j.BoatCraft.render.boat.wood.birch.RenderBirchFurnaceBoat;
import k2b6s9j.BoatCraft.render.boat.wood.birch.RenderBirchHopperBoat;
import k2b6s9j.BoatCraft.render.boat.wood.birch.RenderBirchTNTBoat;
import k2b6s9j.BoatCraft.render.boat.wood.birch.RenderBirchWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.RenderJungleChestBoat;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.RenderJungleFurnaceBoat;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.RenderJungleHopperBoat;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.RenderJungleTNTBoat;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.RenderJungleWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakChestBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakFurnaceBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakHopperBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakTNTBoat;
import k2b6s9j.BoatCraft.render.boat.wood.oak.RenderOakWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.RenderSpruceChestBoat;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.RenderSpruceFurnaceBoat;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.RenderSpruceHopperBoat;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.RenderSpruceTNTBoat;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.RenderSpruceWoodBoat;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
		//Wood Boat Renders
		///Oak Wood Based Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOak.class, new RenderOakWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakChest.class, new RenderOakChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakFurnace.class, new RenderOakFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakTNT.class, new RenderOakTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakHopper.class, new RenderOakHopperBoat());
		///Spruce Wood Based Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruce.class, new RenderSpruceWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceChest.class, new RenderSpruceChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceFurnace.class, new RenderSpruceFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceTNT.class, new RenderSpruceTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceHopper.class, new RenderSpruceHopperBoat());
		///Birch Wood Based Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirch.class, new RenderBirchWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchChest.class, new RenderBirchChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchFurnace.class, new RenderBirchFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchTNT.class, new RenderBirchTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchHopper.class, new RenderBirchHopperBoat());
		///Jungle Wood Based Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle.class, new RenderJungleWoodBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleChest.class, new RenderJungleChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleFurnace.class, new RenderJungleFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleTNT.class, new RenderJungleTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleHopper.class, new RenderJungleHopperBoat());
		
		//Special Boats
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatChest.class, new RenderChestBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, new RenderFurnaceBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatTNT.class, new RenderTNTBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatHopper.class, new RenderHopperBoat());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBuoy.class, new RenderBuoy());
		
		//Register Item Renderers
		//Wood Boat Item Renders
		///Oak Wood Based Boat Items
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderOakWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakChest.shiftedID, new RenderOakChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakFurnace.shiftedID, new RenderOakFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakTNT.shiftedID, new RenderOakTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatOakHopper.shiftedID, new RenderOakHopperBoat());
		///Spruce Wood Based Boat Items
		MinecraftForgeClient.registerItemRenderer(BoatSpruce.shiftedID, new RenderSpruceWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruceChest.shiftedID, new RenderSpruceChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruceFurnace.shiftedID, new RenderSpruceFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruceTNT.shiftedID, new RenderSpruceTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatSpruceHopper.shiftedID, new RenderSpruceHopperBoat());
		///Birch Wood Based Boat Items
		MinecraftForgeClient.registerItemRenderer(BoatBirch.shiftedID, new RenderBirchWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirchChest.shiftedID, new RenderBirchChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirchFurnace.shiftedID, new RenderBirchFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirchTNT.shiftedID, new RenderBirchTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatBirchHopper.shiftedID, new RenderBirchHopperBoat());
		///Jungle Wood Based Boat Items
		MinecraftForgeClient.registerItemRenderer(BoatJungle.shiftedID, new RenderJungleWoodBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungleChest.shiftedID, new RenderJungleChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungleFurnace.shiftedID, new RenderJungleFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungleTNT.shiftedID, new RenderJungleTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatJungleHopper.shiftedID, new RenderJungleHopperBoat());
		
		MinecraftForgeClient.registerItemRenderer(BoatChest.shiftedID, new RenderChestBoat());
		MinecraftForgeClient.registerItemRenderer(BoatFurnace.shiftedID, new RenderFurnaceBoat());
		MinecraftForgeClient.registerItemRenderer(BoatTNT.shiftedID, new RenderTNTBoat());
		MinecraftForgeClient.registerItemRenderer(BoatHopper.shiftedID, new RenderHopperBoat());
		
		MinecraftForgeClient.registerItemRenderer(Buoy.shiftedID, new RenderBuoy());
	}

}
