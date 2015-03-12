package boatcraft.core

import java.io.File

import boatcraft.api.Registry
import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.compatibility
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.{Mod, SidedProxy}
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.oredict.OreDictionary
import org.apache.logging.log4j.Logger

@Mod(modid = "boatcraft",
	name = "BoatCraft",
	version = "2.0",
	modLanguage = "scala",
	dependencies = "after:IronChest;after:Thaumcraft;after:IC2;after:BuildCraft|Factory;" +
				"after:Forestry;after:NotEnoughItems;after:CoFHAPI|energy")
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
		
		registerMaterials(event)
		
		proxy registerBlocks
		
		NetworkRegistry.INSTANCE.registerGuiHandler("boatcraft", GUIHandler)
		MinecraftForge.EVENT_BUS register EventHandler
		
		printModInfo()
		
		compatibility.preInit(event)
		
		Registry register Block.Empty
		
		compatibility.registerModifiers()
		
		GameRegistry.registerItem(ItemCustomBoat, "customBoat")
	}

	def registerMaterials(event: FMLPreInitializationEvent)
	{
		val jarJson = new ResourceLocation("boatcraft", "json")
		val jsonDir = new File(jarJson.getResourcePath)
		jsonDir.listFiles()
	}

	@Mod.EventHandler
	def init(event: FMLInitializationEvent) {
		
		proxy.registerEntities()

		proxy.registerRenders()
		
		GameRegistry.addRecipe(RecipeBoat)
		GameRegistry.addShapelessRecipe(new ItemStack(Items.boat),
				new ItemStack(ItemCustomBoat, 1, OreDictionary.WILDCARD_VALUE))

		compatibility.init(event)
	}

	@Mod.EventHandler
	def postInit(event: FMLPostInitializationEvent) {
		compatibility postInit event
	}

	private def printModInfo() {
		log info "BoatCraft"
		log info "Copyright Kepler Sticka-Jones 2013-2014"
		log info "http://k2b6s9j.com/projects/minecraft/BoatCraft"
	}
}

