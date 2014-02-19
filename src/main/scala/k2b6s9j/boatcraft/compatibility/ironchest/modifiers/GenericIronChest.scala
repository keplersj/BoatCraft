package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.fml.common.Mod
import cpw.mods.ironchest.IronChest
import k2b6s9j.boatcraft.api.Boat
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.compatibility.IronChests
import k2b6s9j.boatcraft.core.utilities.NBTHelper
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.item.ItemStack
import cpw.mods.ironchest.TileEntityIronChest
import cpw.mods.ironchest.IronChestType

abstract class GenericIronChest(chestType: IronChestType) extends Modifier
{
	override def getBlock: Block = IronChest.ironChestBlock
	override def getMeta = chestType ordinal
	
	override def getContent = new ItemStack(getBlock, 1, getMeta)
	
	override def getName = chestType friendlyName
	
	override def hasInventory = true
	override def getInventory(boat: Boat.EntityBoatContainer): IInventory =
		new GenericIronChest.Inventory(boat, chestType)
	
	override def writeStateToNBT(boat: Boat.EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper writeChestToNBT(boat.asInstanceOf[IInventory], tag)
	
	override def readStateFromNBT(boat: Boat.EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper readChestFromNBT(boat.asInstanceOf[IInventory], tag)
	
	override def interact(player: EntityPlayer, boat: Boat.EntityCustomBoat) =
		player openGui(IronChests, getMeta, player.worldObj,
				boat.posX.floor toInt, boat.posY.floor toInt, boat.posZ.floor toInt)
}

object GenericIronChest
{
	private class Inventory(boat: Boat.EntityBoatContainer, t: IronChestType)
		extends TileEntityIronChest(t)
	{
		worldObj = boat.worldObj
		
		override def hasCustomInventoryName = false
		
		override def getInventoryName = t.friendlyName + " Boat"
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}