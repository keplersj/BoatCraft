package boatcraft.core.blocks.tileentites

import boatcraft.api.boat.EntityCustomBoat

class TileDock extends TileEntity {
	
	var docked: EntityCustomBoat = null
	
	override def updateEntity
	{
		super.updateEntity
		
		val redstone = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)
		
		if (docked == null && redstone)
		{
			val dir = ForgeDirection.getOrientation(getBlockMetadata)
			docked =
				worldObj.getEntitiesWithinAABB(classOf[EntityCustomBoat],
					AxisAlignedBB.getBoundingBox(
						xCoord, yCoord, zCoord,
						xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ))
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
		ForgeDirection.getOrientation(getBlockMetadata) match
		{
            case NORTH => (xCoord + 0.5, yCoord, zCoord)
            case SOUTH => (xCoord + 0.5, yCoord, zCoord + 1)
            case WEST => (xCoord, yCoord, zCoord + 0.5)
            case EAST => (xCoord + 1, yCoord, zCoord + 0.5)
            case _ => null
		}
	}
}