package boatcraft.core.blocks.tileentites

import net.minecraft.server.gui.IUpdatePlayerListBox
import net.minecraft.tileentity.TileEntity
import boatcraft.core.blocks.BlockDock
import net.minecraft.util.EnumFacing

class TileDockAddon extends TileEntity with IUpdatePlayerListBox
{
	var parent: TileDock = null
	
	override def update
	{
		if (parent != null) return
		var possible: TileDock = null
		for (x <- -1 to 1) for (y <- -1 to 1 if y.abs != x.abs) for (z <- -1 to 1 if z.abs != x.abs && z.abs != y.abs)
		{
			worldObj.getTileEntity(pos.add(x, y, z)) match
			{
				case dock: TileDock =>
					if (possible == null && check(dock, x, y, z)) possible = dock
					else if (possible != dock) return //Multiple possibilities, back out
				case addon: TileDockAddon =>
					if (possible == null && addon.parent != possible) possible = addon.parent
					else if (addon.parent == null || addon.parent == possible) return
			}
		}
		parent = possible
	}
	
	private def check(dock: TileDock, x: Int, y: Int, z: Int): Boolean =
	{
		val dir = worldObj.getBlockState(dock.getPos).getValue(BlockDock.FACING).asInstanceOf[EnumFacing].getOpposite
		return x != dir.getFrontOffsetX && z != dir.getFrontOffsetZ
	}
}