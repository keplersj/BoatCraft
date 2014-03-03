package k2b6s9j.boatcraft.test.api.traits.examples

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.{Item, ItemStack}

object ExampleMaterial extends Material {

  override def getName: String = "Test Material"
  override def getItem: ItemStack = new ItemStack(Item.helmetChain)

}
