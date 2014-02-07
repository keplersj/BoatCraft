package k2b6s9j.boatcraft.compatibility.vanilla.boat.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation

trait Oak extends Material
{
	override def texture: ResourceLocation =
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/oak.png")
	override def name: String = "Oak"
}