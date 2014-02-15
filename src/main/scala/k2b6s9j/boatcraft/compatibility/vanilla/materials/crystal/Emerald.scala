package k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items

object Emerald extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/emerald.png")
	
	override def getName = "Emerald"
	
	override def getItem = new ItemStack(Items.emerald)
	override def getStick: ItemStack = null
}