package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import cpw.mods.fml.common.Mod
import cpw.mods.ironchest.IronChest
import k2b6s9j.boatcraft.api.Boat
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.compatibility.IronChests
import k2b6s9j.boatcraft.core.utilities.NBTHelper
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.item.ItemStack
import cpw.mods.ironchest.TileEntityIronChest
import cpw.mods.ironchest.IronChestType

/**
 * The modifier front end for all Iron Chests.
 * @author Vilim Lendvaj
 */
class GenericIronChest extends Modifier
{
  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @return block used for rendering
   */
	override def getBlock: Block = IronChest.ironChestBlock

  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @return the ItemStack used when crafting
   */
	override def getContent = new ItemStack(getBlock, 1, getMeta)

  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @return boolean representing if a boat has an inventory
   */
	override def hasInventory = true

  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @param boat the boat entity NBT data is being written to
   * @param tag the NBT data tag being written
   */
	override def writeStateToNBT(boat: Boat.EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper writeChestToNBT(boat.asInstanceOf[IInventory], tag)

  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @param boat the boat entity NBT data is being read from
   * @param tag the NBT data tag being read
   */
	override def readStateFromNBT(boat: Boat.EntityCustomBoat, tag: NBTTagCompound) =
		NBTHelper readChestFromNBT(boat.asInstanceOf[IInventory], tag)

  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @param player the player interacting with the Boat
   * @param boat the Boat being interacted with
   */
	override def interact(player: EntityPlayer, boat: Boat.EntityCustomBoat) =
		player openGui(IronChests, getMeta, player.worldObj,
				boat.posX.floor toInt, boat.posY.floor toInt, boat.posZ.floor toInt)
}

/**
 * Object containing common classes and methods for all the Iron Chest modifiers.
 * @author Vilim Lendvaj
 */
object GenericIronChest
{
  //TODO: Fill Documentation
  /**
   *
   * @author Vilim Lendvaj
   * @param boat
   * @param t
   */
  private[modifiers] class Inventory(boat: Boat.EntityBoatContainer, t: IronChestType)
		extends TileEntityIronChest(t)
	{
    //TODO: Fill Documentation
    /**
     *
     * @author Vilim Lendvaj
     */
    worldObj = boat.worldObj

    //TODO: Fill Documentation
    /**
     *
     * @author Vilim Lendvaj
     * @return
     */
		override def hasCustomInventoryName = false

    //TODO: Fill Documentation
    /**
     *
     * @author Vilim Lendvaj
     * @param player
     * @return
     */
		override def isUseableByPlayer(player: EntityPlayer) =
			(player getDistanceSqToEntity boat) <= 64

		//TODO make it render it on the boat
    //TODO: Fill Documentation
    /**
     *
     * @author Vilim Lendvaj
     */
		override def openInventory {}

    //TODO: Fill Documentation
    /**
     *
     * @author Vilim Lendvaj
     */
    override def closeInventory {}
	}
}