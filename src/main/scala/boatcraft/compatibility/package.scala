package boatcraft

import scala.collection.mutable

import boatcraft.core.BoatCraft
import cpw.mods.fml.common.Loader
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent

package object compatibility {
	
	var maxID = 0

	val mods = mutable.HashMap[String, CompatModule] (
			("BuildCraft|Factory", BuildCraft),
			("CoFHAPI|energy", RedstoneFlux),
			("Forestry", Forestry),
			("IC2", IC2),
			("IronChest", IronChests),
			("NotEnoughItems", NEI),
			("Thaumcraft", Thaumcraft),
			("", Vanilla)
	)

	def registerModifiers() {
		for (module <- mods) {
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2.registerModifiers
			else BoatCraft.log warn "Could not register additions for module:" + module._1
		}
	}

	def preInit(event: FMLPreInitializationEvent) {
		for (module <- mods) {
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2 preInit event
			else BoatCraft.log warn "Could not preInit module:" + module._1
		}
	}

	def init(event: FMLInitializationEvent) {
		for (module <- mods) {
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2 init event
			else BoatCraft.log warn "Could not Init module:" + module._1
		}
	}

	def postInit(event: FMLPostInitializationEvent) {
		for (module <- mods) {
			if (module._1.isEmpty || Loader.isModLoaded(module._1))
				module._2 postInit event
			else BoatCraft.log warn "Could not postInit module:" + module._1
		}
	}

}
