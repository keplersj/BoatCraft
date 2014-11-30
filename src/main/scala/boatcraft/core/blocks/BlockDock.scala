package boatcraft.core.blocks

import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.world.World
import boatcraft.core.blocks.tileentites.TileDock
import net.minecraft.entity.EntityLivingBase
import com.ibm.icu.impl.duration.impl.Utils
import net.minecraft.util.MathHelper
import net.minecraft.item.ItemStack
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.block.BlockContainer
import net.minecraft.util.EnumFacing
import net.minecraft.util.BlockPos
import net.minecraft.block.properties.PropertyInteger
import net.minecraft.block.properties.PropertyDirection
import java.util.Arrays
import net.minecraft.block.state.IBlockState

class BlockDock extends BlockContainer(Material.iron)
{
	import BlockDock._
	import EnumFacing._
	
	setCreativeTab(CreativeTabs.tabRedstone)
	setUnlocalizedName("dock")
	
	override def createNewTileEntity(world: World, meta: Int) = new TileDock
	
	override def onBlockPlacedBy(world: World, pos: BlockPos, state: IBlockState, entity: EntityLivingBase, stack: ItemStack)
	{
		super.onBlockPlacedBy(world, pos, state, entity, stack)
		
		val orientationTable = Array[EnumFacing](
				EnumFacing.SOUTH, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.EAST)
		val orientationIndex = MathHelper.floor_double((entity.rotationYaw + 45) / 90) & 3
		
		val orientation = orientationTable(orientationIndex)
		
		world.setBlockState(pos, state.withProperty(BlockDock.FACING, orientation.getOpposite), 1)
	}
	
	override def rotateBlock(world: World, pos: BlockPos, axis: EnumFacing): Boolean =
	{
		if (axis != UP && axis != DOWN) return false;
		
		world.setBlockState(pos,
							world.getBlockState(pos).cycleProperty(FACING))
		return true
	}
}

object BlockDock extends BlockDock
{
	import EnumFacing._
	
	final val FACING = PropertyDirection.create("facing", Arrays.asList(WEST, EAST, NORTH, SOUTH));
}