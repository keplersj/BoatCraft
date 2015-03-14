package boatcraft.compatibility

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import cofh.api.energy.EnergyStorage
import cofh.api.energy.IEnergyHandler
import cpw.mods.fml.common.Optional
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

class EnergyCell(meta: Int) extends Block
{
	import EnergyCell._
	
	override def getUnlocalizedName = "energycell." + NAMES(meta)
		
	override def getLocalizedName = "redstoneflux.modifiers.blocks.energycell." + NAMES(meta)
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getBlockDataWithType[Data].storage.writeToNBT(tag)
	
	override def getBlock = Blocks.redstone_block //TODO Temporary
	
	override def getContent = new ItemStack(getBlock)
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new Data
	
	@Optional.Interface(modid = "CoFHAPI|energy", iface = "IEnergyHandler", striprefs = true)
	private class Data extends IEnergyHandler
	{
		private[EnergyCell] var storage = new EnergyStorage(STORAGE(meta), MAX_RECEIVE(meta), MAX_SEND(meta))
		
		override def canConnectEnergy(from: ForgeDirection) = true
		
		override def receiveEnergy(from: ForgeDirection, maxReceive: Int, simulate: Boolean) =
			storage.receiveEnergy(maxReceive, simulate)
		
		override def extractEnergy(from: ForgeDirection, maxExtract: Int, simulate: Boolean) =
			storage.extractEnergy(maxExtract, simulate)
		
		override def getEnergyStored(from: ForgeDirection) = storage.getEnergyStored
		
		override def getMaxEnergyStored(from: ForgeDirection) = storage.getMaxEnergyStored
	}
}

object EnergyCell
{
	val NAMES = Array[String]("creative", "basic", "hardened", "reinforced", "resonant")
	
	val STORAGE = Array[Int](10000, 400000, 2000000, 10000000, 50000000)
	val MAX_RECEIVE = Array[Int](0, 80, 400, 2000, 10000)
	val MAX_SEND = Array[Int](10000, 80, 400, 2000, 10000)
	
	val CELLS = (for (i <- 0 until 5) yield new EnergyCell(i)).toArray
}
