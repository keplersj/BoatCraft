package boatcraft.core.blocks.items

class ItemDockAddon(block: Block) extends ItemBlockWithMetadata(block, block) {
	
	override def getMetadata(meta: Int) =
		if (meta < 3) meta
		else 0
	
	override def getUnlocalizedName(stack: ItemStack) =
		getUnlocalizedName() + "." + stack.getItemDamage
}