package boatcraft.api.modifiers

import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack

abstract class Block extends Modifier
{
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
	@inline def getBlock: IBlockState = null

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