package boatcraft.compatibility.vanilla.modifiers.materials.crystal

import boatcraft.api.modifiers.Material
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import boatcraft.api.boat.EntityCustomBoat

object Obsidian extends Material {
	
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/obsidian.png")
	
	override def getUnlocalizedName = "Obsidian"
	
	override def getLocalizedName = "vanilla.materials.crystal.obsidian.name"
	
	override def getItem = new ItemStack(Blocks.obsidian)
	
	override def getStick: ItemStack = null
	
	override def isImmuneToFire = true
}