package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.init.Items

trait Oak extends Material
{
	override def getTexture: ResourceLocation =
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/oak.png")
	override def getName: String = "Oak"
	override def getItem: ItemStack = new ItemStack(Blocks.planks)
	override def getStick: ItemStack = new ItemStack(Items.stick)
}