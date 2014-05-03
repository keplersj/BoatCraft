package boatcraft.compatibility.vanilla.modifiers

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.traits.Modifier
import boatcraft.core.utilities.NBTHelper
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.entity.player.EntityPlayerMP

object Chest extends Modifier
{
	override def getBlock: Block = Blocks.chest
	
	override def getInventory(boat: EntityCustomBoat): IInventory =
		new Chest.Inventory(boat)
	
	override def getName = "Chest"
	
	override def getContent = new ItemStack(Blocks.chest)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
		if (player.isInstanceOf[EntityPlayerMP]) player displayGUIChest boat.getInventory
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper writeChestToNBT (boat.getInventory, tag)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper readChestFromNBT (boat.getInventory, tag)
	
	private class Inventory(boat: EntityCustomBoat) extends TileEntityChest
	{
		worldObj = boat.worldObj
		
		override def getInventoryName = "Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		
		override def closeInventory {}
	}
}