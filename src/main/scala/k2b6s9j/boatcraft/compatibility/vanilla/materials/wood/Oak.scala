package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.init.Items

object Oak extends Material
{
	override def getTexture =
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/oak.png")
	
	override def getName = "Oak"
	
	override def getItem = new ItemStack(Blocks.planks)
	override def getStick = new ItemStack(Items.stick)
}