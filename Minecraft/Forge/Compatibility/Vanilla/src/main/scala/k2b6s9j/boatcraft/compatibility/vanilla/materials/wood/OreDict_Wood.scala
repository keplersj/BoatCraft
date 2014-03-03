package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.block.Block

//TODO: Fill Documentation
/**
 *
 */
object OreDict_Wood extends Material
{
	override def getTexture =
		new ResourceLocation("minecraft", "textures/entity/boat.png")
	
	override def getName = "Wood"
	
	override def getItem = new ItemStack(Block.planks)
	override def getStick = new ItemStack(Item.stick)
}