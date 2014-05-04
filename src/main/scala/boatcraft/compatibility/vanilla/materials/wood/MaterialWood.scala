package boatcraft.compatibility.vanilla.materials.wood

import boatcraft.api.traits.Material
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

class MaterialWood(meta: Int, name: String) extends Material {
  override def getTexture =
    new ResourceLocation("boatcraft",
      "textures/entity/boat/vanilla/wood/" +
        name.toLowerCase.replace(' ', '_').replace("_wood", "") + ".png")

  override def getName = name

  override def getItem = new ItemStack(Blocks.planks, 1, meta)

  override def getStick = new ItemStack(Items.stick)
}