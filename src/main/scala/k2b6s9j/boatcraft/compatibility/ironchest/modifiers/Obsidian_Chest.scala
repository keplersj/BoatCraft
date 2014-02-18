package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityObsidianChest
import net.minecraft.entity.player.EntityPlayer

//TODO: Fill Documentation
/**
 *
 */
object Obsidian_Chest extends GenericIronChest
{
  //TODO: Fill Documentation
  /**
   *
   * @return the metadata of the block being rendering in the boat
   */
  override def getMeta = IronChestType.OBSIDIAN ordinal

  //TODO: Fill Documentation
  /**
   *
   * @return the name of the Modifier
   */
	override def getName = "Obsidian Chest"

  //TODO: Fill Documentation
  /**
   *
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)

  //TODO: Fill Documentation
  /**
   *
   * @param boat
   */
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.OBSIDIAN)
	{
    //TODO: Fill Documentation
    /**
     *
     * @return
     */
		override def getInventoryName = getName + " Boat"
	}
}