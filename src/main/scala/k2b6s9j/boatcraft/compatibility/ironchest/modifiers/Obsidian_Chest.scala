package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityObsidianChest
import net.minecraft.entity.player.EntityPlayer

class Obsidian_Chest extends Iron_Chest
{
	override def getMeta = IronChestType.OBSIDIAN ordinal
	
	override def getName = "Obsidian Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Obsidian_Chest.Inventory(boat)
}

object Obsidian_Chest
{
	private	class Inventory(boat: EntityBoatContainer) extends TileEntityObsidianChest
	{
		override def getInventoryName = "Obsidian Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}