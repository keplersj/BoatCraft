package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntitySilverChest
import net.minecraft.entity.player.EntityPlayer

/**
 * The Modifier for the Silver Chest from the Iron Chests mod.
 * @author Vilim Lendvaj
 */
object Silver_Chest extends GenericIronChest
{
  /**
   * The metadata of the Silver Chest as defined by the Iron Chests mod.
   *
   * @author Vilim Lendvaj
   * @return the metadata of the Silver Chest
   */
  override def getMeta = IronChestType.SILVER ordinal

  /**
   * The name of the Silver Chest
   *
   * @author Vilim Lendvaj
   * @return the name of the Silver Chest
   */
  override def getName = "Silver Chest"

  /**
   * The Inventory of the Silver Chest
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
  override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)

  /**
   * The Inventory of the Silver Chest
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   */
  private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.SILVER)
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