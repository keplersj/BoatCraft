package k2b6s9j.boatcraft.core.packets

import io.netty.buffer.ByteBuf
import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.core.Boat.EntityCustomBoat
import java.util.UUID
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity
import k2b6s9j.boatcraft.core.registry.ModifierRegistry
import k2b6s9j.boatcraft.core.registry.MaterialRegistry

case class PacketBoatInfo(var material: String, var modifier: String, var id: UUID)
	extends AnyRef with IPacket
{
	//def this() = this(null, null, 0)

    def readBytes(buffer: ByteBuf)
	{
    	var bytes: Array[Byte] = new Array[Byte](buffer readByte)
    	buffer readBytes bytes
    	material = new String(bytes)
    	BoatCraft.log info material
    	bytes = new Array[Byte](buffer readByte)
    	buffer readBytes bytes
    	modifier = new String(bytes)
    	BoatCraft.log info modifier
    	
    	for (entity: Entity <- Minecraft.getMinecraft().theWorld.getLoadedEntityList().asInstanceOf[List[Entity]])
    	{
    		if (entity.getUniqueID() == id)
    		{
    			entity.asInstanceOf[EntityCustomBoat].material = MaterialRegistry getMaterial material
    			entity.asInstanceOf[EntityCustomBoat].modifier = ModifierRegistry getModifier modifier
    		}
    	}
    }

    def writeBytes(buffer: ByteBuf)
	{
    	buffer writeByte (material length)
    	buffer writeBytes (material getBytes)
    	
    	buffer writeByte (modifier length)
    	buffer writeBytes (modifier getBytes)
    }
}