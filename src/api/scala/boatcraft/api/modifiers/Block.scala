package boatcraft.api.modifiers

import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks

abstract class Block extends Modifier {
	/**
	 * Boolean representing whether or not the player can mount a Boat, with this Modifier, on action-click
	 *
	 * @return boolean representing if a Boat is mountable
	 */
	@inline def isRideable = false

	/**
	 * The Block to be rendered in a Boat
	 *
	 * @note NOT what's used to craft it!
	 * @return block used for rendering
	 */
	@inline def getBlock: net.minecraft.block.Block = null

	/**
	 * The metadata of the Block to be rendered in a Boat
	 *
	 * @note NOT what's used to craft it!
	 * @return the metadata of the block being rendering in the boat
	 */
	@inline def getMeta = 0

	/**
	 * Should return a new instance of the inventory contained in this Modifier
	 *
	 * @param boat the boat container object
	 * @return the inventory associated with the boat container
	 */
	@inline def getBlockData(boat: EntityCustomBoat): AnyRef = Nil

	/**
	 * The item used to craft a boat with this Block
	 * @note NOT what's rendered in the Boat!
	 *
	 * @return the ItemStack used when crafting
	 */
	@inline def getContent: ItemStack = null
}

object Block {
	
	object Empty extends Block {
		override def isRideable = true
	
		override def getBlock = Blocks.air
	
		override def getName = "Empty"
	
		override def getContent: ItemStack = null
	}
}