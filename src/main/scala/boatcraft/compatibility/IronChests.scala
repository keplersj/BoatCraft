package boatcraft.compatibility

import java.util.{Arrays, List}

import boatcraft.api.traits.Modifier
import boatcraft.compatibility.ironchest.{IronChestsEventHandler, IronChestsGuiHandler}
import boatcraft.compatibility.ironchest.modifiers._
import boatcraft.core.GUIHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.MinecraftForge

object IronChests extends CompatModule
{
	override protected def doPreInit(e: FMLPreInitializationEvent)
	{
		GUIHandler.handlerMap.put(code, IronChestsGuiHandler)
		MinecraftForge.EVENT_BUS register IronChestsEventHandler
	}
	
	override protected def getModifiers: List[Modifier] =
		Arrays.asList(
				Iron_Chest,
				Gold_Chest,
				Diamond_Chest,
				Copper_Chest,
				Silver_Chest,
				Crystal_Chest,
				Obsidian_Chest,
				DirtChest9000)
}