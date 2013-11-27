package k2b6s9j.BoatCraft.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.acacia.EntityBoatAcacia;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.balsa.EntityBoatBalsa;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.baobob.EntityBoatBaobob;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.cherry.EntityBoatCherry;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.chestnut.EntityBoatChestnut;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.citrus.EntityBoatCitrus;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.ebony.EntityBoatEbony;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.greenheart.EntityBoatGreenheart;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.kapok.EntityBoatKapok;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.larch.EntityBoatLarch;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.lime.EntityBoatLime;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.mahoe.EntityBoatMahoe;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.mahogany.EntityBoatMahogany;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.maple.EntityBoatMaple;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.palm.EntityBoatPalm;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.papaya.EntityBoatPapaya;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.pine.EntityBoatPine;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.plum.EntityBoatPlum;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.poplar.EntityBoatPoplar;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.sequoia.EntityBoatSequoia;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.teak.EntityBoatTeak;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.walnut.EntityBoatWalnut;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.wenge.EntityBoatWenge;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.willow.EntityBoatWillow;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.*;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.*;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.*;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.*;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.acacia.BoatAcacia;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.balsa.BoatBalsa;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.baobob.BoatBaobob;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.cherry.BoatCherry;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.chestnut.BoatChestnut;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.citrus.BoatCitrus;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.ebony.BoatEbony;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.greenheart.BoatGreenheart;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.kapok.BoatKapok;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.lime.BoatLime;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.mahoe.BoatMahoe;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.mahogany.BoatMahogany;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.maple.BoatMaple;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.palm.BoatPalm;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.papaya.BoatPapaya;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.pine.BoatPine;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.plum.BoatPlum;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.poplar.BoatPoplar;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.sequoia.BoatSequoia;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.walnut.BoatWalnut;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.wenge.BoatWenge;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.willow.BoatWillow;
import k2b6s9j.BoatCraft.item.boat.wood.birch.*;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.*;
import k2b6s9j.BoatCraft.item.boat.wood.oak.*;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.*;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.acacia.RenderAcaciaWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.balsa.RenderBalsaWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.baobob.RenderBaobobWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.cherry.RenderCherryWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.chestnut.RenderChestnutWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.citrus.RenderCitrusWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.ebony.RenderEbonyWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.greenheart.RenderGreenheartWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.kapok.RenderKapokWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.larch.RenderLarchWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.lime.RenderLimeWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.mahoe.RenderMahoeWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.mahogany.RenderMahoganyWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.maple.RenderMapleWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.palm.RenderPalmWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.papaya.RenderPapayaWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.pine.RenderPineWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.plum.RenderPlumWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.poplar.RenderPoplarWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.sequoia.RenderSequoiaWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.teak.RenderTeakWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.walnut.RenderWalnutWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.wenge.RenderWengeWoodBoat;
import k2b6s9j.BoatCraft.render.boat.forestry.wood.willow.RenderWillowWoodBoat;
import k2b6s9j.BoatCraft.render.boat.wood.birch.*;
import k2b6s9j.BoatCraft.render.boat.wood.jungle.*;
import k2b6s9j.BoatCraft.render.boat.wood.oak.*;
import k2b6s9j.BoatCraft.render.boat.wood.spruce.*;
import net.minecraftforge.client.MinecraftForgeClient;


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
		///Forestry Wood Boats
		////Larch Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatLarch.class, new RenderLarchWoodBoat());
		////Teak Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatTeak.class, new RenderTeakWoodBoat());
		////Acacia Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatAcacia.class, new RenderAcaciaWoodBoat());
		////Lime Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatLime.class, new RenderLimeWoodBoat());
		////Chestnut Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatChestnut.class, new RenderChestnutWoodBoat());
		////Wenge Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatWenge.class, new RenderWengeWoodBoat());
		////Baobob Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBaobob.class, new RenderBaobobWoodBoat());
		////Sequoia Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatSequoia.class, new RenderSequoiaWoodBoat());
		////Kapok Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatKapok.class, new RenderKapokWoodBoat());
		////Ebony Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatEbony.class, new RenderEbonyWoodBoat());
		////Mahogany Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatMahogany.class, new RenderMahoganyWoodBoat());
		////Balsa Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatBalsa.class, new RenderBalsaWoodBoat());
		////Willow Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatWillow.class, new RenderWillowWoodBoat());
		////Walnut Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatWalnut.class, new RenderWalnutWoodBoat());
		////Greenheart Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatGreenheart.class, new RenderGreenheartWoodBoat());
		////Cherry Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatCherry.class, new RenderCherryWoodBoat());
		////Mahoe Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatMahoe.class, new RenderMahoeWoodBoat());
		////Poplar Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatPoplar.class, new RenderPoplarWoodBoat());
		////Palm Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatPalm.class, new RenderPalmWoodBoat());
		////Papaya Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatPapaya.class, new RenderPapayaWoodBoat());
		////Pine Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatPine.class, new RenderPineWoodBoat());
		////Plum Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatPlum.class, new RenderPlumWoodBoat());
		////Maple Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatMaple.class, new RenderMapleWoodBoat());
		////Citrus Wood Based
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatCitrus.class, new RenderCitrusWoodBoat());
		
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
		///Forestry Wood Boats
		////Larch Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderLarchWoodBoat());
		////Teak Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderTeakWoodBoat());
		////Acacia Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatAcacia.shiftedID, new RenderAcaciaWoodBoat());
		////Lime Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatLime.shiftedID, new RenderLimeWoodBoat());
		////Chestnut Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatChestnut.shiftedID, new RenderChestnutWoodBoat());
		////Wenge Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatWenge.shiftedID, new RenderWengeWoodBoat());
		////Baobob Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatBaobob.shiftedID, new RenderBaobobWoodBoat());
		////Sequoia Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatSequoia.shiftedID, new RenderSequoiaWoodBoat());
		////Kapok Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatKapok.shiftedID, new RenderKapokWoodBoat());
		////Ebony Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatEbony.shiftedID, new RenderEbonyWoodBoat());
		////Mahogany Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatMahogany.shiftedID, new RenderMahoganyWoodBoat());
		////Balsa Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatBalsa.shiftedID, new RenderBalsaWoodBoat());
		////Willow Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatWillow.shiftedID, new RenderWillowWoodBoat());
		////Walnut Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatWalnut.shiftedID, new RenderWalnutWoodBoat());
		////Greenheart Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatGreenheart.shiftedID, new RenderGreenheartWoodBoat());
		////Cherry Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatCherry.shiftedID, new RenderCherryWoodBoat());
		////Mahoe Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatMahoe.shiftedID, new RenderMahoeWoodBoat());
		////Poplar Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatPoplar.shiftedID, new RenderPoplarWoodBoat());
		////Palm Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatPalm.shiftedID, new RenderPalmWoodBoat());
		////Papaya Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatPapaya.shiftedID, new RenderPapayaWoodBoat());
		////Pine Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatPine.shiftedID, new RenderPineWoodBoat());
		////Plum Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatPlum.shiftedID, new RenderPlumWoodBoat());
		////Maple Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatMaple.shiftedID, new RenderMapleWoodBoat());
		////Citrus Wood Based
		MinecraftForgeClient.registerItemRenderer(BoatCitrus.shiftedID, new RenderPlumWoodBoat());
	}

}
