package boatcraft.core.blocks.tileentites

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids.IFluidHandler
import net.minecraftforge.fluids.FluidTankInfo
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidStack

class TileFluidLoader extends TileDockAddon with IFluidHandler {
	
	def canDrain(side: ForgeDirection, fluid: Fluid) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.canDrain(side, fluid),
				false)
	
	def canFill(side: ForgeDirection, fluid: Fluid) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.canFill(side, fluid),
				false)
	
	def drain(side: ForgeDirection, amount: Int, doDrain: Boolean) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.drain(side, amount, doDrain),
				null)
	
	def drain(side: ForgeDirection, resource: FluidStack, doDrain: Boolean) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.drain(side, resource, doDrain),
				null)
	
	def fill(side: ForgeDirection, resource: FluidStack, doFill: Boolean) =
		chooseFluidHandlerOrElse(
				(handler: IFluidHandler) => handler.fill(side, resource, doFill),
				0)
	
	def getTankInfo(side: ForgeDirection) =
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