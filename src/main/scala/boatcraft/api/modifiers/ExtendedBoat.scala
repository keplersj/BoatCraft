package boatcraft.api.modifiers

import java.util.HashMap

import boatcraft.core.BoatCraft

import scala.collection.JavaConversions.asScalaSet

class ExtendedBoat extends IExtendedEntityProperties {
	
	var mountables = new HashMap[Mountable.Position, String]
	
	def init(boat: Entity, world: World)
	{
	}
	
	def loadNBTData(tag: NBTTagCompound)
	{
		BoatCraft.log info "Loading Extended Boat data!"
		var list = tag.getTagList("mountables", Constants.NBT.TAG_COMPOUND)
		for (i <- 0 until list.tagCount)
		{
			var nbt = list.getCompoundTagAt(i)
			mountables.put(Mountable.Position withName nbt.getString("pos"), nbt.getString("name"))
		}
	}
	
	def saveNBTData(tag: NBTTagCompound)
	{
		BoatCraft.log info "Saving Extended Boat data!"
		var list = new NBTTagList
		for (entry <- mountables.entrySet)
		{
			var nbt = new NBTTagCompound
			nbt.setString("pos", entry.getKey toString)
			nbt.setString("name", entry.getValue)
			list appendTag nbt
		}
		
		tag.setTag("mountables", list)
	}
	
	def getMount(pos: Mountable.Position) = mountables get pos
	
	def setMount(pos: Mountable.Position, mount: String) = mountables.put(pos, mount)
}

object ExtendedBoat {
	val NAME = "BoatCraft-ExtendedBoatProps"
}