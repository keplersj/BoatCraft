package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

object Birch extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/birch.png")
	
	override def getName = "Birch"
	
	override def getItem = new ItemStack(Blocks.planks, 1,  2)
	override def getStick = new ItemStack(Items.stick)
}