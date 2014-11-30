package boatcraft.compatibility.ironchest.packets

import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import io.netty.buffer.ByteBuf
import net.minecraft.item.ItemStack
import net.minecraft.item.Item

class IronChestSyncMessage(var entityID: Int, var chestType: Int, var facing: Int, var items: Array[ItemStack])
		extends IMessage {
	
	def this() = this(0, -1, -1, null)
	
	override def fromBytes(buffer: ByteBuf)
	{
		entityID = buffer.readInt
		chestType = buffer.readInt
		facing = buffer.readInt
		
		items = new Array[ItemStack](buffer.readInt)
		for (i <- 0 until items.length)
			items(i) = new ItemStack(Item.getItemById(buffer.readInt), buffer.readInt, buffer.readInt)
	}
	
	override def toBytes(buffer: ByteBuf)
	{
		buffer.writeInt(entityID).writeInt(chestType).writeInt(facing).writeInt(items.length)
		
		for (i <- 0 until items.length)
		{
			buffer.writeInt(Item.getIdFromItem(items(i).getItem))
			buffer.writeInt(items(i).stackSize)
			buffer.writeInt(items(i).getItemDamage)
		}
	}
	
}