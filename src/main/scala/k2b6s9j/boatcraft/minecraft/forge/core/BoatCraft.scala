package k2b6s9j.boatcraft.core

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLPreInitializationEvent, FMLPostInitializationEvent}

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
	@SidedProxy(clientSide="k2b6s9j.boatcraft.core.Proxy$ClientProxy",
	    serverSide="k2b6s9j.boatcraft.core.Proxy$CommonProxy")
	var proxy: CommonProxy = null

	var log: Logger = null
	
	var itemBoat: ItemCustomBoat = null

	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event.getModLog

		printModInfo
		
		//All Boat Materials should be at least rideable
		ModifierRegistry.addModifier(new Empty {})
		
		itemBoat = new ItemCustomBoat()
		
		GameRegistry registerItem(itemBoat, "customBoat")

		//Entity Registration
		proxy registerRenderers
	}

	private def printModInfo {
		log.info("BoatCraft")
		log.info("Copyright Kepler Sticka-Jones 2013-2014")
		log.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")
	}

	private def buildMatrix
	{
		for (material: Material <- MaterialRegistry.materials)
		{
			for (modifier: Modifier <- ModifierRegistry.modifiers)
			{
				log.info(material.name + " " + modifier.name)
			}
		}
	}

}
