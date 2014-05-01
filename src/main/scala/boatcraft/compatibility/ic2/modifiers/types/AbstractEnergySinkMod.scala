package boatcraft.compatibility.ic2.modifiers.types

import net.minecraft.tileentity.TileEntity
import boatcraft.api.traits.Modifier
import ic2.api.energy.tile.IEnergySink
import net.minecraftforge.common.util.ForgeDirection
import ic2.api.energy.prefab.BasicSink

abstract class AbstractEnergySinkMod(val capacity: Int, val tier: Int) extends Modifier with IEnergySink
{
	protected var energySink = new BasicSink(null, capacity, tier)
	
	override def demandedEnergyUnits = energySink.demandedEnergyUnits
	
	override def injectEnergyUnits(directionFrom: ForgeDirection, amount: Double) =
		energySink.injectEnergyUnits(directionFrom, amount)
	
	override def getMaxSafeInput = energySink.getMaxSafeInput
	
	override def acceptsEnergyFrom(emitter: TileEntity, direction: ForgeDirection) =
		//TODO Energy loaders/unloaders
		false
}