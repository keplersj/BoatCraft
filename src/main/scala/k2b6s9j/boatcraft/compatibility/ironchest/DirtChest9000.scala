package k2b6s9j.boatcraft.compatibility.ironchest

import cpw.mods.ironchest.IronChest
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Chest
import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import net.minecraft.block.Block
import cpw.mods.ironchest.IronChestType
import net.minecraft.item.ItemStack
import net.minecraft.inventory.IInventory
import cpw.mods.fml.relauncher.ReflectionHelper
import net.minecraft.tileentity.TileEntityChest

class DirtChest9000 extends Chest
{
	override def getBlock: Block = IronChest.ironChestBlock
	override def getMeta = IronChestType.DIRTCHEST9000 ordinal
	
	override def getName = "DirtChest9000"
	override def getContent = new ItemStack(getBlock, 1, getMeta)
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new DirtChest9000.Inventory(boat)
}

object DirtChest9000 extends Chest
{
	private class Inventory(boat: EntityBoatContainer) extends Chest.Inventory(boat, 1)
	{
		override def getInventoryName = "DirtBoat9000!"
	}
}