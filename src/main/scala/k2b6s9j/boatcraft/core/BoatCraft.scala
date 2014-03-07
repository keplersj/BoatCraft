package k2b6s9j.boatcraft.core

import java.util.EnumMap

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.FMLEmbeddedChannel
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.api.boat.ItemCustomBoat
import k2b6s9j.boatcraft.core.modifiers.Empty
import k2b6s9j.boatcraft.core.packets.ChannelHandler
import k2b6s9j.boatcraft.core.utilities.Recipes
import net.minecraft.item.Item
import net.minecraftforge.common.config.Configuration

@Mod(modid = "boatcraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{
	@SidedProxy(modId = "boatcraft",
		clientSide = "k2b6s9j.boatcraft.core.Proxy$ClientProxy",
		serverSide = "k2b6s9j.boatcraft.core.Proxy$CommonProxy")
	var proxy: Proxy.CommonProxy = null
	
	private[boatcraft] var config: Configuration = null
	
	var channels: EnumMap[Side, FMLEmbeddedChannel] = null
	
	private[boatcraft] var log: Logger = null
	
	var itemBoat: ItemCustomBoat = null
	
	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog
		
		config = new Configuration(event getSuggestedConfigurationFile)
		
		channels = NetworkRegistry.INSTANCE newChannel ("boatcraft", ChannelHandler);
		
		printModInfo
		
		//All Boat Materials should be at least rideable
		Registry register Empty
		
		itemBoat = new ItemCustomBoat
		
		GameRegistry registerItem (itemBoat, "customBoat")
		
		log info (Item.itemRegistry getNameForObject itemBoat)
	}
	
	@EventHandler
	def init(event: FMLInitializationEvent) 
	{
		proxy registerEntities

		proxy registerRenderers

		Recipes addBoatRecipes
	}
	
	def printModInfo
	{
		log info "BoatCraft"
		log info "Copyright Kepler Sticka-Jones 2013-2014"
		log info "http://k2b6s9j.com/projects/minecraft/BoatCraft"
	}
}

