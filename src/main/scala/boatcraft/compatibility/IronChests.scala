package boatcraft.compatibility

import org.apache.logging.log4j.Logger

import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkRegistry
import boatcraft.api.Registry
import boatcraft.compatibility.ironchest._
import boatcraft.compatibility.ironchest.modifiers._
import net.minecraftforge.common.MinecraftForge

@Mod(modid = "boatcraft:compatibility:IronChest", name = "BoatCraft Iron Chests Compatibility",
	version = "2.0", dependencies = "required-after:boatcraft;after:IronChest", modLanguage = "scala")
object IronChests
{
	var log: Logger = null
	
	@EventHandler
	@Optional.Method(modid = "IronChest")
	def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog

		try
		{
			addModifiers
			NetworkRegistry.INSTANCE registerGuiHandler (this, IronChestsGuiHandler)
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