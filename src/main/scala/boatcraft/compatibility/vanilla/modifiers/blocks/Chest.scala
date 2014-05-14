package boatcraft.compatibility.vanilla.modifiers.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.entity.player.EntityPlayerMP
import boatcraft.core.utilities.Helper

object Chest extends Block {
	override def getBlock = Blocks.chest

	override def getBlockData(boat: EntityCustomBoat): AnyRef =
		new Chest.Inventory(boat)

	override def getUnlocalizedName = "Chest"

	override def getLocalizedName = "vanilla.blocks.chest.name"

	override def getContent = new ItemStack(Blocks.chest)

	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
	{
		if (player.isInstanceOf[EntityPlayerMP])
			player displayGUIChest boat.getBlockData.asInstanceOf[Inventory]
		
		true
	}

	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		Helper.NBT writeChestToNBT(boat.getBlockData.asInstanceOf[Inventory], tag)

	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		Helper.NBT readChestFromNBT(boat.getBlockData.asInstanceOf[Inventory], tag)

	private class Inventory(boat: EntityCustomBoat) extends TileEntityChest {
		worldObj = boat.worldObj

		override def getInventoryName = "vanilla.blocks.chest.gui.title"

		override def hasCustomInventoryName = false

		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64

		//TODO make it render it on the boat
		override def openInventory() {}

		override def closeInventory() {}
	}

}