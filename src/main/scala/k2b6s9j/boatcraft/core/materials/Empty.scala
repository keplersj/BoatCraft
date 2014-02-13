package k2b6s9j.boatcraft.core.materials

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.block.Block

class Empty extends Modifier
{
	override def isRideable = true
	override def getBlock = Blocks.air
	override def getName = "Empty"
	override def getContent: ItemStack = null
}
