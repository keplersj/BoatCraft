package boatcraft

import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.{Block, Material}

/** The main API used by all BoatCraft Content Modules and Add-ons.
	*
	* This API Package contains all of the methods needed to create new Materials,
	* create new Modifiers, and register anything you create with BoatCraft: Core to implement.
	*/
package object api {
	/**
	 * The function used to retrieve a boat item with
	 * the specified material and modifier
	 *
	 * @param mat the Material
	 * @param block the Block inside the Boat
	 *
	 * @return the custom boat itemstack
	 */
	def getCustomBoat(mat: Material, block: Block): ItemStack =
		getCustomBoat(mat toString, block toString)

	/**
	 * The function used to retrieve a boat item with
	 * the specified material and modifier
	 *
	 * @param mat the Material
	 * @param block the Block inside the Boat
	 *
	 * @return the custom boat ItemStack
	 */
	def getCustomBoat(mat: String, block: String) = {
		val stack = new ItemStack(ItemCustomBoat)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound.setString("material", mat)
		stack.stackTagCompound.setString("block", block)

		stack
	}
}