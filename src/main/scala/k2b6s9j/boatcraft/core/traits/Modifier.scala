package k2b6s9j.boatcraft.core.traits

import net.minecraft.item.ItemStack

trait Modifier
{
	def isRideable: Boolean = false
	def containsBlock: Boolean = false
	def getName: String = null
	def getContent: ItemStack = null
	
	override def toString: String = getName replaceAll(" ", "") toLowerCase
}