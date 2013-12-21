package k2b6s9j.BoatCraft.registry

import java.io.File
import k2b6s9j.BoatCraft.boat.wood._
import net.minecraftforge.common.Configuration


object ConfigRegistry {

  //Config File Strings
  def itemBoats: String = "Boats in Item Form"

  var OreDictWoodBoat: Boolean = false

  def CreateConfig(file: File) : Unit = {
    val config = new Configuration(file)
    config.load()

    LoadItems(config)

    if ( config.hasChanged() ) {
      config.save()
    }
  }

  private def LoadItems (cfg: Configuration): Unit = {
    //Boats
    oak.Empty.Item.ID = cfg.getItem(itemBoats, "Oak Boat", 25500).getInt
    oak.Chest.Item.ID = cfg.getItem(itemBoats, "Oak Chest Boat", 25501).getInt
    oak.Furnace.Item.ID = cfg.getItem(itemBoats, "Oak Furnace Boat", 25502).getInt
    oak.Hopper.Item.ID = cfg.getItem(itemBoats, "Oak Hopper Boat", 25503).getInt
    oak.TNT.Item.ID = cfg.getItem(itemBoats, "Oak TNT Boat", 25504).getInt
    spruce.Empty.Item.ID = cfg.getItem(itemBoats, "Spruce Boat", 25505).getInt
    spruce.Chest.Item.ID = cfg.getItem(itemBoats, "Spruce Chest Boat", 25506).getInt
    spruce.Furnace.Item.ID = cfg.getItem(itemBoats, "Spruce Furnace Boat", 25507).getInt
    spruce.Hopper.Item.ID = cfg.getItem(itemBoats, "Spruce Hopper Boat", 25508).getInt
    spruce.TNT.Item.ID = cfg.getItem(itemBoats, "Spruce TNT Boat", 25509).getInt
    birch.Empty.Item.ID = cfg.getItem(itemBoats, "Birch Boat", 25510).getInt
    birch.Chest.Item.ID = cfg.getItem(itemBoats, "Birch Chest Boat", 25511).getInt
    birch.Furnace.Item.ID = cfg.getItem(itemBoats, "Birch Furnace Boat", 25512).getInt
    birch.Hopper.Item.ID = cfg.getItem(itemBoats, "Birch Hopper Boat", 25513).getInt
    birch.TNT.Item.ID = cfg.getItem(itemBoats, "Birch TNT Boat", 25514).getInt
    jungle.Empty.Item.ID = cfg.getItem(itemBoats, "Jungle Boat", 25515).getInt
    jungle.Chest.Item.ID = cfg.getItem(itemBoats, "Jungle Chest Boat", 25516).getInt
    jungle.Furnace.Item.ID = cfg.getItem(itemBoats, "Jungle Furnace Boat", 25517).getInt
    jungle.Hopper.Item.ID = cfg.getItem(itemBoats, "Jungle Hopper Boat", 25518).getInt
    jungle.TNT.Item.ID = cfg.getItem(itemBoats, "Jungle TNT Boat", 25519).getInt
  }
}