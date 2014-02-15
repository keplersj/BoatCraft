package k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items

object Diamond extends Material
{
	override def getTexture = 
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/diamond.png")
	
	override def getName = "Diamond"
	
	override def getItem = new ItemStack(Items.diamond)
	override def getStick =//Translocator mod
		if ((OreDictionary getOres "diamondNugget") isEmpty)
			null
		else (OreDictionary getOres "diamondNugget") get 0
}