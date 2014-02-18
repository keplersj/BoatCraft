package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityDirtChest
import net.minecraft.entity.player.EntityPlayer

/**
 * The Modifier for the DirtChest9000 from the Iron Chests mod.
 * @author Vilim Lendvaj
 */
object DirtChest9000 extends GenericIronChest
{
  /**
   * The metadata of the DirtChest9000 as defined by the Iron Chests mod.
   *
   * @author Vilim Lendvaj
   * @return the metadata of the DirtChest9000
   */
  override def getMeta = IronChestType.DIRTCHEST9000 ordinal

  /**
   * The name of the DirtChest9000
   *
   * @author Vilim Lendvaj
   * @return the name of the DirtChest9000
   */
	override def getName = "DirtChest9000"

  /**
   * The Inventory of the DirtChest9000
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Inventory(boat)

  /**
   * The Inventory of the DirtChest9000
   *
   * @author Vilim Lendvaj
   * @param boat the boat container object
   */
	private class Inventory(boat: EntityBoatContainer)
		extends GenericIronChest.Inventory(boat, IronChestType.DIRTCHEST9000)
	{
    /**
     * The name of the Inventory in the boat.
     * What's displayed in the Inventories GUI.
     *
     * @author Vilim Lendvaj
     * @return the name of the inventory
     */
		override def getInventoryName = "DirtBoat9000!"
	}
}
