package k2b6s9j.BoatCraft.registry

import cpw.mods.fml.common.event.FMLPreInitializationEvent

import k2b6s9j.BoatCraft.utilities.log.ModLogger
import net.minecraftforge.common.Configuration

object ConfigRegistry {

    //Config File Strings
    def itemBoats: String = "Boats in Item Form"

    def OreDictWoodBoat: Boolean = null

    def CreateConfig (event:FMLPreInitializationEvent) {
      Configuration.cfg = new Configuration(event.getSuggestedConfigurationFile())
      try
        //Boats
        BoatOak.ID = cfg.getItem(itemBoats, "Oak Boat", 25500).getInt(25500)
        BoatOakChest.ID = cfg.getItem(itemBoats, "Oak Chest Boat", 25501).getInt(25501)
        BoatOakFurnace.ID = cfg.getItem(itemBoats, "Oak Furnace Boat", 25502).getInt(25502)
        BoatOakHopper.ID = cfg.getItem(itemBoats, "Oak Hopper Boat", 25503).getInt(25503)
        BoatOakTNT.ID = cfg.getItem(itemBoats, "Oak TNT Boat", 25504).getInt(25504)
        BoatSpruce.ID = cfg.getItem(itemBoats, "Spruce Boat", 25505).getInt(25505)
        BoatSpruceChest.ID = cfg.getItem(itemBoats, "Spruce Chest Boat", 25506).getInt(25506)
        BoatSpruceFurnace.ID = cfg.getItem(itemBoats, "Spruce Furnace Boat", 25507).getInt(25507)
        BoatSpruceHopper.ID = cfg.getItem(itemBoats, "Spruce Hopper Boat", 25508).getInt(25508)
        BoatSpruceTNT.ID = cfg.getItem(itemBoats, "Spruce TNT Boat", 25509).getInt(25509)
        BoatBirch.ID = cfg.getItem(itemBoats, "Birch Boat", 25510).getInt(25510)
        BoatBirchChest.ID = cfg.getItem(itemBoats, "Birch Chest Boat", 25511).getInt(25511)
        BoatBirchFurnace.ID = cfg.getItem(itemBoats, "Birch Furnace Boat", 25512).getInt(25512)
        BoatBirchHopper.ID = cfg.getItem(itemBoats, "Birch Hopper Boat", 25513).getInt(25513)
        BoatBirchTNT.ID = cfg.getItem(itemBoats, "Birch TNT Boat", 25514).getInt(25514)
        BoatJungle.ID = cfg.getItem(itemBoats, "Jungle Boat", 25515).getInt(25515)
        BoatJungleChest.ID = cfg.getItem(itemBoats, "Jungle Chest Boat", 25516).getInt(25516)
        BoatJungleFurnace.ID = cfg.getItem(itemBoats, "Jungle Furnace Boat", 25517).getInt(25517)
        BoatJungleHopper.ID = cfg.getItem(itemBoats, "Jungle Hopper Boat", 25518).getInt(25518)
        BoatJungleTNT.ID = cfg.getItem(itemBoats, "Jungle TNT Boat", 25519).getInt(25519)

        //Modules
        OreDictWoodBoat = cfg.get("Modules", "OreDictWoodBoats", false, "Use the OreDictionary to craft Wooden Boats").getBoolean(false)

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
