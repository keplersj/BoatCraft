package k2b6s9j.boatcraft.compatibility

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkRegistry
import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.compatibility.ironchest.{IronChestsEventHandler, IronChestsGuiHandler}
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers.{Copper_Chest, Crystal_Chest, Diamond_Chest, DirtChest9000, Gold_Chest, Iron_Chest, Obsidian_Chest, Silver_Chest}
import net.minecraftforge.common.MinecraftForge

//TODO: Fill Documentation
/**
 *
 */
@Mod(modid = "boatcraft:compatibility:IronChest", name = "BoatCraft Iron Chests 2 Compatibility",
		version = "2.0", dependencies = "required-after:boatcraft;after:IronChest", modLanguage = "scala")
object IronChests
{
  //TODO: Fill Documentation
  /**
   *
   */
  var log: Logger = null

  //TODO: Fill Documentation
  /**
   *
   * @param e
   */
  @EventHandler
	def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog
		
		printModInfo
		
		try
		{
			addModifiers
			NetworkRegistry.INSTANCE registerGuiHandler(this, IronChestsGuiHandler)
			MinecraftForge.EVENT_BUS register IronChestsEventHandler
		}
		catch
		{
			case ex: NoClassDefFoundError =>			//That's OK
			case err: NoSuchMethodError =>				//Fine
			case ex: NoSuchMethodException =>			//No problem
			case thr: Throwable => thr printStackTrace	//Weird...
		}
	}

  //TODO: Fill Documentation
  /**
   *
   */
  private def printModInfo
	{
		log info "BoatCraft Iron Chests 2 Compatibility"
		log info "Adds Iron Chests 2 Chests to the BoatCraft Modifier Matrix"
		log info "Copyright Vilim Lendvaj 2014"
	}

  //TODO: Fill Documentation
  /**
   *
   */
  @Optional.Method(modid = "IronChest")
	private def addModifiers
	{
		ModifierRegistry addModifier Iron_Chest
		ModifierRegistry addModifier Gold_Chest
		ModifierRegistry addModifier Diamond_Chest
		ModifierRegistry addModifier Copper_Chest
		ModifierRegistry addModifier Silver_Chest
		ModifierRegistry addModifier Crystal_Chest
		ModifierRegistry addModifier Obsidian_Chest
		ModifierRegistry addModifier DirtChest9000
	}
}