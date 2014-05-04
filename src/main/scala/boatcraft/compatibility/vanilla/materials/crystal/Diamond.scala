package boatcraft.compatibility.vanilla.materials.crystal

import boatcraft.api.traits.Material
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.init.Items

//TODO: Fill Documentation
/**
 *
 */
object Diamond extends Material {
  //TODO: Fill Documentation
  /**
   *
   * @return base texture of the Material
   */
  override def getTexture =
    new ResourceLocation("boatcraft",
      "textures/entity/boat/vanilla/crystal/diamond.png")

  //TODO: Fill Documentation
  /**
   *
   * @return name of the Material
   */
  override def getName = "Diamond"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack representing the Material
   */
  override def getItem = new ItemStack(Items.diamond)

  //TODO: Fill Documentation
  /**
   *
   */
  override def getStick = //Translocator mod
    if ((OreDictionary getOres "diamondNugget").isEmpty)
      null
    else (OreDictionary getOres "diamondNugget") get 0
}