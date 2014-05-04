package boatcraft.compatibility.ironchest

import cpw.mods.fml.common.ObfuscationReflectionHelper
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.ironchest.{IronChestType, ItemChestChanger}
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityChest
import net.minecraftforge.event.entity.player.EntityInteractEvent
import net.minecraft.inventory.IInventory

object IronChestsEventHandler {
	@SubscribeEvent
	def interact(e: EntityInteractEvent) {
		if (!e.target.isInstanceOf[EntityCustomBoat]) return

		val boat = e.target.asInstanceOf[EntityCustomBoat]
		
		if (boat.getBlockName != "chest") return
		
		val stack = e.entityPlayer.getCurrentEquippedItem

		if (stack != null && stack.getItem.isInstanceOf[ItemChestChanger]) {
			val changer = stack.getItem.asInstanceOf[ItemChestChanger]

			if (changer.getType canUpgrade IronChestType.WOOD) {
				val newTE = IronChestType.makeEntity(changer getTargetChestOrdinal IronChestType.WOOD.ordinal)
				val newSize = newTE.chestContents.length
				val chestContents: Array[ItemStack] = ObfuscationReflectionHelper getPrivateValue(
					classOf[TileEntityChest], boat.getBlockData.asInstanceOf[TileEntityChest], 0)
				System.arraycopy(chestContents, 0,
					newTE.chestContents, 0,
					Math.min(newSize, chestContents.length))

				boat.setBlock(IronChestType.values()(changer getTargetChestOrdinal IronChestType.WOOD.ordinal)
					.friendlyName replaceAll(" ", "") toLowerCase)

				for (i <- 0 until newTE.getSizeInventory) {
					boat.getBlockData.asInstanceOf[IInventory] setInventorySlotContents(i, newTE getStackInSlot i)
				}
			}
		}
	}
}