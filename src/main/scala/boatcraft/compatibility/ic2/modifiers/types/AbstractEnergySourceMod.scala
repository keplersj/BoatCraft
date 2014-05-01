package boatcraft.compatibility.ic2.modifiers.types

import net.minecraft.tileentity.TileEntity
import boatcraft.api.traits.Modifier
import ic2.api.energy.tile.IEnergySource
import net.minecraftforge.common.util.ForgeDirection
import ic2.api.energy.prefab.BasicSource

abstract class AbstractEnergySourceMod(val capacity: Int, val tier: Int) extends Modifier with IEnergySource
{
	protected var energySource = new BasicSource(null, capacity, tier)
	
	override def getOfferedEnergy = energySource.getOfferedEnergy
	
	override def drawEnergy(amount: Double) = energySource drawEnergy amount
	
	override def emitsEnergyTo(receiver: TileEntity, direction: ForgeDirection) =
		//TODO Energy loaders/unloaders
		false
}