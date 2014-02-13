package k2b6s9j.boatcraft.compatibility.ironchest.modifiers
import k2b6s9j.boatcraft.core.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityCrystalChest
import net.minecraft.entity.player.EntityPlayer

class Crystal_Chest extends Iron_Chest
{
	override def getMeta = IronChestType.CRYSTAL ordinal
	
	override def getName = "Crystal Chest"
	
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Crystal_Chest.Inventory(boat)
}

object Crystal_Chest
{
	private[Crystal_Chest] class Inventory(boat: EntityBoatContainer) extends TileEntityCrystalChest
	{
		override def getInventoryName = "Crystal Chest Boat"
		
		override def hasCustomInventoryName = false
		
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
		override def openInventory {}
		override def closeInventory {}
	}
}