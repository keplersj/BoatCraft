package k2b6s9j.boatcraft.compatibility.vanilla.materials.metal

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items
import net.minecraftforge.oredict.OreDictionary

//TODO: Fill Documentation
/**
 *
 */
object Gold extends Material
{
  //TODO: Fill Documentation
  /**
   *
   * @return base texture of the Material
   */
  override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/metal/gold.png")

  //TODO: Fill Documentation
  /**
   *
   * @return name of the Material
   */
	override def getName = "Gold"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack representing the Material
   */
	override def getItem = new ItemStack(Items.gold_ingot)

  //TODO: Fill Documentation
  /**
   *
   * @return the secondary drop of the boat
   */
  override def getStick = new ItemStack(Items.gold_nugget, 4)
}