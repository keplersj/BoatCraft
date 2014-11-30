package boatcraft.compatibility.ironchest.modifiers.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.compatibility.IronChests
import boatcraft.compatibility.ironchest.packets.IronChestSyncMessage
import boatcraft.core.BoatCraft
import boatcraft.core.utilities.Helper
import cpw.mods.ironchest.BlockIronChest
import cpw.mods.ironchest.IronChest
import cpw.mods.ironchest.IronChestType
import cpw.mods.ironchest.ItemChestChanger
import cpw.mods.ironchest.TileEntityIronChest
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.BlockPos
import net.minecraftforge.fml.common.FMLCommonHandler
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.relauncher.ReflectionHelper

abstract class GenericIronChest(chestType: IronChestType) extends Block
{
	import GenericIronChest.Inventory
	
	override def getBlock = IronChest.ironChestBlock.getDefaultState
							.withProperty(BlockIronChest.VARIANT_PROP, chestType)
	
	override def getContent = new ItemStack(getBlock.getBlock, 1, chestType.ordinal)
	
	override def getUnlocalizedName = chestType.friendlyName
	
	override def getLocalizedName = chestType.friendlyName
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new Inventory(boat, chestType)
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		Helper.NBT writeChestToNBT(boat.getBlockDataWithType[Inventory], tag)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		Helper.NBT readChestFromNBT(boat.getBlockDataWithType[Inventory], tag)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
	{
		val stack = player.getCurrentEquippedItem
		
		if (stack != null && stack.getItem.isInstanceOf[ItemChestChanger])
		{
			val changer = stack.getItem.asInstanceOf[ItemChestChanger]

			if (changer.getType canUpgrade chestType)
			{
				val newTE = boat.asInstanceOf[EntityCustomBoat].getBlockData
					.asInstanceOf[Inventory] applyUpgradeItem changer

				boat.setBlock(IronChestType.values()(changer getTargetChestOrdinal chestType.ordinal)
					.friendlyName replaceAll(" ", "") toLowerCase)

				for (i <- 0 until newTE.getSizeInventory)
				{
					boat.asInstanceOf[EntityCustomBoat].getBlockDataWithType[Inventory].setInventorySlotContents(
						i, newTE getStackInSlot i)
				}
			}
		}
		else if (player.isInstanceOf[EntityPlayerMP])
			player openGui("boatcraft", (IronChests.code << 6) | chestType.ordinal, boat.worldObj,
				boat.getEntityId, -1, 0)
		
		true
	}
}

object GenericIronChest {

	private[ironchest] class Inventory(boat: EntityCustomBoat, t: IronChestType)
		extends TileEntityIronChest(t)
	{
		worldObj = boat.worldObj
		
		override def hasCustomName = false
		
		override def getName = t.friendlyName + " Boat"
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory(player: EntityPlayer) {}

		override def closeInventory(player: EntityPlayer) {}
		
		override def clear() = chestContents = new Array[ItemStack](chestContents.length)

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
				new BlockPos(boat.posX.floor toInt, boat.posY.floor toInt, boat.posZ.floor toInt))
			
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
					getFacing, buildItemStackDataList));
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
	
	class MessageHandler extends IMessageHandler[IronChestSyncMessage, IMessage]
	{
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