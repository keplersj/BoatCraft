package k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.ResourceLocation

//TODO: Fill Documentation
/**
 *
 */
object Emerald extends Material
{
  //TODO: Fill Documentation
  /**
   *
   * @return base texture of the Material
   */
  override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/crystal/emerald.png")

  //TODO: Fill Documentation
  /**
   *
   * @return name of the Material
   */
	override def getName = "Emerald"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack representing the Material
   */
	override def getItem = new ItemStack(Item.emerald)

  //TODO: Fill Documentation
  /**
   *
   * @return the secondary drop of the boat
   */
  override def getStick: ItemStack = null
}