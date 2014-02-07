package k2b6s9j.boatcraft.core.traits

import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack

trait Material
{
	def getTexture: ResourceLocation = null
	def getName: String = null
	def getItem: ItemStack = null
	def getStick: ItemStack = null
	
	override def toString: String = getName replaceAll(" ", "") toLowerCase
}