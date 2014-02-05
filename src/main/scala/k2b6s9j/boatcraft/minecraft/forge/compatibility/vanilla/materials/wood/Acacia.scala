package k2b6s9j.boatcraft.compatibility.vanilla.boat.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation

trait Acacia extends Material {

	override def texture: ResourceLocation = 
		new ResourceLocation("boatcraft",
				"textures/model/boat/vanilla/wood/acacia.png")
	override def name: String = "Acacia"

}