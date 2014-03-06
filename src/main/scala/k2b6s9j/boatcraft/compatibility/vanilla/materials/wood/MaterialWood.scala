package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class MaterialWood(meta: Int, name: String) extends Material
{
	//TODO: Fill Documentation
	/**
	  *
	  * @return base texture of the Material
	  */
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/wood/" +
			name.toLowerCase.replace(' ', '_') + ".png")

	//TODO: Fill Documentation
	/**
	  *
	  * @return name of the Material
	  */
	override def getName = name

	//TODO: Fill Documentation
	/**
	  *
	  * @return the ItemStack representing the Material
	  */
	override def getItem = new ItemStack(Blocks.planks, 1, meta)

	//TODO: Fill Documentation
	/**
	  *
	  * @return the secondary drop of the boat
	  */
	override def getStick = new ItemStack(Items.stick)
}