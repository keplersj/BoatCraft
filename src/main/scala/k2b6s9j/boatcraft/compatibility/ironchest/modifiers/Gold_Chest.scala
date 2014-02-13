package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.{IronChestType, TileEntityGoldChest}
import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory

class Gold_Chest extends Iron_Chest
{
	override def getMeta = IronChestType.GOLD ordinal
	
	override def getName = "Gold Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Gold_Chest.Inventory(boat)
}

object Gold_Chest
{
	private class Inventory(boat: EntityBoatContainer) extends TileEntityGoldChest
	{
		override def getInventoryName = "Gold Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}