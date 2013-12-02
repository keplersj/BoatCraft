package k2b6s9j.BoatCraft

import cpw.mods.fml.client.registry.RenderingRegistry

import k2b6s9j.BoatCraft.boat.wood._

import net.minecraftforge.client.MinecraftForgeClient

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
      RenderingRegistry.registerEntityRenderingHandler(new oak.Empty.Entity.type, new oak.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(new oak.Chest.Entity.type, new oak.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(new oak.Furnace.Entity.type , new oak.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(new oak.TNT.Entity.type, new oak.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(new oak.Hopper.Entity.type , new oak.Hopper.Render())
      ///Spruce Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(new spruce.Empty.Entity.type , new spruce.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(new spruce.Chest.Entity.type , new spruce.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(new spruce.Furnace.Entity.type , new spruce.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(new spruce.TNT.Entity.type , new spruce.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(new spruce.Hopper.Entity.type , new spruce.Hopper.Render())
      ///Birch Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(new birch.Empty.Entity.type , new birch.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(new birch.Chest.Entity.type , new birch.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(new birch.Furnace.Entity.type , new birch.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(new birch.TNT.Entity.type , new birch.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(new birch.Hopper.Entity.type , new birch.Hopper.Render())
      ///Jungle Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(new jungle.Empty.Entity.type , new jungle.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(new jungle.Chest.Entity.type , new jungle.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(new jungle.Furnace.Entity.type , new jungle.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(new jungle.TNT.Entity.type , new jungle.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(new jungle.Hopper.Entity.type , new jungle.Hopper.Render())

      //Wood Boat Item Renders
      ///Oak Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(new oak.Empty.Item.shiftedID, new oak.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(new oak.Chest.Item.shiftedID, new oak.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(new oak.Furnace.Item.shiftedID, new oak.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(new oak.TNT.Item.shiftedID, new oak.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(new oak.Hopper.Item.shiftedID, new oak.Hopper.Render())
      ///Spruce Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(new spruce.Empty.Item.shiftedID, new spruce.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(new spruce.Chest.Item.shiftedID, new spruce.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(new spruce.Furnace.Item.shiftedID, new spruce.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(new spruce.TNT.Item.shiftedID, new spruce.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(new spruce.Hopper.Item.shiftedID, new spruce.Hopper.Render())
      ///Birch Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(new birch.Empty.Item.shiftedID, new birch.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(new birch.Chest.Item.shiftedID, new birch.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(new birch.Furnace.Item.shiftedID, new birch.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(new birch.TNT.Item.shiftedID, new birch.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(new birch.Hopper.Item.shiftedID, new birch.Hopper.Render())
      ///Jungle Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(new jungle.Empty.Item.shiftedID, new jungle.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(new jungle.Chest.Item.shiftedID, new jungle.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(new jungle.Furnace.Item.shiftedID, new jungle.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(new jungle.TNT.Item.shiftedID, new jungle.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(new jungle.Hopper.Item.shiftedID, new jungle.Hopper.Render())
    }
  }
}