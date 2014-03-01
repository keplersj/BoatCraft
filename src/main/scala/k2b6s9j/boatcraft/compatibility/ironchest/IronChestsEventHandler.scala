package k2b6s9j.boatcraft.compatibility.ironchest

import cpw.mods.fml.common.ObfuscationReflectionHelper
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.ironchest.IronChestType
import cpw.mods.ironchest.ItemChestChanger
import k2b6s9j.boatcraft.api.boat.EntityBoatContainer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityChest
import net.minecraftforge.event.entity.player.EntityInteractEvent

object IronChestsEventHandler
{
	@SubscribeEvent
	def interact(e: EntityInteractEvent)
	{
		if (!e.target.isInstanceOf[EntityBoatContainer]) return
		
		var boat = e.target.asInstanceOf[EntityBoatContainer]
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
					boat.asInstanceOf[EntityBoatContainer] setInventorySlotContents(
							i, newTE getStackInSlot i)
				}
			}
		}
	}
}