package boatcraft.core.modifiers.blocks

import boatcraft.api.modifiers.Block

object Empty extends Block {
	override def isRideable = true

	override def getBlock = Blocks.air

	override def getName = "Empty"

	override def getContent: ItemStack = null
}
