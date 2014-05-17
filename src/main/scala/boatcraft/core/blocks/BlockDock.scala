package boatcraft.core.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.world.World
import boatcraft.core.blocks.tileentites.TileDock

class BlockDock extends Block(Material.iron) {
	
	override def createTileEntity(world: World, meta: Int) = new TileDock
}