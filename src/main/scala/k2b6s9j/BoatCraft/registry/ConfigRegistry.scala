package k2b6s9j.BoatCraft.registry

import java.io.File
import k2b6s9j.BoatCraft.boat.wood._
import net.minecraftforge.common.Configuration


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
      new oak.Empty.Item(cfg.getItem(itemBoats, "Empty Oak Wood Boat", 25500).getInt)
      new oak.Chest.Item(cfg.getItem(itemBoats, "Chest Oak Wood Boat", 25501).getInt)
      new oak.Furnace.Item(cfg.getItem(itemBoats, "Furnace Oak Wood Boat", 25502).getInt)
      new oak.Hopper.Item(cfg.getItem(itemBoats, "Hopper Oak Wood Boat", 25503).getInt)
      new oak.TNT.Item(cfg.getItem(itemBoats, "TNT Oak Wood Boat", 25504).getInt)

      /*
      Spruce Wood Boats
      */
      new spruce.Empty.Item(cfg.getItem(itemBoats, "Empty Spruce Wood Boat", 25505).getInt)
      new spruce.Chest.Item(cfg.getItem(itemBoats, "Chest Spruce Wood Boat", 25506).getInt)
      new spruce.Furnace.Item(cfg.getItem(itemBoats, "Furnace Spruce Wood Boat", 25507).getInt)
      new spruce.Hopper.Item(cfg.getItem(itemBoats, "Hopper Spruce Wood Boat", 25508).getInt)
      new spruce.TNT.Item(cfg.getItem(itemBoats, "TNT Spruce Wood Boat Boat", 25509).getInt)

      /*
      Birch Wood Boats
       */
      new birch.Empty.Item(cfg.getItem(itemBoats, "Empty Birch Wood Boat", 25510).getInt)
      new birch.Chest.Item(cfg.getItem(itemBoats, "Chest Birch Wood Boat", 25511).getInt)
      new birch.Furnace.Item(cfg.getItem(itemBoats, "Furnace Birch Wood Boat", 25512).getInt)
      new birch.Hopper.Item(cfg.getItem(itemBoats, "Hopper Birch Wood Boat", 25513).getInt)
      new birch.TNT.Item(cfg.getItem(itemBoats, "TNT Birch Wood Boat", 25514).getInt)

      /*
      Jungle Wood Boats
      */
      new jungle.Empty.Item(cfg.getItem(itemBoats, "Empty Jungle Wood Boat", 25515).getInt)
      new jungle.Chest.Item(cfg.getItem(itemBoats, "Chest Jungle Wood Boat", 25516).getInt)
      new jungle.Furnace.Item(cfg.getItem(itemBoats, "Furnace Jungle Wood Boat", 25517).getInt)
      new jungle.Hopper.Item(cfg.getItem(itemBoats, "Hopper Jungle Wood Boat", 25518).getInt)
      new jungle.TNT.Item(cfg.getItem(itemBoats, "TNT Jungle Wood Boat", 25519).getInt)
    }
    catch {
      case e: Exception => e.printStackTrace()
    }
  }
}