package boatcraft.compatibility

import boatcraft.compatibility.nei.BoatRecipeHandler
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPostInitializationEvent

object NEI extends CompatModule {
	
	@Optional.Method(modid = "NotEnoughItems")
	override def doPostInit(event: FMLPostInitializationEvent) {
		
		codechicken.nei.api.API.registerRecipeHandler(new BoatRecipeHandler)
		codechicken.nei.api.API.registerUsageHandler(new BoatRecipeHandler)
	}
}