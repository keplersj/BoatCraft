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
    override def registerRenderers() {
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
      MinecraftForgeClient.registerItemRenderer(oak.Empty.Item.item.itemID, new oak.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(oak.Chest.Item.item.itemID, new oak.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(oak.Furnace.Item.item.itemID, new oak.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(oak.TNT.Item.item.itemID, new oak.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(oak.Hopper.Item.item.itemID, new oak.Hopper.Render())
      ///Spruce Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(spruce.Empty.Item.item.itemID, new spruce.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.Chest.Item.item.itemID, new spruce.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.Furnace.Item.item.itemID, new spruce.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.TNT.Item.item.itemID, new spruce.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.Hopper.Item.item.itemID, new spruce.Hopper.Render())
      ///Birch Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(birch.Empty.Item.item.itemID, new birch.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(birch.Chest.Item.item.itemID, new birch.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(birch.Furnace.Item.item.itemID, new birch.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(birch.TNT.Item.item.itemID, new birch.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(birch.Hopper.Item.item.itemID, new birch.Hopper.Render())
      ///Jungle Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(jungle.Empty.Item.item.itemID, new jungle.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.Chest.Item.item.itemID, new jungle.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.Furnace.Item.item.itemID, new jungle.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.TNT.Item.item.itemID, new jungle.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.Hopper.Item.item.itemID, new jungle.Hopper.Render())
    }
  }
}