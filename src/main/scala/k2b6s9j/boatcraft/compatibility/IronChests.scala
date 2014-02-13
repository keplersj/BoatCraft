package k2b6s9j.boatcraft.compatibility

import org.apache.logging.log4j.Logger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers._
import cpw.mods.fml.common.network.NetworkRegistry
import k2b6s9j.boatcraft.compatibility.ironchest.IronChestsGuiHandler

@Mod(modid = "boatcraft:compatibility:IronChest", name = "BoatCraft Iron Chests 2 Compatibility",
		version = "2.0", dependencies = "required-after:boatcraft;after:IronChest", modLanguage = "scala")
object IronChests
{
	var log: Logger = null
	
	@EventHandler
	def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog
		
		printModInfo
		
		try
		{
			addModifiers
			NetworkRegistry.INSTANCE registerGuiHandler(this, IronChestsGuiHandler)
		}
		catch
		{
			case thr: Throwable => thr printStackTrace
		}
	}
	
	private def printModInfo
	{
		log info "BoatCraft Iron Chests 2 Compatibility"
		log info "Adds Iron Chests 2 Chests to the BoatCraft Modifier Matrix"
		log info "Copyright Vilim Lendvaj 2014"
	}
	
	@Optional.Method(modid = "IronChest")
	private def addModifiers
	{
		ModifierRegistry addModifier new Iron_Chest
		ModifierRegistry addModifier new Gold_Chest
		ModifierRegistry addModifier new Diamond_Chest
		ModifierRegistry addModifier new Copper_Chest
		ModifierRegistry addModifier new Silver_Chest
		ModifierRegistry addModifier new Crystal_Chest
		ModifierRegistry addModifier new Obsidian_Chest
		ModifierRegistry addModifier new DirtChest9000
	}
}