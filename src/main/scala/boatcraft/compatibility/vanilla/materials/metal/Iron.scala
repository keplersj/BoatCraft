package boatcraft.compatibility.vanilla.materials.metal

import boatcraft.api.modifiers.Material
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.oredict.OreDictionary

object Iron extends Material {
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/metal/iron.png")

	override def getUnlocalizedName = "Iron"

	override def getLocalizedName = "vanilla.materials.metal.iron.name"

	override def getItem = new ItemStack(Items.iron_ingot)

	override def getStick =
		if ((OreDictionary getOres "nuggetIron").isEmpty)
			null
		else new ItemStack(((OreDictionary getOres "nuggetIron") get 0) getItem, 4)

	//TODO Balance
	override def getCrashResistance = 2.0
}