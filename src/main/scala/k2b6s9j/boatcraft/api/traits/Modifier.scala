package k2b6s9j.boatcraft.api.traits

import k2b6s9j.boatcraft.api.boat.EntityCustomBoat
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

abstract class Modifier
{
  /** Boolean representing whether or not the player can mount a Boat, with this Modifier, on action-click
	*
	* @return boolean representing if a Boat is mountable
	*/
	def isRideable = false

  /** The Block to be rendered in a Boat with this Modifier
	*
	* @note NOT what's used to craft it!
	* @return block used for rendering
	*/
	def getBlock: Block = null

  /** The metadata of the Block to be rendered in a Boat with this Modifier
	*
	* @note NOT what's used to craft it!
	* @return the metadata of the block being rendering in the boat
	*/
	def getMeta = 0

  /** Should return a new instance of the inventory contained in this Modifier
	*
	* @param boat the boat container object
	* @return the inventory associated with the boat container
	*/
	def getInventory(boat: EntityCustomBoat): IInventory =
		EntityCustomBoat.NoInventory

  /** The human-readable name of this Modifier.
	* The {@link Modifier#toString} will make it computer-readable
	*
	* @return the name of the Modifier
	*/
	def getName: String = null

  /** The item used to craft a boat with this Modifier
	* @note NOT what's rendered in the Boat!
	*
	* @return the ItemStack used when crafting
	*/
	def getContent: ItemStack = null

  /** Open's the corresponding GUI for this Modifier, if any
	*
	* @param player the player interacting with the Boat
	* @param boat the Boat being interacted with
	*/
	def interact(player: EntityPlayer, boat: EntityCustomBoat) {}

  /** Update the modifier's logic
	*
	* @param boat the boat being updated
	*/
	def update(boat: EntityCustomBoat) {}

  /** Save the modifier's state to NBT
	*
	* @param boat the boat entity NBT data is being written to
	* @param tag the NBT data tag being written
	*/
	def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) {}

  /** Load the modifier's state from NBT
	*
	* @param boat the boat entity NBT data is being read from
	* @param tag the NBT data tag being read
	*/
	def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) {}

  /** Method is overridden to return the Modifier's actual name.
	*
	* @return name of the Modifier
	*/
  override def toString = getName replaceAll(" ", "") toLowerCase
}