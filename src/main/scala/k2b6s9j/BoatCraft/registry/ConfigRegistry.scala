package k2b6s9j.BoatCraft.registry

import java.io.File
import k2b6s9j.BoatCraft.boat.wood._
import net.minecraftforge.common.Configuration
import k2b6s9j.BoatCraft.utilities.log.ModLogger


object ConfigRegistry {

  //Config File Strings
  def itemBoats: String = "Boat Item IDs"

  def CreateConfig(file: File) : Unit = {
    val config = new Configuration(file)
    config.load()

    LoadVanillaBoats(config)

    if ( config.hasChanged() ) {
      config.save()
    }
  }

  private def LoadVanillaBoats (cfg: Configuration): Unit = {
    try {
      /*
      Loading the IDs for Vanilla Wood Boats
      */

      /*
      Oak Wood Boats
      */
      oak.Empty.Item.ID = cfg.getItem(itemBoats, "Empty Oak Wood Boat", 25500).getInt
      oak.Chest.Item.ID = cfg.getItem(itemBoats, "Chest Oak Wood Boat", 25501).getInt
      oak.Furnace.Item.ID = cfg.getItem(itemBoats, "Furnace Oak Wood Boat", 25502).getInt
      oak.Hopper.Item.ID = cfg.getItem(itemBoats, "Hopper Oak Wood Boat", 25503).getInt
      oak.TNT.Item.ID = cfg.getItem(itemBoats, "TNT Oak Wood Boat", 25504).getInt

      /*
      Spruce Wood Boats
      */
      spruce.Empty.Item.ID = cfg.getItem(itemBoats, "Empty Spruce Wood Boat", 25505).getInt
      spruce.Chest.Item.ID = cfg.getItem(itemBoats, "Chest Spruce Wood Boat", 25506).getInt
      spruce.Furnace.Item.ID = cfg.getItem(itemBoats, "Furnace Spruce Wood Boat", 25507).getInt
      spruce.Hopper.Item.ID = cfg.getItem(itemBoats, "Hopper Spruce Wood Boat", 25508).getInt
      spruce.TNT.Item.ID = cfg.getItem(itemBoats, "TNT Spruce Wood Boat Boat", 25509).getInt

      /*
      Birch Wood Boats
       */
      birch.Empty.Item.ID = cfg.getItem(itemBoats, "Empty Birch Wood Boat", 25510).getInt
      birch.Chest.Item.ID = cfg.getItem(itemBoats, "Chest Birch Wood Boat", 25511).getInt
      birch.Furnace.Item.ID = cfg.getItem(itemBoats, "Furnace Birch Wood Boat", 25512).getInt
      birch.Hopper.Item.ID = cfg.getItem(itemBoats, "Hopper Birch Wood Boat", 25513).getInt
      birch.TNT.Item.ID = cfg.getItem(itemBoats, "TNT Birch Wood Boat", 25514).getInt

      /*
      Jungle Wood Boats
      */
      jungle.Empty.Item.ID = cfg.getItem(itemBoats, "Empty Jungle Wood Boat", 25515).getInt
      jungle.Chest.Item.ID = cfg.getItem(itemBoats, "Chest Jungle Wood Boat", 25516).getInt
      jungle.Furnace.Item.ID = cfg.getItem(itemBoats, "Furnace Jungle Wood Boat", 25517).getInt
      jungle.Hopper.Item.ID = cfg.getItem(itemBoats, "Hopper Jungle Wood Boat", 25518).getInt
      jungle.TNT.Item.ID = cfg.getItem(itemBoats, "TNT Jungle Wood Boat", 25519).getInt
    }
    catch {
      case _ => ModLogger.info("Unable to save item IDs.")
      case e: Exception => e.printStackTrace()
    }
  }
}