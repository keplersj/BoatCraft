package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityDiamondChest
import net.minecraft.entity.player.EntityPlayer

class Diamond_Chest extends Iron_Chest
{
	override def getMeta = IronChestType.DIAMOND ordinal
	
	override def getName = "Diamond Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Diamond_Chest.Inventory(boat)
}

object Diamond_Chest
{
	private class Inventory(boat: EntityBoatContainer) extends TileEntityDiamondChest
	{
		override def getInventoryName = "Diamond Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}