package boatcraft.core.blocks.tileentites

import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.AxisAlignedBB
import net.minecraft.world.World
import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing
import boatcraft.core.blocks.BlockDock
import net.minecraft.server.gui.IUpdatePlayerListBox

class TileDock extends TileEntity with IUpdatePlayerListBox
{
	var docked: EntityCustomBoat = null
	
	override def update
	{
		val redstone = worldObj.isBlockPowered(pos)
		
		if (docked == null && redstone)
		{
			val dir = worldObj.getBlockState(pos).getValue(BlockDock.FACING).asInstanceOf[EnumFacing]
			docked =
				worldObj.getEntitiesWithinAABB(classOf[EntityCustomBoat],
					AxisAlignedBB.fromBounds(
						pos.getX, pos.getY, pos.getZ,
						pos.getX + dir.getFrontOffsetX,
						pos.getY + dir.getFrontOffsetY,
						pos.getZ + dir.getFrontOffsetZ))
					match {
				case list if !list.isEmpty => list.get(0).asInstanceOf[EntityCustomBoat]
				case _ => null
			}
		}
		else if (docked != null && !redstone) docked = null
		
		if (docked != null && redstone)
		{
			val center = getEffectCenter
			
            val distX = center._1 - docked.posX
            val distY = center._2 - docked.posY
            val distZ = center._3 - docked.posZ
            
			val f = Math.sqrt(distX*distX + distY*distY + distZ*distZ)
			
			if (f < 0.75) return
			
			val d0 = distX / f
			val d1 = distY / f
			val d2 = distZ / f
			
			docked.motionX += d0 * Math.abs(d0) * 0.4
			docked.motionY += d1 * Math.abs(d1) * 0.4
			docked.motionZ += d2 * Math.abs(d2) * 0.4
		}
	}
	
	def getEffectCenter: (Double, Double, Double) =
	{
		import EnumFacing._
		worldObj.getBlockState(pos).getValue(BlockDock.FACING).asInstanceOf[EnumFacing] match
		{
            case NORTH => (pos.getX + 0.5, pos.getY, pos.getZ)
            case SOUTH => (pos.getX + 0.5, pos.getY, pos.getZ + 1)
            case WEST => (pos.getX, pos.getY, pos.getZ + 0.5)
            case EAST => (pos.getX + 1, pos.getY, pos.getZ + 0.5)
            case _ => null
		}
	}
}