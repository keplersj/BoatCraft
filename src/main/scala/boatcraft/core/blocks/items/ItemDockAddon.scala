package boatcraft.core.blocks.items

import net.minecraft.item.ItemBlockWithMetadata
import boatcraft.core.blocks.BlockDockAddon
import net.minecraft.item.ItemStack
import net.minecraft.block.Block

class ItemDockAddon(block: Block) extends ItemBlockWithMetadata(block, block) {
	
	override def getMetadata(meta: Int) =
		if (meta < 3) meta
		else 0
	
	override def getUnlocalizedName(stack: ItemStack) =
		getUnlocalizedName() + "." + stack.getItemDamage
}