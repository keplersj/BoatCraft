package k2b6s9j.boatcraft.compatibility.thaumcraft.boat

import cpw.mods.fml.common.event.{ FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent }
import net.minecraftforge.common.config.Configuration
import k2b6s9j.boatcraft.compatibility.thaumcraft.boat.Materials.Entity.Wood._
import java.io.File
import cpw.mods.fml.common.Optional

object Thaumcraft
{

	def boats: String = "Thaumcraft Boat Item IDs"

	@Optional.Method(modid = "thaumcraft")
	def PreInit(event: FMLPreInitializationEvent) {

		val file: File = event.getSuggestedConfigurationFile
		val config = new Configuration(file)
		config.load()

		loadBoats(config)

		registerEntities()

	}

	private def loadBoats(cfg: Configuration) {
		/*
    Loading the IDs for Vanilla Wood Boats
     */

		/*
    Greatwood Boats
     */
		/*greatwood.Empty.Item.ID = cfg.getItem(boats, "Empty Greatwood Boat", 25520).getInt
    greatwood.Chest.Item.ID = cfg.getItem(boats, "Chest Greatwood Boat", 25521).getInt
    greatwood.Furnace.Item.ID = cfg.getItem(boats, "Furnace Greatwood Boat", 25522).getInt
    greatwood.TNT.Item.ID = cfg.getItem(boats, "TNT Greatwood Boat", 25523).getInt
    greatwood.Hopper.Item.ID = cfg.getItem(boats, "Hopper Greatwood Boat", 25524).getInt

    /*
    Silverwood Boats
     */
    silverwood.Empty.Item.ID = cfg.getItem(boats, "Empty Silverwood Boat", 25525).getInt
    silverwood.Chest.Item.ID = cfg.getItem(boats, "Chest Silverwood Boat", 25526).getInt
    silverwood.Furnace.Item.ID = cfg.getItem(boats, "Furnace Silverwood Boat", 25527).getInt
    silverwood.TNT.Item.ID = cfg.getItem(boats, "TNT Silverwood Boat", 25528).getInt
    silverwood.Hopper.Item.ID = cfg.getItem(boats, "Hopper Silverwood Boat", 25529).getInt
*/
	}

	private def registerEntities() {

	}

	def Init(event: FMLInitializationEvent) {

	}

	def PostInit(event: FMLPostInitializationEvent) {

	}

}
