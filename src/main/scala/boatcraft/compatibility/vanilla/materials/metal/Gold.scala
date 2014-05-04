package boatcraft.compatibility.vanilla.materials.metal

import boatcraft.api.traits.Material
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

object Gold extends Material {
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/metal/gold.png")

	override def getName = "Gold"

	override def getItem = new ItemStack(Items.gold_ingot)

	override def getStick = new ItemStack(Items.gold_nugget, 4)

	//TODO Balance
	override def getCrashResistance = 1.5
}