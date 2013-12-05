package k2b6s9j.BoatCraft.registry

import net.minecraft.item.Item
import net.minecraftforge.oredict.OreDictionary
import k2b6s9j.BoatCraft.boat.wood._

object ItemRegistry {

     def InitItems() {
        //OreDictionary Registration
        OreDictionary.registerOre("itemBoat", Item.boat)
        OreDictionary.registerOre("boat", Item.boat)

        new oak.Empty.Item(oak.Empty.Item.ID)
        new oak.Chest.Item(oak.Chest.Item.ID)
        new oak.Furnace.Item(oak.Furnace.Item.ID)
        new oak.Hopper.Item(oak.Hopper.Item.ID)
        new oak.TNT.Item(oak.TNT.Item.ID)
        new spruce.Empty.Item(spruce.Empty.Item.ID)
        new spruce.Chest.Item(spruce.Chest.Item.ID)
        new spruce.Furnace.Item(spruce.Furnace.Item.ID)
        new spruce.Hopper.Item(spruce.Hopper.Item.ID)
        new spruce.TNT.Item(spruce.TNT.Item.ID)
        new birch.Empty.Item(birch.Empty.Item.ID)
        new birch.Chest.Item(birch.Chest.Item.ID)
        new birch.Furnace.Item(birch.Furnace.Item.ID)
        new birch.Hopper.Item(birch.Hopper.Item.ID)
        new birch.TNT.Item(birch.TNT.Item.ID)
        new jungle.Empty.Item(jungle.Empty.Item.ID)
        new jungle.Chest.Item(jungle.Chest.Item.ID)
        new jungle.Furnace.Item(jungle.Furnace.Item.ID)
        new jungle.Hopper.Item(jungle.Hopper.Item.ID)
        new jungle.TNT.Item(jungle.TNT.Item.ID)
    }
}
