package boatcraft.compatibility.vanilla.materials.crystal

import boatcraft.api.traits.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items

//TODO: Fill Documentation
/**
 *
 */
object Emerald extends Material {
	//TODO: Fill Documentation
	/**
	 *
	 * @return base texture of the Material
	 */
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/emerald.png")

	//TODO: Fill Documentation
	/**
	 *
	 * @return name of the Material
	 */
	override def getUnlocalizedName = "Emerald"
	
	override def getLocalizedName = "vanilla.materials.crystal.emerald.name"

	//TODO: Fill Documentation
	/**
	 *
	 * @return the ItemStack representing the Material
	 */
	override def getItem = new ItemStack(Items.emerald)

	//TODO: Fill Documentation
	/**
	 *
	 * @return the secondary drop of the boat
	 */
	override def getStick: ItemStack = null
}