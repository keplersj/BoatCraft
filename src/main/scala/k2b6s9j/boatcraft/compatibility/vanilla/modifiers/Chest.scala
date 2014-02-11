package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.core.Boat.{EntityCustomBoat, EntityBoatContainer}
import k2b6s9j.boatcraft.core.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraftforge.common.util.Constants
import cpw.mods.fml.relauncher.ReflectionHelper
import java.lang.reflect.Field
import k2b6s9j.boatcraft.core.BoatCraft

class Chest extends Modifier
{
	override def getBlock: Block = Blocks.chest
	override def getMeta = 0

	override def hasInventory = true
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Chest.Inventory(boat)
	
	override def getName = "Chest"
	override def getContent = new ItemStack(Blocks.chest)

	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
		//player openGui(Vanilla, 0, player.worldObj, 0, 0, 0)
		player displayGUIChest boat.asInstanceOf[IInventory]
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound)
	{
		var container = boat.asInstanceOf[EntityBoatContainer]
		
		var list = new NBTTagList
		for (i: Int <- 0 until container.getInventory.getSizeInventory)
		{
			if (container.getInventory.getStackInSlot(i) != null)
			{
				var _tag = new NBTTagCompound
				container.getInventory.getStackInSlot(i) writeToNBT _tag
				_tag setByte ("Slot", i.toByte)
				list appendTag _tag
			}
		}
		tag.setTag("Items", list)
	}
	
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound)
	{
		var list = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND)
		
		for (i: Int <- 0 until list.tagCount)
		{
			val _tag = list getCompoundTagAt i
			boat.asInstanceOf[EntityBoatContainer] setInventorySlotContents(
				_tag getByte "Slot",
				ItemStack loadItemStackFromNBT _tag)
		}
	}
}

object Chest
{
	private[compatibility] class Inventory(boat: EntityBoatContainer, size: Int) extends TileEntityChest
	{
		worldObj = boat.worldObj
		
		ReflectionHelper setPrivateValue(classOf[TileEntityChest], this,
				new Array[ItemStack](size), "chestContents")
		
		def this(boat: EntityBoatContainer) = this(boat, 27)
		
		override def getSizeInventory = size
		
		override def getInventoryName = "Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}