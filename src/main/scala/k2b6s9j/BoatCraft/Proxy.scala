package k2b6s9j.BoatCraft

import k2b6s9j.BoatCraft.render.boat.wood.oak._
import k2b6s9j.BoatCraft.render.boat.wood.spruce._
import k2b6s9j.BoatCraft.render.boat.wood.birch._
import k2b6s9j.BoatCraft.render.boat.wood.jungle._
import cpw.mods.fml.client.registry.RenderingRegistry
import k2b6s9j.BoatCraft.entity.boat.wood.oak._
import k2b6s9j.BoatCraft.entity.boat.wood.spruce._
import k2b6s9j.BoatCraft.entity.boat.wood.birch._
import k2b6s9j.BoatCraft.entity.boat.wood.jungle._
import net.minecraftforge.client.MinecraftForgeClient
import k2b6s9j.BoatCraft.item.boat.wood.oak._
import k2b6s9j.BoatCraft.item.boat.wood.spruce._
import k2b6s9j.BoatCraft.item.boat.wood.birch._
import k2b6s9j.BoatCraft.item.boat.wood.jungle._

object Proxy {
  class CommonProxy {
    def registerRenderers() {
    }
  }

  class ClientProxy extends CommonProxy {
    @Override
    def registerRenderers() {
      //Wood Boat Renders
      ///Oak Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatOak, new RenderOakWoodBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakChest, new RenderOakChestBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakFurnace, new RenderOakFurnaceBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakTNT, new RenderOakTNTBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatOakHopper, new RenderOakHopperBoat())
      ///Spruce Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruce, new RenderSpruceWoodBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceChest, new RenderSpruceChestBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceFurnace, new RenderSpruceFurnaceBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceTNT, new RenderSpruceTNTBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatSpruceHopper, new RenderSpruceHopperBoat())
      ///Birch Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirch, new RenderBirchWoodBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchChest, new RenderBirchChestBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchFurnace, new RenderBirchFurnaceBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchTNT, new RenderBirchTNTBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatBirchHopper, new RenderBirchHopperBoat())
      ///Jungle Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungle, new RenderJungleWoodBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleChest, new RenderJungleChestBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleFurnace, new RenderJungleFurnaceBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleTNT, new RenderJungleTNTBoat())
      RenderingRegistry.registerEntityRenderingHandler(EntityBoatJungleHopper, new RenderJungleHopperBoat())

      //Wood Boat Item Renders
      ///Oak Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(BoatOak.shiftedID, new RenderOakWoodBoat())
      MinecraftForgeClient.registerItemRenderer(BoatOakChest.shiftedID, new RenderOakChestBoat())
      MinecraftForgeClient.registerItemRenderer(BoatOakFurnace.shiftedID, new RenderOakFurnaceBoat())
      MinecraftForgeClient.registerItemRenderer(BoatOakTNT.shiftedID, new RenderOakTNTBoat())
      MinecraftForgeClient.registerItemRenderer(BoatOakHopper.shiftedID, new RenderOakHopperBoat())
      ///Spruce Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(BoatSpruce.shiftedID, new RenderSpruceWoodBoat())
      MinecraftForgeClient.registerItemRenderer(BoatSpruceChest.shiftedID, new RenderSpruceChestBoat())
      MinecraftForgeClient.registerItemRenderer(BoatSpruceFurnace.shiftedID, new RenderSpruceFurnaceBoat())
      MinecraftForgeClient.registerItemRenderer(BoatSpruceTNT.shiftedID, new RenderSpruceTNTBoat())
      MinecraftForgeClient.registerItemRenderer(BoatSpruceHopper.shiftedID, new RenderSpruceHopperBoat())
      ///Birch Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(BoatBirch.shiftedID, new RenderBirchWoodBoat())
      MinecraftForgeClient.registerItemRenderer(BoatBirchChest.shiftedID, new RenderBirchChestBoat())
      MinecraftForgeClient.registerItemRenderer(BoatBirchFurnace.shiftedID, new RenderBirchFurnaceBoat())
      MinecraftForgeClient.registerItemRenderer(BoatBirchTNT.shiftedID, new RenderBirchTNTBoat())
      MinecraftForgeClient.registerItemRenderer(BoatBirchHopper.shiftedID, new RenderBirchHopperBoat())
      ///Jungle Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(BoatJungle.shiftedID, new RenderJungleWoodBoat())
      MinecraftForgeClient.registerItemRenderer(BoatJungleChest.shiftedID, new RenderJungleChestBoat())
      MinecraftForgeClient.registerItemRenderer(BoatJungleFurnace.shiftedID, new RenderJungleFurnaceBoat())
      MinecraftForgeClient.registerItemRenderer(BoatJungleTNT.shiftedID, new RenderJungleTNTBoat())
      MinecraftForgeClient.registerItemRenderer(BoatJungleHopper.shiftedID, new RenderJungleHopperBoat())
    }
  }
}