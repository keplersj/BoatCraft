package k2b6s9j.BoatCraft

import cpw.mods.fml.common.Mod.{Instance, EventHandler}
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent

import k2b6s9j.BoatCraft.registry._
;
import k2b6s9j.BoatCraft.utilities.log.ModLogger
import cpw.mods.fml.common.{SidedProxy, Mod}
import cpw.mods.fml.common.network.NetworkMod
import k2b6s9j.BoatCraft.proxy.CommonProxy

@Mod(modid = BoatCraft.name, name = BoatCraft.name, version = BoatCraft.version, dependencies="after:Forestry;")
@NetworkMod(channels = {"BoatCraft"}, clientSideRequired = true, serverSideRequired = true)
class BoatCraft
{
	  @Instance("BoatCraft")
    val instance:this.type

	  @SidedProxy(clientSide="k2b6s9j.BoatCraft.proxy.ClientProxy", serverSide="k2b6s9j.BoatCraft.proxy.CommonProxy")
	  def proxy:CommonProxy

    //General Mod Information
    val version: String = "2.0"
    val name: String = "BoatCraft"

    @EventHandler
	  def PreInit (event:FMLPreInitializationEvent)
	  {
        ModLogger.info("BoatCraft")
        ModLogger.info("Copyright Kepler Sticka-Jones 2013")
        ModLogger.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")

        //Configuration Registration
        ConfigRegistry.CreateConfig(event)

        //Item Registration
        ItemRegistry.InitItems()

        RecipeRegistration.RegisterRecipies()

        //Entity Registration
        proxy.registerRenderers()
        EntityRegistry.RegisterEntities()

        //Statistics Registration
        StatisticsRegistration.RegisterMCStats()
	  }

	  @EventHandler
	  def Init (event:FMLInitializationEvent)
	  {
      LanguageRegistry.RegisterName()
    }

	  @EventHandler
	  def PostInit (event:FMLPostInitializationEvent)
	  {

	  }
}
