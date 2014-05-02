package boatcraft.compatibility

import org.apache.logging.log4j.Logger
import boatcraft.api.Registry
import boatcraft.compatibility.ironchest.{IronChestsEventHandler, IronChestsGuiHandler}
import boatcraft.compatibility.ironchest.modifiers.{Copper_Chest, Crystal_Chest, Diamond_Chest, DirtChest9000, Gold_Chest, Iron_Chest, Obsidian_Chest, Silver_Chest}
import boatcraft.core.BoatCraft
import cpw.mods.fml.common.{DummyModContainer, Mod, Optional}
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkRegistry
import net.minecraftforge.common.MinecraftForge
import cpw.mods.fml.common.ModMetadata
import boatcraft.core.GUIHandler

object IronChests extends CompatModule
{
	var log: Logger = null
	
	@Optional.Method(modid = "IronChest")
	override def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog

		try
		{
			addModifiers
			GUIHandler.handlerMap.put(code, IronChestsGuiHandler)
			//MinecraftForge.EVENT_BUS register IronChestsEventHandler
		}
		catch
		{
			case ex: NoClassDefFoundError => //That's OK
			case err: NoSuchMethodError => //Fine
			case ex: NoSuchMethodException => //No problem
			case ex: NullPointerException => //Sure
			case thr: Throwable => thr printStackTrace //Weird...
		}
	}
	
	@Optional.Method(modid = "IronChest")
	private def addModifiers
	{
		Registry register Iron_Chest
		Registry register Gold_Chest
		Registry register Diamond_Chest
		Registry register Copper_Chest
		Registry register Silver_Chest
		Registry register Crystal_Chest
		Registry register Obsidian_Chest
		Registry register DirtChest9000
	}
}