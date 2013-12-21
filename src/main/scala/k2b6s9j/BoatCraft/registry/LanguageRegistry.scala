package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.boat.wood._
import net.minecraft.item.Item

object LanguageRegistry {

    def RegisterName() {

      cpw.mods.fml.common.registry.LanguageRegistry.addName(Item.boat, "Vanilla Boat")

      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[oak.Empty.Item], "Oak Wood Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[oak.Chest.Item], "Oak Wood Chest Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[oak.Furnace.Item], "Oak Wood Furnace Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[oak.Hopper.Item], "Oak Wood Hopper Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[oak.TNT.Item], "Oak Wood TNT Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[spruce.Empty.Item], "Spruce Wood Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[spruce.Chest.Item], "Spruce Wood Chest Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[spruce.Furnace.Item], "Spruce Wood Furnace Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[spruce.Hopper.Item], "Spruce Wood Hopper Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[spruce.TNT.Item], "Spruce Wood TNT Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[birch.Empty.Item], "Birch Wood Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[birch.Chest.Item], "Birch Wood Chest Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[birch.Furnace.Item], "Birch Wood Furnace Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[birch.Hopper.Item], "Birch Wood Hopper Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[birch.TNT.Item], "Birch Wood TNT Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[jungle.Empty.Item], "Jungle Wood Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[jungle.Chest.Item], "Jungle Wood Chest Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[jungle.Furnace.Item], "Jungle Wood Furnace Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[jungle.Hopper.Item], "Jungle Wood Hopper Boat")
      cpw.mods.fml.common.registry.LanguageRegistry.addName(classOf[jungle.TNT.Item], "Jungle Wood TNT Boat")
    }
}
