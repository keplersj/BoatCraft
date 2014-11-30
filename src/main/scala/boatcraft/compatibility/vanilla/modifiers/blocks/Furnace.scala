package boatcraft.compatibility.vanilla.modifiers.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraftforge.fml.common.registry.GameRegistry

object Furnace extends Block
{
	GameRegistry.registerTileEntity(classOf[Inventory], "boatFurnace")
	
	override def getBlock = Blocks.furnace.getDefaultState
	
	override def getUnlocalizedName = "Furnace"
	
	override def getLocalizedName = "vanilla.blocks.furnace.name"
	
	override def getContent = new ItemStack(Blocks.furnace)
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new Inventory(boat)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
	{
		if (player.isInstanceOf[EntityPlayerMP])
			player.asInstanceOf[EntityPlayerMP].displayGUIChest(boat.getBlockDataWithType[Inventory])
		
		true
	}

	override def update(boat: EntityCustomBoat) =
		boat.asInstanceOf[EntityCustomBoat].getBlockDataWithType[Inventory].update

	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.asInstanceOf[EntityCustomBoat].getBlockDataWithType[Inventory] readFromNBT tag

	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.asInstanceOf[EntityCustomBoat].getBlockDataWithType[Inventory] writeToNBT tag

	private class Inventory(boat: EntityCustomBoat) extends TileEntityFurnace
	{
		worldObj = boat.worldObj
		
		override def getName = "vanilla.blocks.furnace.gui.title"
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		override def update
		{
			val getFurnaceBurnTime = () => getField(0)
			val setFurnaceBurnTime = (x: Int) => setField(0, x)
			
			val getCurrentItemBurnTime = () => getField(1)
			val setCurrentItemBurnTime = (x: Int) => setField(1, x)
			
			val getFurnaceCookTime = () => getField(2)
			val setFurnaceCookTime = (x: Int) => setField(2, x)
			
			if (getFurnaceBurnTime() > 0)
				setFurnaceBurnTime(getFurnaceBurnTime() - 1)

			if (!worldObj.isRemote) {
				if (getFurnaceBurnTime() == 0 && canSmelt) {
					setCurrentItemBurnTime (TileEntityFurnace.getItemBurnTime(getStackInSlot(1)))
					setFurnaceBurnTime(TileEntityFurnace.getItemBurnTime(getStackInSlot(1)))

					if (getFurnaceBurnTime() > 0) {
						if (getStackInSlot(1) != null) {
							getStackInSlot(1).stackSize = getStackInSlot(1).stackSize - 1

							if (getStackInSlot(1).stackSize == 0) {
								setInventorySlotContents(1,
									getStackInSlot(1).getItem getContainerItem getStackInSlot(1))
							}
						}
					}
				}

				if (isBurning && canSmelt)
				{
					setFurnaceCookTime(getFurnaceCookTime() + 1)

					if (getFurnaceCookTime() == 200)
					{
						setFurnaceCookTime(0)
						smeltItem
					}
				}
				else setFurnaceCookTime(0)
			}
		}

		private def canSmelt: Boolean =
		{
			if (getStackInSlot(0) == null) false
			else {
				val itemstack = FurnaceRecipes.instance.getSmeltingResult(getStackInSlot(0))
				if (itemstack == null) return false
				if (getStackInSlot(2) == null) return true
				if (!getStackInSlot(2).isItemEqual(itemstack)) return false
				val result = getStackInSlot(2).stackSize + itemstack.stackSize

				result <= getInventoryStackLimit &&
					result <= getStackInSlot(2).getMaxStackSize
			}
		}
	}
}