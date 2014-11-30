package boatcraft.compatibility

import boatcraft.compatibility.nei.BoatRecipeHandler
//import codechicken.nei.api.API
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent

object NEI extends CompatModule {
	
	/*@Optional.Method(modid = "NotEnoughItems")
	override def doPostInit(event: FMLPostInitializationEvent) {
		
		API.registerRecipeHandler(new BoatRecipeHandler)
		API.registerUsageHandler(new BoatRecipeHandler)
	}*/
}