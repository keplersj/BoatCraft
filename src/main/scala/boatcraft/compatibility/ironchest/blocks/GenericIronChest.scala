package boatcraft.compatibility.ironchest.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.compatibility.IronChests
import boatcraft.core.utilities.NBTHelper
import cpw.mods.ironchest.{IronChest, IronChestType, ItemChestChanger, TileEntityIronChest}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.entity.player.EntityPlayerMP
import boatcraft.api.modifiers.Block

abstract class GenericIronChest(chestType: IronChestType) extends Block {
	import GenericIronChest.Inventory
	
	override def getBlock = IronChest.ironChestBlock

	override def getMeta = chestType.ordinal

	override def getContent = new ItemStack(getBlock, 1, getMeta)

	override def getUnlocalizedName = chestType.friendlyName

	override def getLocalizedName = chestType.friendlyName

	override def getBlockData(boat: EntityCustomBoat): AnyRef =
		new Inventory(boat, chestType)

	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper writeChestToNBT(boat.getBlockData.asInstanceOf[Inventory], tag)

	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper readChestFromNBT(boat.getBlockData.asInstanceOf[Inventory], tag)

	override def interact(player: EntityPlayer, boat: EntityCustomBoat) = {
		val stack = player.getCurrentEquippedItem

		if (stack != null && stack.getItem.isInstanceOf[ItemChestChanger]) {
			val changer = stack.getItem.asInstanceOf[ItemChestChanger]

			if (changer.getType canUpgrade chestType) {
				val newTE = boat.asInstanceOf[EntityCustomBoat].getBlockData
					.asInstanceOf[Inventory] applyUpgradeItem changer

				boat.setBlock(IronChestType.values()(changer getTargetChestOrdinal chestType.ordinal)
					.friendlyName replaceAll(" ", "") toLowerCase)

				for (i <- 0 until newTE.getSizeInventory) {
					boat.asInstanceOf[EntityCustomBoat].getBlockData.asInstanceOf[Inventory] setInventorySlotContents(
						i, newTE getStackInSlot i)
				}
			}
		}
		else if (player.isInstanceOf[EntityPlayerMP])
			player openGui("boatcraft", (IronChests.code << 6) | getMeta, boat.worldObj,
				boat.getEntityId, -1, 0)
		
		true
	}
}

object GenericIronChest {

	private[ironchest] class Inventory(boat: EntityCustomBoat, t: IronChestType)
		extends TileEntityIronChest(t) {
		worldObj = boat.worldObj

		override def hasCustomInventoryName = false

		override def getInventoryName = t.friendlyName + " Boat"

		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64

		//TODO make it render it on the boat
		override def openInventory() {}

		override def closeInventory() {}

		override def applyUpgradeItem(changer: ItemChestChanger): TileEntityIronChest = {
			if (!(changer.getType canUpgrade getType))
				return null

			val newEntity = new Inventory(boat,
				IronChestType.values()(changer getTargetChestOrdinal getType.ordinal))
			val newSize = newEntity.chestContents.length

			System arraycopy(chestContents, 0,
				newEntity.chestContents, 0,
				Math min(newSize, chestContents.length))

			val block = IronChest.ironChestBlock
			block dropContent(newSize, this, worldObj,
				boat.posX.floor.toInt, boat.posY.floor.toInt, boat.posZ.floor.toInt)

			newEntity markDirty()

			newEntity
		}
	}

}