package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.item.boat.wood.birch._
import k2b6s9j.BoatCraft.item.boat.wood.jungle._
import k2b6s9j.BoatCraft.item.boat.wood.oak._
import k2b6s9j.BoatCraft.item.boat.wood.spruce._
import net.minecraft.item.Item

object LanguageRegistry {

    def RegisterName() {

        FMLLanguageRegistry.addName(Item.boat, "Vanilla Boat")

        //Boats
        if (!ConfigRegistry.OreDictWoodBoat) {
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOak, "Oak Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakChest, "Oak Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakFurnace, "Oak Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakHopper, "Oak Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakTNT, "Oak Wood TNT Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruce, "Spruce Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceChest, "Spruce Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceFurnace, "Spruce Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceHopper, "Spruce Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceTNT, "Spruce Wood TNT Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirch, "Birch Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchChest, "Birch Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchFurnace, "Birch Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchHopper, "Birch Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchTNT, "Birch Wood TNT Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungle, "Jungle Wood Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleChest, "Jungle Wood Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleFurnace, "Jungle Wood Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleHopper, "Jungle Wood Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleTNT, "Jungle Wood TNT Boat")
        }
        if (ConfigRegistry.OreDictWoodBoat) {
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOak, "Wooden Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakChest, "Chest Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakFurnace, "Furnace Boat")
          cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakHopper, "Hopper Boat")
          cpw.mods.fml.common.registry.LanguageRegistryaddName(BoatOakTNT, "TNT Boat")
        }
    }
}
