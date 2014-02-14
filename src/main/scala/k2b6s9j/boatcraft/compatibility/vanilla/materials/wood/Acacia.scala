package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

object Acacia extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/wood/acacia.png")
	
	override def getName = "Acacia"
	
	override def getItem = new ItemStack(Blocks.planks, 1, 4)
	override def getStick = new ItemStack(Items.stick)
}