package boatcraft.core

import java.util.EnumMap

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.FMLEmbeddedChannel
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import boatcraft.api.Registry
import boatcraft.api.boat.ItemCustomBoat
import boatcraft.core.modifiers.Empty
import boatcraft.core.packets.ChannelHandler
import boatcraft.core.utilities.Recipes
import boatcraft.compatibility
import net.minecraftforge.common.config.Configuration

@Mod(modid = "boatcraft",
  name = "BoatCraft",
  version = "2.0",
  modLanguage = "scala",
  dependencies = "after:IronChest, thaumcraft, IC2")
object BoatCraft
{
	@SidedProxy(modId = "boatcraft",
		clientSide = "boatcraft.core.Proxy$ClientProxy",
		serverSide = "boatcraft.core.Proxy$CommonProxy")
	var proxy: Proxy.CommonProxy = null
	
	private[boatcraft] var config: Configuration = null
	
	var channels: EnumMap[Side, FMLEmbeddedChannel] = null
	
	private[boatcraft] var log: Logger = null


  @EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog
		
		config = new Configuration(event getSuggestedConfigurationFile)
		
		channels = NetworkRegistry.INSTANCE newChannel ("boatcraft", ChannelHandler);
		
		printModInfo
		
		//All Boat Materials should be at least rideable
		Registry register Empty
		
		GameRegistry registerItem (ItemCustomBoat, "customBoat")

    compatibility.preInit(event)
	}
	
	@EventHandler
	def init(event: FMLInitializationEvent) 
	{
		proxy registerEntities

		proxy registerRenderers

		Recipes addBoatRecipes

    compatibility.init(event)
	}

  @EventHandler
  def postInit(event: FMLPostInitializationEvent) {
    compatibility.postInit(event)
  }
	
	def printModInfo
	{
		log info "BoatCraft"
		log info "Copyright Kepler Sticka-Jones 2013-2014"
		log info "http://k2b6s9j.com/projects/minecraft/BoatCraft"
	}
}

