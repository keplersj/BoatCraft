package k2b6s9j.boatcraft.core.materials

import k2b6s9j.boatcraft.core.traits.Modifier
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.block.Block

trait Empty extends Modifier
{
	override def isRideable: Boolean = true
	override def getBlock: Block = Blocks.air
	override def getName: String = "Empty"
	override def getContent: ItemStack = null
}
