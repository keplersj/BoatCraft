package k2b6s9j.BoatCraft.core

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent

import cpw.mods.fml.common.{SidedProxy, Mod}
import k2b6s9j.BoatCraft.core.Proxy.CommonProxy

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "2.0", modLanguage = "scala",dependencies="after:Forestry;")
object BoatCraft
{
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.Proxy$ClientProxy", serverSide="k2b6s9j.BoatCraft.Proxy$CommonProxy")
	var proxy: CommonProxy = null
	
	@EventHandler
	def PreInit (event:FMLPreInitializationEvent) {
      		Log.info("BoatCraft")
      		Log.info("Copyright Kepler Sticka-Jones 2013-2014")
      		Log.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")

      		//Entity Registration
      		proxy.registerRenderers()
	}
  
}
