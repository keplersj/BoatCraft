package k2b6s9j.boatcraft.compatibility.vanilla.boat.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation

trait Jungle extends Material {

	override def texture: ResourceLocation = 
		new ResourceLocation("boatcraft",
				"textures/model/boat/vanilla/wood/jungle.png")
	override def name: String = "Jungle"

}