package boatcraft.compatibility.ic2.modifiers.types

import boatcraft.api.traits.Modifier
import ic2.api.energy.prefab.{BasicSink, BasicSource}
import ic2.api.energy.tile.{IEnergySink, IEnergySource}
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection

abstract class AbstractEnergyStorageMod(val capacity: Int, val tier: Int)
	extends Modifier with IEnergySink with IEnergySource
{
	protected var energySink = new BasicSink(null, capacity, tier)
	protected var energySource = new BasicSource(null, capacity, tier)
	
	override def demandedEnergyUnits = energySink.demandedEnergyUnits
	
	override def injectEnergyUnits(directionFrom: ForgeDirection, amount: Double) =
		energySink.injectEnergyUnits(directionFrom, amount)
	
	override def getMaxSafeInput = energySink.getMaxSafeInput
	
	override def acceptsEnergyFrom(emitter: TileEntity, direction: ForgeDirection) =
		//TODO Energy loaders/unloaders
		false
	
	override def getOfferedEnergy = energySource.getOfferedEnergy
	
	override def drawEnergy(amount: Double) = energySource drawEnergy amount
	
	override def emitsEnergyTo(receiver: TileEntity, direction: ForgeDirection) =
		//TODO Energy loaders/unloaders
		false
}