package k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

object Obsidian extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/obsidian.png")
	
	override def getName = "Obsidian"
	
	override def getItem = new ItemStack(Blocks.obsidian)
	override def getStick: ItemStack = null
}