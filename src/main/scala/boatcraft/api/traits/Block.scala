package boatcraft.api.traits

import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

abstract class Block extends Modifier {
  /**
   * Boolean representing whether or not the player can mount a Boat, with this Modifier, on action-click
   *
   * @return boolean representing if a Boat is mountable
   */
  def isRideable = false

  /**
   * The Block to be rendered in a Boat
   *
   * @note NOT what's used to craft it!
   * @return block used for rendering
   */
  def getBlock: net.minecraft.block.Block = null

  /**
   * The metadata of the Block to be rendered in a Boat
   *
   * @note NOT what's used to craft it!
   * @return the metadata of the block being rendering in the boat
   */
  def getMeta = 0

  /**
   * Should return a new instance of the inventory contained in this Modifier
   *
   * @param boat the boat container object
   * @return the inventory associated with the boat container
   */
  def getInventory(boat: EntityCustomBoat): IInventory =
    EntityCustomBoat.NoInventory

  /**
   * The item used to craft a boat with this Block
   * @note NOT what's rendered in the Boat!
   *
   * @return the ItemStack used when crafting
   */
  def getContent: ItemStack = null
}