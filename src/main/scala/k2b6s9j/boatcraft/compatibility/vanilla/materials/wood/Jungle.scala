package k2b6s9j.boatcraft.compatibility.vanilla.boat.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

trait Jungle extends Material {

	override def getTexture: ResourceLocation = 
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/jungle.png")
	override def getName: String = "Jungle"
	override def getItem: ItemStack = new ItemStack(Blocks.planks, 1,  3)
	override def getStick: ItemStack = new ItemStack(Items.stick)

}