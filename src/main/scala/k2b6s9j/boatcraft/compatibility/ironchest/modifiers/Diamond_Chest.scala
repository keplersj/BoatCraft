package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.ironchest.IronChestType
import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import net.minecraft.inventory.IInventory

//TODO: Fill Documentation
/**
 *
 */
object Diamond_Chest extends GenericIronChest
{
  //TODO: Fill Documentation
  /**
   *
   * @return the metadata of the block being rendering in the boat
   */
  override def getMeta = IronChestType.DIAMOND ordinal

  //TODO: Fill Documentation
  /**
   *
   * @return the name of the Modifier
   */
	override def getName = "Diamond Chest"

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
		extends GenericIronChest.Inventory(boat, IronChestType.DIAMOND)
	{
    //TODO: Fill Documentation
    /**
     *
     * @return
     */
    override def getInventoryName = getName + " Boat"
	}
}