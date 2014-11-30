package boatcraft.core.blocks

import java.util.List
import boatcraft.core.blocks.tileentites.TileDockAddon
import boatcraft.core.blocks.tileentites.TileFluidLoader
import boatcraft.core.blocks.tileentites.TileItemLoader
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.fml.relauncher.Side
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class BlockDockAddon extends BlockContainer(Material.iron) {
	
	setCreativeTab(CreativeTabs.tabRedstone)
	setUnlocalizedName("dockAddon")
    
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