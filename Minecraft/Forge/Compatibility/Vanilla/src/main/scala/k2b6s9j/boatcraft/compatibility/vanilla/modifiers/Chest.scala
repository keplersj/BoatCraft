package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.api.boat.EntityBoatContainer
import k2b6s9j.boatcraft.api.boat.EntityCustomBoat
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.core.utilities.NBTHelper
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntityChest

//TODO: Fill Documentation
/**
 *
 */
object Chest extends Modifier
{
  //TODO: Fill Documentation
  /**
   *
   * @return block used for rendering
   */
  override def getBlock: Block = Block.chest

  //TODO: Fill Documentation
  /**
   *
   * @return boolean representing if a boat has an inventory
   */
	override def hasInventory = true

  //TODO: Fill Documentation
  /**
   *
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
  override def getInventory(boat: EntityBoatContainer): IInventory =
		new Chest.Inventory(boat)

  //TODO: Fill Documentation
  /**
   *
   * @return the name of the Modifier
   */
	override def getName = "Chest"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack used when crafting
   */
  override def getContent = new ItemStack(Block.chest)

  //TODO: Fill Documentation
  /**
   *
   * @param player the player interacting with the Boat
   * @param boat the Boat being interacted with
   */
  override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
		//player openGui(Vanilla, 0, player.worldObj, 0, 0, 0)
		player displayGUIChest boat.asInstanceOf[IInventory]

  //TODO: Fill Documentation
  /**
   *
   * @param boat the boat entity NBT data is being written to
   * @param tag the NBT data tag being written
   */
  override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper writeChestToNBT(boat.asInstanceOf[IInventory], tag)

  //TODO: Fill Documentation
  /**
   *
   * @param boat the boat entity NBT data is being read from
   * @param tag the NBT data tag being read
   */
  override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper readChestFromNBT(boat.asInstanceOf[IInventory], tag)

  //TODO: Fill Documentation
  /**
   *
   * @param boat
   */
  private class Inventory(boat: EntityBoatContainer) extends TileEntityChest
	{
    //TODO: Fill Documentation
    /**
     *
     */
    worldObj = boat.worldObj

    //TODO: Fill Documentation
    /**
     *
     * @return
     */
    //override def getInventoryName = "Chest Boat"

    //TODO: Fill Documentation
    /**
     *
     * @return
     */
    //override def hasCustomInventoryName = false

    //TODO: Fill Documentation
    /**
     *
     * @param player
     * @return
     */
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64
		
		//TODO make it render it on the boat
    //TODO: Fill Documentation
    /**
     *
     */
    //override def openInventory {}

    //TODO: Fill Documentation
    /**
     *
     */
    //override def closeInventory {}
	}
}