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
import boatcraft.core.BoatCraft
import boatcraft.compatibility.buildcraft.packets.TankSyncMessage

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
		
		if (!player.capabilities.isCreativeMode)
			player.setCurrentItemOrArmor(0, stack)
		
		data.hasUpdate = true
		
		return true
	}
	
	override def update(boat: EntityCustomBoat)
	{
		var logic = boat.getBlockData.asInstanceOf[Logic]
		//TODO make it glow when the fluid is luminous
		
		if (!boat.worldObj.isRemote && logic.hasUpdate)
		{
			var fluidID = 0
			if (logic.tank.getFluidType != null)
				fluidID = logic.tank.getFluidType.getID
			
			/*BoatCraft.log.info(String.format("Sending Tank sync data:\n\t%s %s %s %s",
				boat.getEntityId.toString,
				fluidID.toString, logic.tank.getFluidAmount.toString, logic.tank.colorRenderCache.toString))*/
			
			BoatCraft.channel.sendToAll(new TankSyncMessage(boat.getEntityId,
					fluidID, logic.tank.getFluidAmount, logic.tank.colorRenderCache))
			
			logic.hasUpdate = false
		}
	}
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
	  boat.getBlockData.asInstanceOf[Logic].tankManager.readFromNBT(tag)
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
	  boat.getBlockData.asInstanceOf[Logic].tankManager.writeToNBT(tag)
	
	private[buildcraft] class Logic(boat: EntityCustomBoat) extends TileTank
	{
		worldObj = boat.worldObj
		
		override def getBottomTank = this
		
		override def getTopTank = this
		
		override def moveFluidBelow {}
	}
}