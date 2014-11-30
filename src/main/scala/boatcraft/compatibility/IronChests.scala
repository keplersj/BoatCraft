package boatcraft.compatibility

import boatcraft.compatibility.ironchest.{IronChestsEventHandler, IronChestsGuiHandler}
import boatcraft.core.GUIHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.MinecraftForge
import boatcraft.compatibility.ironchest.modifiers.blocks._
import boatcraft.api.modifiers.Block
import boatcraft.core.BoatCraft
import boatcraft.compatibility.ironchest.packets.IronChestSyncMessage
import net.minecraftforge.fml.relauncher.Side
import boatcraft.core.utilities.Helper
import net.minecraftforge.fml.common.Optional

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