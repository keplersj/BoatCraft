package k2b6s9j.boatcraft.core

import cpw.mods.fml.common.Mod.{EventHandler, Instance}
import cpw.mods.fml.common.event._
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.boatcraft.core.Proxy.CommonProxy
import org.apache.logging.log4j.Logger
import k2b6s9j.boatcraft.core.registry.{ModifierRegistry, MaterialRegistry}
import k2b6s9j.boatcraft.core.traits.{Modifier, Material}
import k2b6s9j.boatcraft.core.materials.Empty
import k2b6s9j.boatcraft.core.Boat.ItemCustomBoat

@Mod(modid = "boatcraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{
	@SidedProxy(modId = "boatcraft",
		clientSide = "k2b6s9j.boatcraft.core.Proxy$ClientProxy",
	    serverSide = "k2b6s9j.boatcraft.core.Proxy$CommonProxy")
	var proxy: CommonProxy = null
	
	var log: Logger = null

	var itemBoat: ItemCustomBoat = null
	
	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		BoatCraft.log = event.getModLog

		printModInfo
		
		//All Boat Materials should be at least rideable
		ModifierRegistry addModifier new Empty {}
		
		BoatCraft.itemBoat = new ItemCustomBoat()
		
		GameRegistry registerItem(BoatCraft.itemBoat, "customBoat")
	}
	
	@EventHandler
	def init(event: FMLInitializationEvent)
	{
		BoatCraft.proxy.registerEntities
		BoatCraft.proxy.registerRenderers
	}
	
	private def printModInfo {
		BoatCraft.log.info("BoatCraft")
		BoatCraft.log.info("Copyright Kepler Sticka-Jones 2013-2014")
		BoatCraft.log.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")
	}

}
