package boatcraft.compatibility.vanilla.materials.crystal

import boatcraft.api.traits.Material
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

//TODO: Fill Documentation
/**
 *
 */
object Obsidian extends Material {
	//TODO: Fill Documentation
	/**
	 *
	 * @return base texture of the Material
	 */
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/obsidian.png")

	//TODO: Fill Documentation
	/**
	 *
	 * @return name of the Material
	 */
	override def getUnlocalizedName = "Obsidian"

	override def getLocalizedName = "vanilla.materials.crystal.obsidian.name"

	//TODO: Fill Documentation
	/**
	 *
	 * @return the ItemStack representing the Material
	 */
	override def getItem = new ItemStack(Blocks.obsidian)

	//TODO: Fill Documentation
	/**
	 *
	 * @return the secondary drop of the boat
	 */
	override def getStick: ItemStack = null
}