package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.IronChestType
import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import net.minecraft.inventory.IInventory

object Crystal_Chest extends GenericIronChest
{
	override def getMeta = IronChestType.CRYSTAL ordinal
	
	override def getName = "Crystal Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)
	
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.CRYSTAL)
	{
		override def getInventoryName = getName + " Boat"
	}
}