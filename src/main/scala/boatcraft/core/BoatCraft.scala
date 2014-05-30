package boatcraft.core

import org.apache.logging.log4j.Logger
import boatcraft.api.Registry
import boatcraft.api.boat.ItemCustomBoat
import boatcraft.compatibility
import boatcraft.core.utilities.Recipes
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.{FMLEmbeddedChannel, NetworkRegistry}
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import net.minecraftforge.common.config.Configuration
import boatcraft.core.modifiers.blocks.Empty
import java.util
import net.minecraftforge.common.MinecraftForge
import boatcraft.core.modifiers.blocks.Empty
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
import cpw.mods.fml.common.event.FMLServerStartingEvent
import boatcraft.compatibility.nei.BoatRecipeHandler

@Mod(modid = "boatcraft",
	name = "BoatCraft",
	version = "2.0",
	modLanguage = "scala",
	dependencies = "after:IronChest;after:Thaumcraft;after:IC2;after:BuildCraft|Factory;" +
					"after:Forestry;after:NotEnoughItems")
object BoatCraft {
	@SidedProxy(modId = "boatcraft",
		clientSide = "boatcraft.core.Proxy$ClientProxy",
		serverSide = "boatcraft.core.Proxy$CommonProxy")
	var proxy: Proxy.CommonProxy = null

	private[boatcraft] var config: Configuration = null

	var channel: SimpleNetworkWrapper = null

	private[boatcraft] var log: Logger = null

	@Mod.EventHandler
	def preInit(event: FMLPreInitializationEvent) {
		
		log = event.getModLog
		
		config = new Configuration(event getSuggestedConfigurationFile)
		
		channel = NetworkRegistry.INSTANCE.newSimpleChannel("boatcraft")
		
		proxy registerBlocks
		
		NetworkRegistry.INSTANCE.registerGuiHandler("boatcraft", GUIHandler)
		MinecraftForge.EVENT_BUS register EventHandler
		
		printModInfo
		
		compatibility preInit event
		
		Registry register Empty
		
		compatibility registerModifiers
		
		GameRegistry registerItem(ItemCustomBoat, "customBoat")
	}

	@Mod.EventHandler
	def init(event: FMLInitializationEvent) {
		
		proxy registerEntities

		proxy registerRenders
		
		GameRegistry.addRecipe(RecipeBoat)

		compatibility init event
	}

	@Mod.EventHandler
	def postInit(event: FMLPostInitializationEvent) {
		compatibility postInit event
	}

	private def printModInfo {
		log info "BoatCraft"
		log info "Copyright Kepler Sticka-Jones 2013-2014"
		log info "http://k2b6s9j.com/projects/minecraft/BoatCraft"
	}
}

