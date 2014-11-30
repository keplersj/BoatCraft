package boatcraft.core.blocks.tileentites

import net.minecraft.util.EnumFacing
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fluids.FluidTankInfo
import net.minecraftforge.fluids.IFluidHandler

class TileFluidLoader extends TileDockAddon with IFluidHandler
{
	override def canDrain(side: EnumFacing, fluid: Fluid) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.canDrain(side, fluid),
				false)
	
	override def canFill(side: EnumFacing, fluid: Fluid) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.canFill(side, fluid),
				false)
	
	override def drain(side: EnumFacing, amount: Int, doDrain: Boolean) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.drain(side, amount, doDrain),
				null)
	
	override def drain(side: EnumFacing, resource: FluidStack, doDrain: Boolean) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.drain(side, resource, doDrain),
				null)
	
	override def fill(side: EnumFacing, resource: FluidStack, doFill: Boolean) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.fill(side, resource, doFill),
				0)
	
	override def getTankInfo(side: EnumFacing) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.getTankInfo(side),
				Array[FluidTankInfo]())
	
	private def doIfFluidHandler(f: IFluidHandler => Unit) =
		parent.docked.getBlockData match
		{
			case handler: IFluidHandler => f(handler)
		}
	
	private def chooseFluidHandlerOrElse[T](yes: IFluidHandler => T, no: T): T =
		parent.docked.getBlockData match
		{
			case handler: IFluidHandler => yes(handler)
			case _ => no
		}
	
}