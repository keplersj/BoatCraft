package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.BoatCraft
import k2b6s9j.BoatCraft.boat.wood._

object EntityRegistry {

    val mod:BoatCraft = mod

    def RegisterEntities() {
      int.id = 1
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new oak.Empty.Entity, "Oak Wood Boat", id, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new oak.Chest.Entity, "Oak Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new oak.Furnace.Entity, "Oak Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new oak.Hopper.Entity, "Oak Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new oak.Hopper.Entity, "Oak Wood TNT Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new spruce.Empty.Entity, "Spruce Wood Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new spruce.Chest.Entity, "Spruce Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new spruce.Furnace.Entity, "Spruce Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new spruce.Hopper.Entity, "Spruce Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new spruce.TNT.Entity, "Spruce Wood TNT Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new birch.Empty.Entity, "Birch Wood Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new birch.Chest.Entity, "Birch Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new birch.Furnace.Entity, "Birch Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new birch.Hopper.Entity, "Birch Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new birch.TNT.Entity, "Birch Wood TNT Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new jungle.Empty.Entity, "Jungle Wood Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new jungle.Chest.Entity, "Jungle Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new jungle.Furnace.Entity, "Jungle Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new jungle.Hopper.Entity, "Jungle Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(new jungle.TNT.Entity, "Jungle Wood TNT Boat", id++, mod, 80, 3, true)
    }
}
