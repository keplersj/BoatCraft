package k2b6s9j.boatcraft.core.traits

import net.minecraft.util.ResourceLocation

trait Material
{
	def texture: ResourceLocation = null
	def name: String = null
	
	override def toString: String = name.replaceAll(" ", "");
}