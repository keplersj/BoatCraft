package boatcraft.compatibility.buildcraft.modifiers.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import buildcraft.BuildCraftFactory
import buildcraft.factory.TileTank
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.fluids.FluidContainerRegistry

object Tank extends Block {
	
	override def getBlock = BuildCraftFactory.tankBlock
	
	override def getUnlocalizedName = "Tank"
	
	override def getLocalizedName = "buidcraft.blocks.tank.name"
	
	override def getContent = new ItemStack(getBlock)
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new Logic(boat)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat): Boolean =
	{
		if (player.getCurrentEquippedItem == null) return false
		var stack = player.getCurrentEquippedItem
		var data = boat.getBlockData.asInstanceOf[Logic]
		val fluid = data.getTankInfo(ForgeDirection.UNKNOWN)(0).fluid
		if (FluidContainerRegistry.isEmptyContainer(stack))
		{
			val result = FluidContainerRegistry.fillFluidContainer(fluid, stack)
			
			if (result == null) return false
			
			stack = result
			data.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem(result), true)
		}
		else if (FluidContainerRegistry.isFilledContainer(stack))
		{
		  val fluid = FluidContainerRegistry.getFluidForFilledItem(stack)
			val result = data.fill(ForgeDirection.UNKNOWN, fluid, false)
			
			if (result != fluid.amount) return false
			
			stack = stack.getItem.getContainerItem(stack)
			data.fill(ForgeDirection.UNKNOWN, fluid, true)
		}
		else return false
		
		player.setCurrentItemOrArmor(0, stack)
		
		return true
	}
	
	override def update(boat: EntityCustomBoat)
	{
		//TODO make it glow when the fluid is luminous
	}
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
	  boat.getBlockData.asInstanceOf[Logic].tankManager.readFromNBT(tag)
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
	  boat.getBlockData.asInstanceOf[Logic].tankManager.writeToNBT(tag)
	
	private class Logic(boat: EntityCustomBoat) extends TileTank
	{
		worldObj = boat.worldObj
	}
}