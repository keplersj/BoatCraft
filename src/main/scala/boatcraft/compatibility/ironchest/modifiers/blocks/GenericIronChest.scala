package boatcraft.compatibility.ironchest.modifiers.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.compatibility.IronChests
import boatcraft.core.utilities.Helper
import cpw.mods.ironchest.{IronChest, IronChestType, ItemChestChanger, TileEntityIronChest}
import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import boatcraft.core.BoatCraft
import boatcraft.compatibility.ironchest.packets.IronChestSyncMessage
import cpw.mods.fml.relauncher.ReflectionHelper
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler
import cpw.mods.fml.common.network.simpleimpl.IMessage
import cpw.mods.fml.common.network.simpleimpl.MessageContext
import java.util.Arrays

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
		Helper.NBT writeChestToNBT(boat.getBlockDataWithType[Inventory], tag)

	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		Helper.NBT readChestFromNBT(boat.getBlockDataWithType[Inventory], tag)

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
					boat.asInstanceOf[EntityCustomBoat].getBlockDataWithType[Inventory] setInventorySlotContents(
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
		
		override def sortTopStacks
		{
			super.sortTopStacks
			
			if (!worldObj.isRemote)
				BoatCraft.channel.sendToAll(new IronChestSyncMessage(
					boat.getEntityId,
					ReflectionHelper.getPrivateValue(classOf[TileEntityIronChest], this, "type")
						.asInstanceOf[IronChestType].ordinal,
					getFacing, buildIntDataList))
		}
		
		override def updateFromMetadata(meta: Int): TileEntityIronChest = {
			if (meta == getType.ordinal) return this
			
			var res = chests(meta)
			
			boat.setBlock(res toString)
			
			return res.getBlockData(boat).asInstanceOf[Inventory]
		}
		
		override def getTopItemStacks: Array[ItemStack] =
		{
			for (x <- super.getTopItemStacks) print(x + " ")
			println()
			return super.getTopItemStacks
		}
	}
	
	class MessageHandler extends IMessageHandler[IronChestSyncMessage, IMessage] {
		
		override def onMessage(message: IronChestSyncMessage, context: MessageContext): IMessage =
		{
			println(String.format("Recieved message:\n%s\n%s %s",
				message.entityID toString, message.facing toString, message.chestType toString))
			for (x <- message.items) print(" " + x)
			println()
			
			var boat = FMLCommonHandler.instance.getMinecraftServerInstance.getEntityWorld
						.getEntityByID(message.entityID).asInstanceOf[EntityCustomBoat]
			var data = boat.getBlockData.asInstanceOf[GenericIronChest.Inventory]
			
			data.setFacing(message.facing)
			data.handlePacketData(message.chestType, message.items)
			
			return null
		}
	}
}