package boatcraft.compatibility.ironchest.packets

import cpw.mods.fml.common.network.simpleimpl.IMessage
import io.netty.buffer.ByteBuf

class IronChestSyncMessage(var entityID: Int, var chestType: Int, var facing: Int, var items: Array[Int])
		extends IMessage {
	
	def this() = this(0, -1, -1, null)
	
	override def fromBytes(buffer: ByteBuf)
	{
		entityID = buffer.readInt
		chestType = buffer.readInt
		facing = buffer.readInt
		
		items = new Array[Int](buffer.readInt)
		for (i <- 0 until items.length) items(i) = buffer.readInt
	}
	
	override def toBytes(buffer: ByteBuf)
	{
		buffer.writeInt(entityID).writeInt(chestType).writeInt(facing).writeInt(items.length)
		
		for (i <- 0 until items.length) buffer.writeInt(items(i))
	}
	
}