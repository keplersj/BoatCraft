package boatcraft.compatibility.vanilla.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.traits.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.tileentity.TileEntity
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.ObfuscationReflectionHelper
import net.minecraft.nbt.NBTTagList

object Furnace extends Block {
	GameRegistry.registerTileEntity(classOf[Inventory], "boatFurnace")
	
	override def getBlock = Blocks.furnace

	override def getMeta = 3

	override def getUnlocalizedName = "Furnace"

	override def getLocalizedName = "vanilla.blocks.furnace.name"

	override def getContent = new ItemStack(Blocks.furnace)

	override def getBlockData(boat: EntityCustomBoat): AnyRef =
		new Inventory(boat)

	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
	{
		if (player.isInstanceOf[EntityPlayerMP])
			player func_146101_a boat.asInstanceOf[EntityCustomBoat]
				.getBlockData.asInstanceOf[Inventory]
		
		true
	}

	override def update(boat: EntityCustomBoat) =
		(boat.asInstanceOf[EntityCustomBoat].getBlockData.asInstanceOf[Inventory]
			updateEntity())

	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.asInstanceOf[EntityCustomBoat].getBlockData.asInstanceOf[Inventory] readFromNBT tag

	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.asInstanceOf[EntityCustomBoat].getBlockData.asInstanceOf[Inventory] writeToNBT tag

	private class Inventory(boat: EntityCustomBoat) extends TileEntityFurnace {
		worldObj = boat.worldObj

		override def getInventoryName = "vanilla.blocks.furnace.gui.title"

		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64

		override def updateEntity {
			if (furnaceBurnTime > 0)
				furnaceBurnTime = furnaceBurnTime - 1

			if (!worldObj.isRemote) {
				if (furnaceBurnTime == 0 && canSmelt) {
					currentItemBurnTime = TileEntityFurnace getItemBurnTime getStackInSlot(1)
					furnaceBurnTime = TileEntityFurnace getItemBurnTime getStackInSlot(1)

					if (furnaceBurnTime > 0) {
						if (getStackInSlot(1) != null) {
							getStackInSlot(1).stackSize = getStackInSlot(1).stackSize - 1

							if (getStackInSlot(1).stackSize == 0) {
								setInventorySlotContents(1,
									getStackInSlot(1).getItem getContainerItem getStackInSlot(1))
							}
						}
					}
				}

				if (isBurning && canSmelt) {
					furnaceCookTime = furnaceCookTime + 1

					if (furnaceCookTime == 200) {
						furnaceCookTime = 0
						smeltItem
					}
				}
				else furnaceCookTime = 0
			}
		}

		private def canSmelt: Boolean = {
			if (getStackInSlot(0) == null) false
			else {
				val itemstack = FurnaceRecipes.smelting.getSmeltingResult(getStackInSlot(0))
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