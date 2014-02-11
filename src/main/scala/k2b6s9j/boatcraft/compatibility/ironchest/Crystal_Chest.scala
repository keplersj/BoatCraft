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

class Crystal_Chest extends Chest
{
	override def getBlock: Block = IronChest.ironChestBlock
	override def getMeta = IronChestType.CRYSTAL ordinal
	
	override def getName = "Crystal Chest"
	override def getContent = new ItemStack(getBlock, 1, getMeta)
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Crystal_Chest.Inventory(boat)
}

object Crystal_Chest extends Chest
{
	private[Crystal_Chest] class Inventory(boat: EntityBoatContainer) extends Chest.Inventory(boat, 108)
	{
		override def getInventoryName = "Crystal Chest Boat"
	}
}