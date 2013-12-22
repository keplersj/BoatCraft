package k2b6s9j.BoatCraft

import cpw.mods.fml.client.registry.RenderingRegistry

import k2b6s9j.BoatCraft.boat.wood._
import k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.wood._

import net.minecraftforge.client.MinecraftForgeClient
import cpw.mods.fml.common.Loader
import k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.wood.greatwood.Empty

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
      MinecraftForgeClient.registerItemRenderer(oak.Empty.Item.shiftedID, new oak.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(oak.Chest.Item.shiftedID, new oak.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(oak.Furnace.Item.shiftedID, new oak.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(oak.TNT.Item.shiftedID, new oak.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(oak.Hopper.Item.shiftedID, new oak.Hopper.Render())
      ///Spruce Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(spruce.Empty.Item.shiftedID, new spruce.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.Chest.Item.shiftedID, new spruce.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.Furnace.Item.shiftedID, new spruce.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.TNT.Item.shiftedID, new spruce.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(spruce.Hopper.Item.shiftedID, new spruce.Hopper.Render())
      ///Birch Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(birch.Empty.Item.shiftedID, new birch.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(birch.Chest.Item.shiftedID, new birch.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(birch.Furnace.Item.shiftedID, new birch.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(birch.TNT.Item.shiftedID, new birch.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(birch.Hopper.Item.shiftedID, new birch.Hopper.Render())
      ///Jungle Wood Based Boat Items
      MinecraftForgeClient.registerItemRenderer(jungle.Empty.Item.shiftedID, new jungle.Empty.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.Chest.Item.shiftedID, new jungle.Chest.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.Furnace.Item.shiftedID, new jungle.Furnace.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.TNT.Item.shiftedID, new jungle.TNT.Render())
      MinecraftForgeClient.registerItemRenderer(jungle.Hopper.Item.shiftedID, new jungle.Hopper.Render())

      if (Loader.isModLoaded("Thaumcraft")) {
        /*
        Wood Boat Rendering
         */

        /*
        Greatwood Boat Rendering
         */
        RenderingRegistry.registerEntityRenderingHandler(classOf[greatwood.Empty.Entity], new greatwood.Empty.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[greatwood.Chest.Entity], new greatwood.Chest.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[greatwood.Furnace.Entity], new greatwood.Furnace.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[greatwood.TNT.Entity], new greatwood.TNT.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[greatwood.Hopper.Entity], new greatwood.Hopper.Render())

        /*
        Silverwood Boat Rendering
         */
        RenderingRegistry.registerEntityRenderingHandler(classOf[silverwood.Empty.Entity], new silverwood.Empty.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[silverwood.Chest.Entity], new silverwood.Chest.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[silverwood.Furnace.Entity], new silverwood.Furnace.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[silverwood.TNT.Entity], new silverwood.TNT.Render())
        RenderingRegistry.registerEntityRenderingHandler(classOf[silverwood.Hopper.Entity], new silverwood.Hopper.Render())

        /*
        Wood Boat Item Rendering
         */

        /*
        Greatwood Boat Item Rendering
         */
        MinecraftForgeClient.registerItemRenderer(greatwood.Empty.Item.item.itemID, new greatwood.Empty.Render())
        MinecraftForgeClient.registerItemRenderer(greatwood.Chest.Item.item.itemID, new greatwood.Chest.Render())
        MinecraftForgeClient.registerItemRenderer(greatwood.Furnace.Item.item.itemID, new greatwood.Furnace.Render())
        MinecraftForgeClient.registerItemRenderer(greatwood.TNT.Item.item.itemID, new greatwood.TNT.Render())
        MinecraftForgeClient.registerItemRenderer(greatwood.Hopper.Item.item.itemID, new greatwood.Hopper.Render())

        /*
        Silverwood Boat Item Rendering
         */
        MinecraftForgeClient.registerItemRenderer(silverwood.Empty.Item.item.itemID, new silverwood.Empty.Render())
        MinecraftForgeClient.registerItemRenderer(silverwood.Chest.Item.item.itemID, new silverwood.Chest.Render())
        MinecraftForgeClient.registerItemRenderer(silverwood.Furnace.Item.item.itemID, new silverwood.Furnace.Render())
        MinecraftForgeClient.registerItemRenderer(silverwood.TNT.Item.item.itemID, new silverwood.TNT.Render())
        MinecraftForgeClient.registerItemRenderer(silverwood.Hopper.Item.item.itemID, new silverwood.Hopper.Render())

      }
    }
  }
}