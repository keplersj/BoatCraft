package k2b6s9j.boatcraft.api.traits

import k2b6s9j.boatcraft.core.Boat.{EntityCustomBoat, EntityBoatContainer}
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

abstract class Modifier
{
	/**
	 * Whether the player can enter a Boat with this Modifier
	 */
	def isRideable = false
	
	/**
	 * The Block to be rendered in a Boat with this Modifier
	 * @note NOT what's used to craft it!
	 */
	def getBlock: Block = null
	/**
	 * The metadata of the Block to be rendered in a Boat with this Modifier
	 * @note NOT what's used to craft it!
	 */
	def getMeta = 0
	
	/**
	 * Whether or not a boat with this modifier should hld an inventory
	 */
	def hasInventory = false
	
	/**
	 * Should return a new instance of the inventory contained in this Modifier
	 */
	def getInventory(boat: EntityBoatContainer): IInventory = null 
	
	/**
	 * The human-readable name of this Modifier.
	 * The {@link Modifier#toString} will make it computer-readable
	 */
	def getName: String = null
	/**
	 * The item used to craft a boat with this Modifier
	 * @note NOT what's rendered in the Boat!
	 */
	def getContent: ItemStack = null
	
	/**
	 * Open's the corresponding GUI for this Modifier, if any
	 */
	def interact(player: EntityPlayer, boat: EntityCustomBoat) {}
	
	/**
	 * Update the modifier's logic
	 */
	def update(boat: EntityCustomBoat) {}
	
	/**
	 * Save the modifier's state to NBT
	 */
	def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) {}
	
	/**
	 * Load the modifier's state from NBT
	 */
	def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) {}
	
	override def toString = getName replaceAll(" ", "") toLowerCase
}