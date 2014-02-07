package k2b6s9j.boatcraft.core.packets

import java.util.List
import java.util.UUID

import scala.collection.JavaConversions.asScalaBuffer

import cpw.mods.fml.common.Mod
import io.netty.buffer.ByteBuf
import k2b6s9j.boatcraft.core.Boat.EntityCustomBoat
import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.core.registry.MaterialRegistry
import k2b6s9j.boatcraft.core.registry.ModifierRegistry
import net.minecraft.client.Minecraft
import net.minecraft.entity.Entity

case class PacketBoatInfo(var material: String, var modifier: String, var id: UUID)
	extends AnyRef with IPacket
{
	def this() = this(null, null, null)

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
    	
    	id = new UUID(buffer readLong, buffer readLong)
    	BoatCraft.log info id
    	
    	for (entity: Entity <- Minecraft.getMinecraft().theWorld.getLoadedEntityList().asInstanceOf[List[Entity]])
    	{
    		if (entity.isInstanceOf[EntityCustomBoat])
    			BoatCraft.log info entity.getUniqueID()
    		if (entity.getUniqueID() == id)
    		{
    			entity.asInstanceOf[EntityCustomBoat] setMaterial(MaterialRegistry getMaterial material toString)
    			entity.asInstanceOf[EntityCustomBoat] setModifier(ModifierRegistry getModifier modifier toString)
    			BoatCraft.log info entity
    		}
    	}
    }

    def writeBytes(buffer: ByteBuf)
	{
    	buffer writeByte (material length)
    	buffer writeBytes (material getBytes)
    	
    	buffer writeByte (modifier length)
    	buffer writeBytes (modifier getBytes)
    	
    	buffer writeLong(id getMostSignificantBits)
    	buffer writeLong(id getLeastSignificantBits)
    }
}