package k2b6s9j.BoatCraft.registry;

import k2b6s9j.BoatCraft.BoatCraft;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.*;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.*;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.*;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.*;

public class EntityRegistry {

    static BoatCraft mod;

    public static void RegisterEntities() {
        int id = 1;
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOak.class, "Oak Wood Boat", id, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakChest.class, "Oak Wood Chest Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakFurnace.class, "Oak Wood Furnace Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakHopper.class, "Oak Wood Hopper Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatOakTNT.class, "Oak Wood TNT Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruce.class, "Spruce Wood Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceChest.class, "Spruce Wood Chest Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceFurnace.class, "Spruce Wood Furnace Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceHopper.class, "Spruce Wood Hopper Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatSpruceTNT.class, "Spruce Wood TNT Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirch.class, "Birch Wood Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchChest.class, "Birch Wood Chest Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchFurnace.class, "Birch Wood Furnace Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchHopper.class, "Birch Wood Hopper Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatBirchTNT.class, "Birch Wood TNT Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungle.class, "Jungle Wood Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleChest.class, "Jungle Wood Chest Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleFurnace.class, "Jungle Wood Furnace Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleHopper.class, "Jungle Wood Hopper Boat", id++, mod, 80, 3, true);
        cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityBoatJungleTNT.class, "Jungle Wood TNT Boat", id++, mod, 80, 3, true);
    }
}
