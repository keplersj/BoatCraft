package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.block.Block

//TODO: Fill Documentation
/**
 *
 */
object Spruce extends Material
{
  //TODO: Fill Documentation
  /**
   *
   * @return base texture of the Material
   */
  override def getTexture =
		new ResourceLocation("boatcraft",
				"textures/entity/boat/vanilla/wood/spruce.png")

  //TODO: Fill Documentation
  /**
   *
   * @return name of the Material
   */
  override def getName = "Spruce"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack representing the Material
   */
  override def getItem = new ItemStack(Block.planks, 1,  1)

  //TODO: Fill Documentation
  /**
   *
   * @return the secondary drop of the boat
   */
  override def getStick = new ItemStack(Item.stick)
}