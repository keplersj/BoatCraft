package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityDirtChest
import net.minecraft.entity.player.EntityPlayer

object DirtChest9000 extends GenericIronChest
{
	override def getMeta = IronChestType.CRYSTAL ordinal
	
	override def getName = "DirtChest9000"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)
	
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.DIRTCHEST9000)
	{
		override def getInventoryName = "DirtBoat9000!"
	}
}