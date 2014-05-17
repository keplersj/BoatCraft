package boatcraft.core.blocks.tileentites

import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import boatcraft.api.boat.EntityCustomBoat

class TileDock extends TileEntity {
	
	var docked: EntityCustomBoat = null
}