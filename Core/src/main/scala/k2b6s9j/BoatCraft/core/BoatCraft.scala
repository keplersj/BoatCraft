package k2b6s9j.BoatCraft.core

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent

import cpw.mods.fml.common.{SidedProxy, Mod}
import k2b6s9j.BoatCraft.core.Proxy.CommonProxy
import org.apache.logging.log4j.Logger

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.Proxy$ClientProxy", serverSide="k2b6s9j.BoatCraft.Proxy$CommonProxy")
	var proxy: CommonProxy = null

  var log: Logger = null
	
	@EventHandler
	def PreInit (event:FMLPreInitializationEvent) {
    log = event.getModLog

    PrintModInfo

    //Entity Registration
    proxy.registerRenderers()
	}

  private def PrintModInfo {
    log.info("BoatCraft")
    log.info("Copyright Kepler Sticka-Jones 2013-2014")
    log.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")
  }

}
