package k2b6s9j.boatcraft.compatibility.ironchest

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.ironchest.{IronChestType, ItemChestChanger}
import k2b6s9j.boatcraft.api.Boat
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers.GenericIronChest
import net.minecraftforge.event.entity.player.EntityInteractEvent
import net.minecraft.item.ItemStack
import cpw.mods.fml.common.ObfuscationReflectionHelper
import net.minecraft.tileentity.TileEntityChest

object IronChestsEventHandler
{
	@SubscribeEvent
	def interact(e: EntityInteractEvent)
	{
		if (!e.target.isInstanceOf[Boat.EntityBoatContainer]) return
		
		var boat = e.target.asInstanceOf[Boat.EntityBoatContainer]
		val stack = e.entityPlayer.getCurrentEquippedItem
		
		if (stack != null && stack.getItem.isInstanceOf[ItemChestChanger])
		{
			val changer = stack.getItem.asInstanceOf[ItemChestChanger]
			
			if (changer.getType canUpgrade IronChestType.WOOD)
			{
				val newTE = IronChestType.makeEntity(changer getTargetChestOrdinal IronChestType.WOOD.ordinal)
				val newSize = newTE.chestContents.length
				val chestContents: Array[ItemStack] = ObfuscationReflectionHelper getPrivateValue(
						classOf[TileEntityChest], boat.getInventory.asInstanceOf[TileEntityChest], 0)
				System.arraycopy(chestContents, 0,
							newTE.chestContents, 0,
							Math.min(newSize, chestContents.length))
				
				boat.setModifier((IronChestType.values()(changer getTargetChestOrdinal IronChestType.WOOD.ordinal))
						.friendlyName replaceAll(" ", "") toLowerCase)
				
				for (i <- 0 until newTE.getSizeInventory)
				{
					boat.asInstanceOf[Boat.EntityBoatContainer] setInventorySlotContents(
							i, newTE getStackInSlot i)
				}
			}
		}
	}
}