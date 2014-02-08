package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

trait Spruce extends Material
{
	override def getTexture: ResourceLocation = 
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/spruce.png")
	override def getName: String = "Spruce"
	override def getItem: ItemStack = new ItemStack(Blocks.planks, 1,  1)
	override def getStick: ItemStack = new ItemStack(Items.stick)
}