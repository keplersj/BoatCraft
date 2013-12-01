package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.BoatCraft
import k2b6s9j.BoatCraft.entity.boat.wood.birch._
import k2b6s9j.BoatCraft.entity.boat.wood.jungle._
import k2b6s9j.BoatCraft.entity.boat.wood.oak._
import k2b6s9j.BoatCraft.entity.boat.wood.spruce._

object EntityRegistry {

    val mod:BoatCraft = mod

    def RegisterEntities() {
      int.id = 1
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOak, "Oak Wood Boat", id, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakChest, "Oak Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakFurnace, "Oak Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakHopper, "Oak Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakTNT, "Oak Wood TNT Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruce, "Spruce Wood Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceChest, "Spruce Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceFurnace, "Spruce Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceHopper, "Spruce Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceTNT, "Spruce Wood TNT Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirch, "Birch Wood Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchChest, "Birch Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchFurnace, "Birch Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchHopper, "Birch Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchTNT, "Birch Wood TNT Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungle, "Jungle Wood Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleChest, "Jungle Wood Chest Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleFurnace, "Jungle Wood Furnace Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleHopper, "Jungle Wood Hopper Boat", id++, mod, 80, 3, true)
      cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleTNT, "Jungle Wood TNT Boat", id++, mod, 80, 3, true)
    }
}
