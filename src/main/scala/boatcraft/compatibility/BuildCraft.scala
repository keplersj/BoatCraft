package boatcraft.compatibility

import boatcraft.api.modifiers.Block
import boatcraft.compatibility.buildcraft.modifiers.blocks.Tank
import boatcraft.compatibility.buildcraft.packets.TankSyncMessage
import boatcraft.core.BoatCraft
import boatcraft.core.utilities.Helper
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.relauncher.Side

object BuildCraft extends CompatModule("BuildCraft|Factory") {
	
	@Optional.Method(modid = "BuildCraft|Factory")
	override protected def doPreInit(event: FMLPreInitializationEvent)
	{
		BoatCraft.channel.registerMessage(classOf[Tank.MessageHandler], classOf[TankSyncMessage],
				Helper.Packet.getNextID, Side.CLIENT)
	}
	
	@Optional.Method(modid = "BuildCraft|Factory")
	override protected def getBlocks = Array[Block](
			Tank)
}