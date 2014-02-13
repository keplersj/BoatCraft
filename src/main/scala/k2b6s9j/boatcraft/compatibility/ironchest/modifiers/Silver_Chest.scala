package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntitySilverChest
import net.minecraft.entity.player.EntityPlayer

class Silver_Chest extends Iron_Chest
{
	override def getMeta = IronChestType.SILVER ordinal
	
	override def getName = "Silver Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Silver_Chest.Inventory(boat)
}

object Silver_Chest
{
	private class Inventory(boat: EntityBoatContainer) extends TileEntitySilverChest
	{
		override def getInventoryName = "Silver Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}