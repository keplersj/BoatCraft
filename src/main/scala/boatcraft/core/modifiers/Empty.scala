package boatcraft.core.modifiers

import boatcraft.api.traits.Modifier
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks

object Empty extends Modifier
{
	override def isRideable = true
	
	override def getBlock = Blocks.air
	
	override def getName = "Empty"
	
	override def getContent: ItemStack = null
}
