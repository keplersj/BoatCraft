package boatcraft.compatibility

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkRegistry
import boatcraft.api.Registry
import boatcraft.compatibility.ironchest._
import boatcraft.compatibility.ironchest.blocks._
import net.minecraftforge.common.MinecraftForge
import boatcraft.core.BoatCraft

object IronChests extends CompatModule
{
	var log: Logger = null
	
	@Optional.Method(modid = "IronChest")
	override def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog

		try
		{
			addBlocks
			NetworkRegistry.INSTANCE registerGuiHandler (BoatCraft, IronChestsGuiHandler)
			MinecraftForge.EVENT_BUS register IronChestsEventHandler
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
	private def addBlocks
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