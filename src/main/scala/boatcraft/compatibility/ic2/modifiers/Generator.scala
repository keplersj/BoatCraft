package boatcraft.compatibility.ic2.modifiers

import net.minecraft.inventory.IInventory
import boatcraft.compatibility.ic2.modifiers.types.AbstractEnergySourceMod
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.common.ForgeHooks
import net.minecraft.tileentity.TileEntityFurnace
import ic2.api.item.IElectricItem

object Generator extends AbstractEnergySourceMod(4000, 1) with IInventory
{
	var fuel: ItemStack = null
	var charge: ItemStack = null
	
	def decrStackSize(slot: Int, count: Int) =
		if (slot == 0) fuel splitStack count
		else if (slot == 1) charge splitStack count
		else null
	
	def getInventoryName = "Generator on a Boat"
	
	def getInventoryStackLimit = 64
	
	def getSizeInventory = 2
	
	def getStackInSlot(slot: Int): ItemStack =
		if (slot == 0) fuel
        else if (slot == 1) charge
        else null
	
	def getStackInSlotOnClosing(slot: Int): ItemStack =
        if (slot == 0) fuel
        else if (slot == 1) charge
        else null
	
	def hasCustomInventoryName: Boolean = false
	
	def isItemValidForSlot(slot: Int, stack: ItemStack): Boolean =
        if (slot == 0) TileEntityFurnace isItemFuel stack
        else if (slot == 1) stack.getItem.isInstanceOf[IElectricItem]
        else false
	
	def isUseableByPlayer(player: EntityPlayer): Boolean = true
	
	def markDirty
	{}
	
	def openInventory
    {}
	
    def closeInventory
    {}
    
    def setInventorySlotContents(slot: Int, stack: ItemStack)
    {
    	if (slot == 0) fuel = stack
        else if (slot == 1) charge = stack
    }
    
    override def getOfferedEnergy =
    	if (energySource.getOfferedEnergy >= 10) 10
    	else 0
}