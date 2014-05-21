package boatcraft.compatibility.buildcraft.packets

import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import cpw.mods.fml.common.FMLCommonHandler
import boatcraft.api.boat.EntityCustomBoat
import boatcraft.compatibility.buildcraft.modifiers.blocks.Tank
import net.minecraftforge.fluids.FluidStack
import boatcraft.core.BoatCraft

class TankSyncMessageHandler extends IMessageHandler[TankSyncMessage, IMessage] {
	
	override def onMessage(message: TankSyncMessage, context: MessageContext): IMessage =
	{
		/*BoatCraft.log.info(String.format("Recieving Tank sync data:\n\t%s %s %s %s",
				message.entityID.toString,
				message.fluidID.toString, message.amount.toString, message.color.toString))*/
		
		var boat = FMLCommonHandler.instance.getMinecraftServerInstance.getEntityWorld.getEntityByID(message.entityID)
					.asInstanceOf[EntityCustomBoat]
		var logic = boat.getBlockData.asInstanceOf[Tank.Logic]
		
		logic.tank.setFluid(new FluidStack(message.fluidID, message.amount))
		logic.tank.colorRenderCache = message.color
		
		return null
	}
}