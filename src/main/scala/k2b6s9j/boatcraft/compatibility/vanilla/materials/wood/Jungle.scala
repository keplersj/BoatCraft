package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import net.minecraft.init.Blocks

//TODO: Fill Documentation
/**
 *
 */
object Jungle extends Material
{
  //TODO: Fill Documentation
  /**
   *
   * @return base texture of the Material
   */
  override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/wood/jungle.png")

  //TODO: Fill Documentation
  /**
   *
   * @return name of the Material
   */
  override def getName = "Jungle"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack representing the Material
   */
  override def getItem = new ItemStack(Blocks.planks, 1,  3)

  //TODO: Fill Documentation
  /**
   *
   * @return the secondary drop of the boat
   */
  override def getStick = new ItemStack(Items.stick)
}