package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityDirtChest
import net.minecraft.entity.player.EntityPlayer

class DirtChest9000 extends Iron_Chest
{
	override def getMeta = IronChestType.DIRTCHEST9000 ordinal
	
	override def getName = "DirtChest9000"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new DirtChest9000.Inventory(boat)
}

object DirtChest9000
{
	private class Inventory(boat: EntityBoatContainer) extends TileEntityDirtChest
	{
		override def getInventoryName = "DirtBoat9000!"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}