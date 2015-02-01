package boatcraft.core.blocks

import boatcraft.core.blocks.tileentites.TileDock

class BlockDock extends BlockContainer(Material.iron) {
	
	setCreativeTab(CreativeTabs.tabRedstone)
	setBlockName("dock")
	
	override def createNewTileEntity(world: World, meta: Int) = new TileDock
	
	override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, stack: ItemStack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack)
		
		val orientationTable = Array[ForgeDirection](ForgeDirection.SOUTH,
				ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.EAST)
		val orientationIndex = MathHelper.floor_double((entity.rotationYaw + 45) / 90) & 3
		
		val orientation = orientationTable(orientationIndex)

		world.setBlockMetadataWithNotify(x, y, z, orientation.getOpposite.ordinal, 1)
	}
	
	override def rotateBlock(world: World, x: Int, y: Int, z: Int, axis: ForgeDirection): Boolean = {
		val meta = world.getBlockMetadata(x, y, z)
		ForgeDirection.getOrientation(meta) match {
			case WEST =>
				world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.SOUTH.ordinal, 3)
			case EAST =>
				world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.NORTH.ordinal, 3)
			case NORTH =>
				world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.WEST.ordinal, 3)
			case SOUTH =>
				world.setBlockMetadataWithNotify(x, y, z, ForgeDirection.EAST.ordinal, 3)
			case _ => //NOOP
		}
		world.markBlockForUpdate(x, y, z)
		return true
	}
}

object BlockDock extends BlockDock