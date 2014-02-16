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
import k2b6s9j.boatcraft.api.Boat
import net.minecraft.item.Item
import net.minecraftforge.common.config.Configuration

/**
  * The main BoatCraft object.
  * Used for the required FML operations.
  */
@Mod(modid = "boatcraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{

  /**
   * Variable referencing the proxies.
   * FML dynamically sets which Proxy to use at runtime, depending on if the mod is being run on a client or a server.
   */
  @SidedProxy(modId = "boatcraft",
		clientSide = "k2b6s9j.boatcraft.core.Proxy$ClientProxy",
	    serverSide = "k2b6s9j.boatcraft.core.Proxy$CommonProxy")
	var proxy: Proxy.CommonProxy = null

  /**
   * Variable referencing BoatCraft Configuration file.
   */
  private[boatcraft] var config: Configuration = null

  /**
   * Variable referencing the Side being run on, and the channels associated with it.
   */
  var channels: EnumMap[Side, FMLEmbeddedChannel] = null

  /**
   *  Variable referencing the Logger provided by Forge Mod Loader.
   */
	private[boatcraft] var log: Logger = null

  /**
   * Variable referencing the Boat Item.
   */
  var itemBoat: Boat.ItemCustomBoat = null

  /**
   * Method used by Forge Mod Loader in the Pre-Initialization phase.
   *
   * @param event the pre-initialization event passed by fml
   */
  @EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog
		
		config = new Configuration(event getSuggestedConfigurationFile)
		
		channels = NetworkRegistry.INSTANCE newChannel("boatcraft", ChannelHandler);
		
		printModInfo
		
		//All Boat Materials should be at least rideable
		ModifierRegistry addModifier new Empty
		
		itemBoat = new Boat.ItemCustomBoat
		
		GameRegistry registerItem(itemBoat, "customBoat")
		
		log info (Item.itemRegistry getNameForObject itemBoat)
	}

  /**
   * Method used by Forge Mod Loader in the Initialization phase
   *
   * @param event the initialization event passed by fml
   */
  @EventHandler
	def init(event: FMLInitializationEvent)
	{
		proxy registerEntities
		
		proxy registerRenderers
		
		Recipes addBoatRecipes
	}

  /**
   * Method used to print mod name, copyright information, and miscellaneous mod information to the console and log.

  def printModInfo
	{
		log info "BoatCraft"
		log info "Copyright Kepler Sticka-Jones 2013-2014"
		log info "http://k2b6s9j.com/projects/minecraft/BoatCraft"
	}
}

