package k2b6s9j.boatcraft.core.traits

import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack

abstract class Material
{
	/**
	 * The texture path for rendering the Boat in the world
	 */
	def getTexture: ResourceLocation = null
	/**
	 * The human-readable name of this Material.
	 * The {@link Material#toString} will make it computer-readable
	 */
	def getName: String = null
	/**
	 * The item used in the crafting recipe.
	 * Also the primary drop when the boat crashes
	 */
	def getItem: ItemStack = null
	/**
	 * The secondary drop
	 */
	def getStick: ItemStack = null
	
	override def toString = getName replaceAll(" ", "") toLowerCase
}