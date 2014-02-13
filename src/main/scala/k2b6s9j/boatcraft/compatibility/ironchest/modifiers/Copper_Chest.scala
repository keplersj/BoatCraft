package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityCopperChest
import net.minecraft.entity.player.EntityPlayer

class Copper_Chest extends Iron_Chest
{
	override def getMeta = IronChestType.COPPER ordinal
	
	override def getName = "Copper Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Copper_Chest.Inventory(boat)
}

object Copper_Chest
{
	private class Inventory(boat: EntityBoatContainer) extends TileEntityCopperChest
	{
		override def getInventoryName = "Copper Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}