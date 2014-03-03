package k2b6s9j.boatcraft.api.boat

import net.minecraft.inventory.IInventory
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

//TODO: Fill Documentation
/**
  *
  * @param world
  * @param x
  * @param y
  * @param z
  */
class EntityBoatContainer(world: World, x: Double, y: Double, z: Double)
	extends EntityCustomBoat(world, x, y, z) with IInventory
{
	def this(world: World) = this(world, 0, 0, 0)

	override def getSizeInventory =
		getInventory getSizeInventory

	override def getStackInSlot(slot: Int) =
		getInventory getStackInSlot slot

	override def decrStackSize(slot: Int, count: Int) =
		getInventory decrStackSize (slot, count)

	override def getStackInSlotOnClosing(slot: Int) =
		getInventory getStackInSlotOnClosing slot

	override def setInventorySlotContents(slot: Int, stack: ItemStack) =
		getInventory setInventorySlotContents (slot, stack)

	override def getInvName =
		getInventory getInvName

	override def getInventoryStackLimit =
		getInventory getInventoryStackLimit
	
	override def isUseableByPlayer(player: EntityPlayer) =
		getDistanceSqToEntity(player) <= 64
	
	override def openChest = getInventory.openChest
	
	override def closeChest = getInventory.closeChest
	
	override def isItemValidForSlot(slot: Int, stack: ItemStack) =
		getInventory.isItemValidForSlot(slot, stack)
	
	override def setDead
	{
		for (i: Int <- 0 until getSizeInventory)
			if (getStackInSlot(i) != null)
				entityDropItem(getStackInSlotOnClosing(i), 0F)
		super.setDead
	}

	//TODO: Fill Documentation
	/**
	  *
	  * @param modifier
	  */
	override def setModifier(modifier: String)
	{
		val prev = getModifierName
		super.setModifier(modifier)

		if (prev == null || prev != modifier || inventory == null)
			inventory = getModifier getInventory this
	}
	
	override protected def writeEntityToNBT(tag: NBTTagCompound)
	{
		super.writeEntityToNBT(tag)

		getModifier writeStateToNBT (this, tag)
	}
	
	override protected def readEntityFromNBT(tag: NBTTagCompound)
	{
		super.readEntityFromNBT(tag)

		getModifier readStateFromNBT (this, tag)
	}

	//TODO: Fill Documentation
	/**
	  *
	  */
	private var inventory: IInventory = null
	
	protected[boatcraft] def getInventory =
	{
		if (inventory == null) inventory = getModifier getInventory this
		inventory
	}

  override def onInventoryChanged(): Unit = getInventory.onInventoryChanged()

  override def isInvNameLocalized: Boolean = getInventory.isInvNameLocalized
}