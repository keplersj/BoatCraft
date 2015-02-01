package boatcraft.core.blocks

import java.util.List

import boatcraft.core.blocks.tileentites.{TileDockAddon, TileFluidLoader, TileItemLoader}

class BlockDockAddon extends BlockContainer(Material.iron) {
	
	setCreativeTab(CreativeTabs.tabRedstone)
	setBlockName("dockAddon")
    
    override def createNewTileEntity(world: World, meta: Int) = meta match
        {
    	    case 0 => new TileDockAddon
            case 1 => new TileItemLoader
            case 2 => new TileFluidLoader
        }
    
    @SideOnly(Side.CLIENT)
    override def getSubBlocks(item: Item, tab: CreativeTabs, list: List[_])
    {
    	for (i <- 0 until 3) list.asInstanceOf[List[ItemStack]].add(new ItemStack(item, 1, i))
    }
}

object BlockDockAddon extends BlockDockAddon