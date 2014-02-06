package k2b6s9j.boatcraft.compatibility.vanilla

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.Mod.EventHandler
import k2b6s9j.boatcraft.core.registry.MaterialRegistry
import k2b6s9j.boatcraft.compatibility.vanilla.boat.wood._
import org.apache.logging.log4j.Logger

@Mod(name = "boatcraft:compatibility:vanilla:materials:wood",
	modid = "BoatCraft:Compatibility:Vanilla:Materials:Wood",
	modLanguage = "scala", dependencies = "required-after:boatcraft")
object Mod
{

	var log: Logger = null

	@EventHandler
	def preInit(event: FMLPreInitializationEvent) {
		log = event.getModLog

		printModInfo

		registerMaterials
	}

	private def printModInfo {
		log.info("BoatCraft:Compatibility:Vanilla:Materials:Wood")
		log.info("Adds Vanilla Woods to the BoatCraft Material Matrix")
		log.info("Copyright Kepler Sticka-Jones 2014")
	}

	private def registerMaterials {
		MaterialRegistry.addMaterial(new Oak {})
		MaterialRegistry.addMaterial(new Spruce {})
		MaterialRegistry.addMaterial(new Birch {})
		MaterialRegistry.addMaterial(new Jungle {})
		MaterialRegistry.addMaterial(new Acacia {})
		MaterialRegistry.addMaterial(new DarkOak {})
	}

}
