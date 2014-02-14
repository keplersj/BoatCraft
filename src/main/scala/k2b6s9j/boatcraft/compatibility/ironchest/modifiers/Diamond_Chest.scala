package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.IronChestType
import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import net.minecraft.inventory.IInventory

object Diamond_Chest extends GenericIronChest
{
	override def getMeta = IronChestType.DIAMOND ordinal
	
	override def getName = "Diamond Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)
	
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.DIAMOND)
	{
		override def getInventoryName = getName + " Boat"
	}
}