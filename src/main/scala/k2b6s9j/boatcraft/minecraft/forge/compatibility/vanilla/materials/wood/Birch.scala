package k2b6s9j.boatcraft.compatibility.vanilla.boat.wood

import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.util.ResourceLocation

trait Birch extends Material {

	override def texture: ResourceLocation = new ResourceLocation("boatcraft:compatibility/vanilla/textures/boats/wood/birch.png")
	override def name: String = "Birch"

}