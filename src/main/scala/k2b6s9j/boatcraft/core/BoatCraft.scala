package k2b6s9j.boatcraft.core

import java.util.EnumMap

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.network.{FMLEmbeddedChannel, NetworkRegistry}
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.relauncher.Side
import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.core.materials.Empty
import k2b6s9j.boatcraft.core.packets.ChannelHandler
import k2b6s9j.boatcraft.core.utilities.Recipes

@Mod(modid = "boatcraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{
	@SidedProxy(modId = "boatcraft",
		clientSide = "k2b6s9j.boatcraft.core.Proxy$ClientProxy",
	    serverSide = "k2b6s9j.boatcraft.core.Proxy$CommonProxy")
	var proxy: Proxy.CommonProxy = null
	
	var channels: EnumMap[Side, FMLEmbeddedChannel] = null
	
	var log: Logger = null

	var itemBoat: Boat.ItemCustomBoat = null
	
	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog
		
		channels = NetworkRegistry.INSTANCE newChannel("boatcraft", ChannelHandler);
		
		printModInfo
		
		//All Boat Materials should be at least rideable
		ModifierRegistry addModifier new Empty
		
		itemBoat = new Boat.ItemCustomBoat
		
		GameRegistry registerItem(itemBoat, "customBoat")
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

