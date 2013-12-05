package k2b6s9j.BoatCraft.registry

import cpw.mods.fml.common.event.FMLPreInitializationEvent

import k2b6s9j.BoatCraft.boat.wood._
import k2b6s9j.BoatCraft.utilities.log.ModLogger

import net.minecraftforge.common.Configuration

object ConfigRegistry {

    //Config File Strings
    def itemBoats: String = "Boats in Item Form"

  var OreDictWoodBoat: Boolean = false

    def CreateConfig (event:FMLPreInitializationEvent) {
      val cfg: Configuration = new Configuration(event.getSuggestedConfigurationFile())
      try {
        //Boats
        oak.Empty.Item.ID = cfg.getItem(itemBoats, "Oak Boat", 25500).getInt(25500)
        oak.Chest.Item.ID = cfg.getItem(itemBoats, "Oak Chest Boat", 25501).getInt(25501)
        oak.Furnace.Item.ID = cfg.getItem(itemBoats, "Oak Furnace Boat", 25502).getInt(25502)
        oak.Hopper.Item.ID = cfg.getItem(itemBoats, "Oak Hopper Boat", 25503).getInt(25503)
        oak.TNT.Item.ID = cfg.getItem(itemBoats, "Oak TNT Boat", 25504).getInt(25504)
        spruce.Empty.Item.ID = cfg.getItem(itemBoats, "Spruce Boat", 25505).getInt(25505)
        spruce.Chest.Item.ID = cfg.getItem(itemBoats, "Spruce Chest Boat", 25506).getInt(25506)
        spruce.Furnace.Item.ID = cfg.getItem(itemBoats, "Spruce Furnace Boat", 25507).getInt(25507)
        spruce.Hopper.Item.ID = cfg.getItem(itemBoats, "Spruce Hopper Boat", 25508).getInt(25508)
        spruce.TNT.Item.ID = cfg.getItem(itemBoats, "Spruce TNT Boat", 25509).getInt(25509)
        birch.Empty.Item.ID = cfg.getItem(itemBoats, "Birch Boat", 25510).getInt(25510)
        birch.Chest.Item.ID = cfg.getItem(itemBoats, "Birch Chest Boat", 25511).getInt(25511)
        birch.Furnace.Item.ID = cfg.getItem(itemBoats, "Birch Furnace Boat", 25512).getInt(25512)
        birch.Hopper.Item.ID = cfg.getItem(itemBoats, "Birch Hopper Boat", 25513).getInt(25513)
        birch.TNT.Item.ID = cfg.getItem(itemBoats, "Birch TNT Boat", 25514).getInt(25514)
        jungle.Empty.Item.ID = cfg.getItem(itemBoats, "Jungle Boat", 25515).getInt(25515)
        jungle.Chest.Item.ID = cfg.getItem(itemBoats, "Jungle Chest Boat", 25516).getInt(25516)
        jungle.Furnace.Item.ID = cfg.getItem(itemBoats, "Jungle Furnace Boat", 25517).getInt(25517)
        jungle.Hopper.Item.ID = cfg.getItem(itemBoats, "Jungle Hopper Boat", 25518).getInt(25518)
        jungle.TNT.Item.ID = cfg.getItem(itemBoats, "Jungle TNT Boat", 25519).getInt(25519)

        //Modules
        OreDictWoodBoat = cfg.get("Modules", "OreDictWoodBoats", false, "Use the OreDictionary to craft Wooden Boats").getBoolean(false)
      }
      catch {
        case e: Exception => ModLogger.severe("Failed to load configuration file.")
        case e: Exception => e.printStackTrace()
      }
      finally {
        if (cfg.hasChanged())
          cfg.save()
      }
    }
}
