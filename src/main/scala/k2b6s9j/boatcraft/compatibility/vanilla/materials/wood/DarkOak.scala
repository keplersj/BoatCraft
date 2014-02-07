package k2b6s9j.boatcraft.compatibility.vanilla.boat.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

trait DarkOak extends Material
{
	override def getTexture: ResourceLocation = 
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/dark_oak.png")
	override def getName: String = "Dark Oak"
	override def getItem: ItemStack = new ItemStack(Blocks.planks, 1, 5)
	override def getStick: ItemStack = new ItemStack(Items.stick)
}