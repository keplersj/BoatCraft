package boatcraft.core.blocks

import boatcraft.api.traits.Block
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks

object Empty extends Block
{
	override def isRideable = true
	
	override def getBlock = Blocks.air
	
	override def getName = "Empty"
	
	override def getContent: ItemStack = null
}
