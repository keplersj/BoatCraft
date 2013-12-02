package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.boat.wood._
import net.minecraft.item.Item

object LanguageRegistry {

    def RegisterName() {

        FMLLanguageRegistry.addName(Item.boat, "Vanilla Boat")

        //Boats
        if (!ConfigRegistry.OreDictWoodBoat) {
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Empty.Item, "Oak Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Chest.Item, "Oak Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Furnace.Item, "Oak Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Hopper.Item, "Oak Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.TNT.Item, "Oak Wood TNT Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new spruce.Empty.Item, "Spruce Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new spruce.Chest.Item, "Spruce Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new spruce.Furnace.Item, "Spruce Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new spruce.Hopper.Item, "Spruce Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new spruce.TNT.Item, "Spruce Wood TNT Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new birch.Empty.Item, "Birch Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new birch.Chest.Item, "Birch Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new birch.Furnace.Item, "Birch Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new birch.Hopper.Item, "Birch Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new birch.TNT.Item, "Birch Wood TNT Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new jungle.Empty.Item, "Jungle Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new jungle.Chest.Item, "Jungle Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new jungle.Furnace.Item, "Jungle Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new jungle.Hopper.Item, "Jungle Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new jungle.TNT.Item, "Jungle Wood TNT Boat")
        }
        if (ConfigRegistry.OreDictWoodBoat) {
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Empty.Item, "Wooden Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Chest.Item, "Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Furnace.Item, "Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.Hopper.Item, "Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(new oak.TNT.Item, "TNT Boat")
        }
    }
}
