package boatcraft.core

import nova.core.loader.{Loadable, NovaMod}

@NovaMod(id = "boatcraft", name = "BoatCraft", version = "3.0.0", novaVersion = "0.1.0")
object BoatCraft extends Loadable {

	def preInit() = {
		/*
		log = event.getModLog
		
		config = new Configuration(event getSuggestedConfigurationFile)
		
		channel = NetworkRegistry.INSTANCE.newSimpleChannel("boatcraft")
		
		proxy registerBlocks
		
		NetworkRegistry.INSTANCE.registerGuiHandler("boatcraft", GUIHandler)
		MinecraftForge.EVENT_BUS register EventHandler
		*/
		printModInfo()
		/*
		compatibility preInit event
		
		Registry register Empty
		
		compatibility registerModifiers
		
		GameRegistry registerItem(ItemCustomBoat, "customBoat")
		*/
	}

	def init() = {
		/*
		proxy registerEntities

		proxy registerRenders
		
		GameRegistry.addRecipe(RecipeBoat)
		GameRegistry.addShapelessRecipe(new ItemStack(Items.boat),
				new ItemStack(ItemCustomBoat, 1, OreDictionary.WILDCARD_VALUE))

		compatibility init event
		*/
	}

	def postInit() = {
		//compatibility postInit event
	}

	private def printModInfo() {
		/*
		log info "BoatCraft"
		log info "Copyright Kepler Sticka-Jones 2013-2014"
		log info "http://k2b6s9j.com/projects/minecraft/BoatCraft"
		*/
	}
}

