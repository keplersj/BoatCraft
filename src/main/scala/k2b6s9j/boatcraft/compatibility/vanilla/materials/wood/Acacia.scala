package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

class Acacia extends Material
{
	override def getTexture: ResourceLocation = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/wood/acacia.png")
	
	override def getName: String = "Acacia"
	
	override def getItem: ItemStack = new ItemStack(Blocks.planks, 1, 4)
	override def getStick: ItemStack = new ItemStack(Items.stick)
}