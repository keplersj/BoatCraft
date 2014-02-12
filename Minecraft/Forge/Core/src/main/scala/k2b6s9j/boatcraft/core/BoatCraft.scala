package k2b6s9j.boatcraft.core

import org.apache.logging.log4j.Logger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.registry.GameRegistry
import java.util.EnumMap
import k2b6s9j.boatcraft.core.Boat.ItemCustomBoat
import k2b6s9j.boatcraft.core.Proxy.CommonProxy
import k2b6s9j.boatcraft.core.materials.Empty
import k2b6s9j.boatcraft.core.registry.ModifierRegistry
import cpw.mods.fml.common.network.FMLEmbeddedChannel
import cpw.mods.fml.relauncher.Side
import k2b6s9j.boatcraft.core.packets.ChannelHandler
import net.minecraft.item.Item
import net.minecraft.init.Items
import cpw.mods.fml.relauncher.IFMLLoadingPlugin
import k2b6s9j.boatcraft.core.utilities.Recipes

@Mod(modid = "boatcraft", name = "BoatCraft", version = "2.0", modLanguage = "scala")
object BoatCraft
{
	@SidedProxy(modId = "boatcraft",
		clientSide = "k2b6s9j.boatcraft.core.Proxy$ClientProxy",
	    serverSide = "k2b6s9j.boatcraft.core.Proxy$CommonProxy")
	var proxy: CommonProxy = null
	
	var channels: EnumMap[Side, FMLEmbeddedChannel] = null
	
	var log: Logger = null

	var itemBoat: ItemCustomBoat = null
	
	@EventHandler
	def preInit(event: FMLPreInitializationEvent)
	{
		log = event getModLog
		
		channels = NetworkRegistry.INSTANCE.newChannel("boatcraft", new ChannelHandler());
		
		printModInfo
		
		//All Boat Materials should be at least rideable
		ModifierRegistry addModifier new Empty
		
		itemBoat = new ItemCustomBoat()
		
		GameRegistry registerItem(BoatCraft.itemBoat, "customBoat")
	}
	
	@EventHandler
	def init(event: FMLInitializationEvent)
	{
		proxy registerEntities
		
		proxy registerRenderers
		
		Recipes addBoatRecipes
	}
	
	private def printModInfo
	{
		BoatCraft.log.info("BoatCraft")
		BoatCraft.log.info("Copyright Kepler Sticka-Jones 2013-2014")
		BoatCraft.log.info("http://k2b6s9j.com/projects/minecraft/BoatCraft")
	}
}
