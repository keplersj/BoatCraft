package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityObsidianChest
import net.minecraft.entity.player.EntityPlayer

/**
 * The Modifier for the Obsidian Chest from the Iron Chests mod.
 * @author Vilim Lendvaj
 */
object Obsidian_Chest extends GenericIronChest
{
  /**
   * The metadata of the Obsidian Chest as defined by the Iron Chests mod.
   *
   * @author Vilim Lendvaj
   * @return the metadata of the Obsidian Chest
   */
  override def getMeta = IronChestType.OBSIDIAN ordinal

  /**
   * The name of the Obsidian Chest
   *
   * @author Vilim Lendvaj
   * @return the name of the Obsidian Chest
   */
	override def getName = "Obsidian Chest"

  /**
   * The Inventory of the Obsidian Chest
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)

  /**
   * The Inventory of the Obsidian Chest
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   */
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.OBSIDIAN)
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