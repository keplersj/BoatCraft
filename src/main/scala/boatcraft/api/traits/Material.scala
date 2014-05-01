package boatcraft.api.traits

import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

abstract class Material extends Modifier
{
	/**
	  * The texture path for rendering the Boat in the world
	  *
	  * @return base texture of the Material
	  */
	def getTexture: ResourceLocation = null

	/**
	  * The item used in the crafting recipe.
	  * Also the primary drop when the boat crashes
	  *
	  * @return the ItemStack representing the Material
	  */
	def getItem: ItemStack = null

	/**
	  * The secondary drop when the boat crashes
	  * For Wood-based boats, this is the associated wood's sticks.
	  * For Metallic boats, this is the nugget form of the metal.
	  * For other Material types is either null or the small tier of the base material
	  * (ie: stone rods, diamond nuggets, flint, etc)
	  *
	  * @return the secondary drop of the boat
	  */
	def getStick: ItemStack = null

}