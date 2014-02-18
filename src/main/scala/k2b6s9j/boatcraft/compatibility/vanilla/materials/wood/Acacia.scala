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
object Acacia extends Material
{
  //TODO: Fill Documentation
  /**
   *
   * @return base texture of the Material
   */
  override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/wood/acacia.png")

  //TODO: Fill Documentation
  /**
   *
   * @return name of the Material
   */
	override def getName = "Acacia"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack representing the Material
   */
	override def getItem = new ItemStack(Blocks.planks, 1, 4)

  //TODO: Fill Documentation
  /**
   *
   * @return the secondary drop of the boat
   */
  override def getStick = new ItemStack(Items.stick)
}