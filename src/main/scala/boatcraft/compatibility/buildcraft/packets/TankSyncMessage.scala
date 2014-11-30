package boatcraft.compatibility.buildcraft.packets

import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import io.netty.buffer.ByteBuf

class TankSyncMessage(var entityID: Int, var fluidID: Int, var amount: Int, var color: Int) extends IMessage {
	
	def this() = this(0, 0, 0, 0xFFFFFF)
	
	override def fromBytes(buffer: ByteBuf)
	{
		entityID = buffer.readInt
		fluidID = buffer.readInt
		amount = buffer.readInt
		color = buffer.readInt
	}
	
	override def toBytes(buffer: ByteBuf)
	{
		buffer.writeInt(entityID).writeInt(fluidID).writeInt(amount).writeInt(color)
	}
	
}