package k2b6s9j.BoatCraft.core

import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent

import cpw.mods.fml.common.{SidedProxy, Mod}
import k2b6s9j.BoatCraft.core.Proxy.CommonProxy
import org.apache.logging.log4j.Logger
import k2b6s9j.BoatCraft.core.registry.{ModifierRegistry, MaterialRegistry}
import k2b6s9j.BoatCraft.core.traits.{Modifier, Material}
import k2b6s9j.BoatCraft.core.materials.Empty
import k2b6s9j.BoatCraft.core.Boat.ItemCustomBoat

@Mod(modid = "BoatCraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{
	@SidedProxy(clientSide="k2b6s9j.BoatCraft.core.Proxy$ClientProxy", serverSide="k2b6s9j.BoatCraft.core.Proxy$CommonProxy")
	var proxy: CommonProxy = null

  var log: Logger = null
	
	@EventHandler
	def PreInit (event:FMLPreInitializationEvent) {
    log = event.getModLog

    PrintModInfo

    //All Boat Materials should be atleast rideable
    ModifierRegistry.addModifier(new Empty {})

    BuildMatrix

    //Entity Registration
    proxy.registerRenderers()
	}

  private def PrintModInfo {
    log.info("BoatCraft")
    log.info("Copyright Kepler Sticka-Jones 2013-2014")
    log.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")
  }

  private def BuildMatrix {
    for (material: Material <- MaterialRegistry.materials) {
      for (modifier: Modifier <- ModifierRegistry.modifiers) {
        new ItemCustomBoat
      }
    }
  }

}
