package boatcraft.core.modifiers.blocks

import boatcraft.api.modifiers.Block
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks

object Empty extends Block
{
	override def isRideable = true

	override def getBlock = Blocks.air.getDefaultState

	override def getName = "Empty"

	override def getContent: ItemStack = null
}
