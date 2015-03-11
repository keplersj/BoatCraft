package boatcraft.core.modifiers.blocks

import boatcraft.api.modifiers.Block
import nova.core.item.ItemStack

object Empty extends Block {
	override def isRideable = true

	override def getBlock = Blocks.air

	override def getName = "Empty"

	override def getContent: ItemStack = null
}
