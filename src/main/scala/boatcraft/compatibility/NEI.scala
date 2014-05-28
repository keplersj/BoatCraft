package boatcraft.compatibility

import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import codechicken.nei.api.API

object NEI extends CompatModule("NEI") {
	
	@Optional.Method(modid = "NEI")
	override def doPostInit(event: FMLPostInitializationEvent) {
		
		API.registerRecipeHandler(CustomBoatRecipeHandler)
		API.registerUsageHandler(CustomBoatRecipeHandler)
	}
}