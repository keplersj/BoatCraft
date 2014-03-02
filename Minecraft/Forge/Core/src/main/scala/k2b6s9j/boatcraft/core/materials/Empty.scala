package k2b6s9j.boatcraft.core.materials

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.block.Block

object Empty extends Modifier
{
  //TODO: Fill Documentation
  /**
   *
   * @return boolean representing if a Boat is mountable
   */
  override def isRideable = true

  //TODO: Fill Documentation
  /**
   *
   * @return block used for rendering
   */
  override def getBlock = Blocks.air

  //TODO: Fill Documentation
  /**
   *
   * @return the name of the Modifier
   */
  override def getName = "Empty"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack used when crafting
   */
  override def getContent: ItemStack = null
}
