package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.IronChestType
import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import net.minecraft.inventory.IInventory

/**
 * The Modifier for the Iron Chest from the Iron Chests mod.
 * @author Vilim Lendvaj
 */
object Iron_Chest extends GenericIronChest
{
  /**
   * The metadata of the Iron Chest as defined by the Iron Chests mod.
   *
   * @author Vilim Lendvaj
   * @return the metadata of the Iron Chest
   */
  override def getMeta = IronChestType.IRON ordinal

  /**
   * The name of the Iron Chest
   *
   * @author Vilim Lendvaj
   * @return the name of the Iron Chest
   */
	override def getName = "Iron Chest"

  /**
   * The Inventory of the Iron Chest
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)

  /**
   * The Inventory of the Gold Chest
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   */
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.IRON)
	{
    /**
     * The name of the Inventory in the boat.
     * What's displayed in the Inventories GUI.
     *
     * @author Vilim Lendvaj
     * @return the name of the inventory
     */
		override def getInventoryName = getName + " Boat"
	}
}