package boatcraft.core.blocks

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.world.World
import boatcraft.core.blocks.tileentites.TileDockAddon
import boatcraft.core.blocks.tileentites.TileItemLoader
import boatcraft.core.blocks.tileentites.TileFluidLoader
import net.minecraft.item.Item
import net.minecraft.creativetab.CreativeTabs
import cpw.mods.fml.relauncher.SideOnly
import cpw.mods.fml.relauncher.Side
import java.util.List
import net.minecraft.item.ItemStack

class BlockDockAddon extends BlockContainer(Material.iron) {
    
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