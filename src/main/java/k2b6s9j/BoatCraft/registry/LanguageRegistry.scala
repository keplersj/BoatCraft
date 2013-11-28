package k2b6s9j.BoatCraft.registry;

import k2b6s9j.BoatCraft.item.boat.wood.birch.*;
import k2b6s9j.BoatCraft.item.boat.wood.jungle.*;
import k2b6s9j.BoatCraft.item.boat.wood.oak.*;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.*;
import net.minecraft.item.Item;

public class LanguageRegistry {

    public static void RegisterName() {

        cpw.mods.fml.common.registry.LanguageRegistry.addName(Item.boat, "Vanilla Boat");

        //Boats
        if (!ConfigRegistry.OreDictWoodBoat) {
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOak.class, "Oak Wood Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakChest.class, "Oak Wood Chest Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakFurnace.class, "Oak Wood Furnace Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakHopper.class, "Oak Wood Hopper Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakTNT.class, "Oak Wood TNT Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruce.class, "Spruce Wood Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceChest.class, "Spruce Wood Chest Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceFurnace.class, "Spruce Wood Furnace Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceHopper.class, "Spruce Wood Hopper Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatSpruceTNT.class, "Spruce Wood TNT Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirch.class, "Birch Wood Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchChest.class, "Birch Wood Chest Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchFurnace.class, "Birch Wood Furnace Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchHopper.class, "Birch Wood Hopper Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatBirchTNT.class, "Birch Wood TNT Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungle.class, "Jungle Wood Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleChest.class, "Jungle Wood Chest Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleFurnace.class, "Jungle Wood Furnace Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleHopper.class, "Jungle Wood Hopper Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatJungleTNT.class, "Jungle Wood TNT Boat");
        }
        if (ConfigRegistry.OreDictWoodBoat) {
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOak.class, "Wooden Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakChest.class, "Chest Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakFurnace.class, "Furnace Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakHopper.class, "Hopper Boat");
            cpw.mods.fml.common.registry.LanguageRegistry.addName(BoatOakTNT.class, "TNT Boat");
        }
    }
}
