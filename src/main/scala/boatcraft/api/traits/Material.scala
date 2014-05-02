package boatcraft.api.traits

import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

abstract class Material
{
	/**
	  * The texture path for rendering the Boat in the world
	  *
	  * @return base texture of the Material
	  */
	def getTexture: ResourceLocation = null

	/**
	  * The human-readable name of this Material.
	  * The {@link Material#toString} will make it computer-readable
	  *
	  * @return name of the Material
	  */
	def getName: String = null

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
	
	/**
	 * This function allows a material to be faster or slower than normal
	 * 
	 * @return the multiplier to apply to the speed of the boat
	 */
	def getSpeedMultiplier = 1.0
	
	/**
	 * This function allows a material to act more resistant or more fragile
	 * 
	 * @return the multiplier to apply to the boat's default crash resistance
	 */ 
	def getCrashResistance = 1.0

	/**
	  * Method is overridden to return the Material's actual name.
	  *
	  * @return name of the Material
	  */
	override def toString = getName replaceAll (" ", "") toLowerCase
}