package k2b6s9j.BoatCraft.registry

import k2b6s9j.BoatCraft.item.boat.wood.birch._
import k2b6s9j.BoatCraft.item.boat.wood.jungle._
import k2b6s9j.BoatCraft.item.boat.wood.oak._
import k2b6s9j.BoatCraft.item.boat.wood.spruce._
import net.minecraft.item.Item

class LanguageRegistry {

  def FMLLanguageRegistry:cpw.mods.fml.common.registry.LanguageRegistry

    def RegisterName() {

        FMLLanguageRegistry.addName(Item.boat, "Vanilla Boat")

        //Boats
        if (!ConfigRegistry.OreDictWoodBoat) {
          FMLLanguageRegistry.addName(BoatOak, "Oak Wood Boat")
          FMLLanguageRegistry.addName(BoatOakChest, "Oak Wood Chest Boat")
          FMLLanguageRegistry.addName(BoatOakFurnace, "Oak Wood Furnace Boat")
          FMLLanguageRegistry.addName(BoatOakHopper, "Oak Wood Hopper Boat")
          FMLLanguageRegistry.addName(BoatOakTNT, "Oak Wood TNT Boat")
          FMLLanguageRegistry.addName(BoatSpruce, "Spruce Wood Boat")
          FMLLanguageRegistry.addName(BoatSpruceChest, "Spruce Wood Chest Boat")
          FMLLanguageRegistry.addName(BoatSpruceFurnace, "Spruce Wood Furnace Boat")
          FMLLanguageRegistry.addName(BoatSpruceHopper, "Spruce Wood Hopper Boat")
          FMLLanguageRegistry.addName(BoatSpruceTNT, "Spruce Wood TNT Boat")
          FMLLanguageRegistry.addName(BoatBirch, "Birch Wood Boat")
          FMLLanguageRegistry.addName(BoatBirchChest, "Birch Wood Chest Boat")
          FMLLanguageRegistry.addName(BoatBirchFurnace, "Birch Wood Furnace Boat")
          FMLLanguageRegistry.addName(BoatBirchHopper, "Birch Wood Hopper Boat")
          FMLLanguageRegistry.addName(BoatBirchTNT, "Birch Wood TNT Boat")
          FMLLanguageRegistry.addName(BoatJungle, "Jungle Wood Boat")
          FMLLanguageRegistry.addName(BoatJungleChest, "Jungle Wood Chest Boat")
          FMLLanguageRegistry.addName(BoatJungleFurnace, "Jungle Wood Furnace Boat")
          FMLLanguageRegistry.addName(BoatJungleHopper, "Jungle Wood Hopper Boat")
          FMLLanguageRegistry.addName(BoatJungleTNT, "Jungle Wood TNT Boat")
        }
        if (ConfigRegistry.OreDictWoodBoat) {
          FMLLanguageRegistry.addName(BoatOak, "Wooden Boat")
          FMLLanguageRegistry.addName(BoatOakChest, "Chest Boat")
          FMLLanguageRegistry.addName(BoatOakFurnace, "Furnace Boat")
          FMLLanguageRegistry.addName(BoatOakHopper, "Hopper Boat")
          FMLLanguageRegistry.addName(BoatOakTNT, "TNT Boat")
        }
    }
}
