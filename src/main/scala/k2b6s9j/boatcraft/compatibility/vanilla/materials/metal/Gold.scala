package k2b6s9j.boatcraft.compatibility.vanilla.materials.metal

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items
import net.minecraftforge.oredict.OreDictionary

object Gold extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/metal/gold.png")
	
	override def getName = "Gold"
	
	override def getItem = new ItemStack(Items.gold_ingot)
	override def getStick = new ItemStack(Items.gold_nugget)
}