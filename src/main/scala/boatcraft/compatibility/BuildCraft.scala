package boatcraft.compatibility

import boatcraft.api.modifiers.Block
import boatcraft.compatibility.buildcraft.modifiers.blocks.Tank
import boatcraft.compatibility.buildcraft.packets.TankSyncMessage
import boatcraft.core.BoatCraft
import boatcraft.core.utilities.Helper
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Optional
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.relauncher.Side

object BuildCraft extends CompatModule {
	
	/*@Optional.Method(modid = "BuildCraft|Factory")
	override protected def doPreInit(event: FMLPreInitializationEvent)
	{
		BoatCraft.channel.registerMessage(classOf[Tank.MessageHandler], classOf[TankSyncMessage],
				Helper.Packet.getNextID, Side.CLIENT)
	}
	
	@Optional.Method(modid = "BuildCraft|Factory")
	override protected def getBlocks = Array[Block](
			Tank)*/
}