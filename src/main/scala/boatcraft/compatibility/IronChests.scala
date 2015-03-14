package boatcraft.compatibility

import boatcraft.api.modifiers.Block
import boatcraft.compatibility.ironchest.IronChestsEventHandler
import boatcraft.compatibility.ironchest.IronChestsGuiHandler
import boatcraft.compatibility.ironchest.modifiers.blocks._
import boatcraft.compatibility.ironchest.packets.IronChestSyncMessage
import boatcraft.core.BoatCraft
import boatcraft.core.GUIHandler
import boatcraft.core.utilities.Helper
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.relauncher.Side
import net.minecraftforge.common.MinecraftForge

object IronChests extends CompatModule {
	
	@Optional.Method(modid = "IronChest")
	override protected def doPreInit(e: FMLPreInitializationEvent) {
		
		GUIHandler.handlerMap.put(code, IronChestsGuiHandler)
		
		MinecraftForge.EVENT_BUS register IronChestsEventHandler
		
		BoatCraft.channel.registerMessage(classOf[GenericIronChest.MessageHandler], classOf[IronChestSyncMessage],
				Helper.Packet.getNextID, Side.CLIENT)
	}
	
	@Optional.Method(modid = "IronChest")
	override protected def getBlocks = Array[Block](
			Iron_Chest,
			Gold_Chest,
			Diamond_Chest,
			Copper_Chest,
			Silver_Chest,
			Crystal_Chest,
			Obsidian_Chest,
			DirtChest9000)
}