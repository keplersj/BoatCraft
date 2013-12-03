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
      RenderingRegistry.registerEntityRenderingHandler(classOf[oak.Empty.Entity], new oak.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[oak.Chest.Entity], new oak.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[oak.Furnace.Entity] , new oak.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[oak.TNT.Entity], new oak.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[oak.Hopper.Entity], new oak.Hopper.Render())
      ///Spruce Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(classOf[spruce.Empty.Entity], new spruce.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[spruce.Chest.Entity], new spruce.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[spruce.Furnace.Entity], new spruce.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[spruce.TNT.Entity], new spruce.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[spruce.Hopper.Entity], new spruce.Hopper.Render())
      ///Birch Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(classOf[birch.Empty.Entity], new birch.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[birch.Chest.Entity], new birch.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[birch.Furnace.Entity], new birch.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[birch.TNT.Entity], new birch.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[birch.Hopper.Entity], new birch.Hopper.Render())
      ///Jungle Wood Based Boats
      RenderingRegistry.registerEntityRenderingHandler(classOf[jungle.Empty.Entity], new jungle.Empty.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[jungle.Chest.Entity], new jungle.Chest.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[jungle.Furnace.Entity], new jungle.Furnace.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[jungle.TNT.Entity], new jungle.TNT.Render())
      RenderingRegistry.registerEntityRenderingHandler(classOf[jungle.Hopper.Entity], new jungle.Hopper.Render())

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