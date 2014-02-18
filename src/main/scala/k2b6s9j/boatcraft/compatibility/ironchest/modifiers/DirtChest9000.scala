package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityBoatContainer
import cpw.mods.ironchest.IronChestType
import net.minecraft.inventory.IInventory
import cpw.mods.ironchest.TileEntityDirtChest
import net.minecraft.entity.player.EntityPlayer

//TODO: Fill Documentation
/**
 *
 */
object DirtChest9000 extends GenericIronChest
{
  //TODO: Fill Documentation
  /**
   *
   * @return the metadata of the block being rendering in the boat
   */
  override def getMeta = IronChestType.DIRTCHEST9000 ordinal

  //TODO: Fill Documentation
  /**
   *
   * @return the name of the Modifier
   */
	override def getName = "DirtChest9000"

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
		extends GenericIronChest.Inventory(boat, IronChestType.DIRTCHEST9000)
	{
    //TODO: Fill Documentation
    /**
     *
     * @return
     */
		override def getInventoryName = "DirtBoat9000!"
	}
}
