package k2b6s9j.boatcraft.compatibility.vanilla.materials.metal

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items
import net.minecraftforge.oredict.OreDictionary

class Iron extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/metal/iron.png")
	
	override def getName = "Iron"
	
	override def getItem = new ItemStack(Items.iron_ingot)
	override def getStick =
		if ((OreDictionary getOres "nuggetIron") isEmpty)
			null
		else (OreDictionary getOres "nuggetIron") get 0
}