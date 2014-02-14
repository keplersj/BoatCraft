package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.{IronChest, IronChestType, TileEntityIronChest}
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Chest
import k2b6s9j.boatcraft.api.Boat.{EntityBoatContainer, EntityCustomBoat}
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import k2b6s9j.boatcraft.compatibility.IronChests
import cpw.mods.fml.common.Mod

class Iron_Chest extends Chest
{
	override def getBlock: Block = IronChest.ironChestBlock
	override def getMeta = IronChestType.IRON ordinal
	
	override def getName = "Iron Chest"
	override def getContent = new ItemStack(getBlock, 1, getMeta)
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Iron_Chest.Inventory(boat)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
		player openGui(IronChests, getMeta, player.worldObj,
				boat.posX.round toInt, boat.posY.round toInt, boat.posZ.round toInt)
}

object Iron_Chest extends Chest
{
	private class Inventory(boat: EntityBoatContainer) extends TileEntityIronChest
	{
		override def getInventoryName = "Iron Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}