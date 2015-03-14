package boatcraft.core

import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipFile

import org.apache.commons.io.FileUtils
import org.apache.logging.log4j.Logger

import boatcraft.api.Registry
import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.compatibility
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.oredict.OreDictionary

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
		
		registerJSONs(event)
		
//		proxy registerBlocks
		
		NetworkRegistry.INSTANCE.registerGuiHandler("boatcraft", GUIHandler)
		MinecraftForge.EVENT_BUS register EventHandler
		
		printModInfo()
		
		compatibility.preInit(event)
		
		Registry register Block.Empty
		
		compatibility.registerModifiers()
		
		GameRegistry.registerItem(ItemCustomBoat, "customBoat")
	}

	def registerJSONs(event: FMLPreInitializationEvent)
	{
		val jsonDir = new File(event.getModConfigurationDirectory, "boatcraft/json")
		
		log.info(jsonDir.getAbsolutePath)
		
		if (!jsonDir.isDirectory || jsonDir.listFiles == null && jsonDir.listFiles.isEmpty)
		{
			jsonDir.mkdirs()
			
			val source = FMLCommonHandler.instance.findContainerFor("boatcraft").getSource
			
			if (source isDirectory) {
				val defaultJsonDir = new File(source, "assets/boatcraft/json")
				
				FileUtils.copyDirectory(defaultJsonDir, jsonDir, true)
			}
			else {
				val zip = new ZipFile(source)
				
				val entries = zip.entries()
				
				while (entries hasMoreElements) {
					val entry = entries.nextElement
					if (entry.getName startsWith "assets/boatcraft/json/") {
						val newFile = new File(jsonDir, entry.getName.substring(22))
						
						log.info(newFile.getAbsolutePath)
						
						if (entry.getName endsWith ".json") {
							newFile.createNewFile()
							
							val zin = zip.getInputStream(entry)
							val fout = new FileOutputStream(newFile)
							
							log.info("Copying " + entry.getName.substring(22))
							
							val content = new Array[Byte](FileUtils.ONE_MB toInt)
							val size = zin.read(content)
							fout.write(content, 0, size)
							fout.flush()
						}
						else newFile.mkdirs()
					}
				}
				
				//TODO add defaults from the JAR
			}
		}
		
		log.info(jsonDir.listFiles)
		
		jsonDir.listFiles.foreach(registerJSON(_))
	}

	def registerJSON(file: File): Unit = {
		if(file.isDirectory) {
			file.listFiles().foreach{(realFile: File) => {registerJSON(realFile)}}
		}
		else {
			log.info(s"$file is trying to be registered.")
		}
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

