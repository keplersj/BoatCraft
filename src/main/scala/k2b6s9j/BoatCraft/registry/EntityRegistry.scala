package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.BoatCraft
import k2b6s9j.BoatCraft.boat.wood._

object EntityRegistry {

    def RegisterEntities() {
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[oak.Empty.Entity], "Oak Wood Boat", 1, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[oak.Chest.Entity], "Oak Wood Chest Boat", 2, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[oak.Furnace.Entity], "Oak Wood Furnace Boat", 3, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[oak.Hopper.Entity], "Oak Wood Hopper Boat", 4, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[oak.Hopper.Entity], "Oak Wood TNT Boat", 5, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[spruce.Empty.Entity], "Spruce Wood Boat", 6, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[spruce.Chest.Entity], "Spruce Wood Chest Boat", 7, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[spruce.Furnace.Entity], "Spruce Wood Furnace Boat", 8, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[spruce.Hopper.Entity], "Spruce Wood Hopper Boat", 9, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[spruce.TNT.Entity], "Spruce Wood TNT Boat", 10, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[birch.Empty.Entity], "Birch Wood Boat", 11, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[birch.Chest.Entity], "Birch Wood Chest Boat", 12, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[birch.Furnace.Entity], "Birch Wood Furnace Boat", 13, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[birch.Hopper.Entity], "Birch Wood Hopper Boat", 14, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[birch.TNT.Entity], "Birch Wood TNT Boat", 15, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[jungle.Empty.Entity], "Jungle Wood Boat", 16, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[jungle.Chest.Entity], "Jungle Wood Chest Boat", 17, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[jungle.Furnace.Entity], "Jungle Wood Furnace Boat", 18, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[jungle.Hopper.Entity], "Jungle Wood Hopper Boat", 19, BoatCraft, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(classOf[jungle.TNT.Entity], "Jungle Wood TNT Boat", 20, BoatCraft, 80, 3, true)
    }
}
