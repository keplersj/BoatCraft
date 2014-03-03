package k2b6s9j.boatcraft.test.api.traits.examples

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.{Item, ItemStack}

object ExampleMaterial2 extends Material {

  override def getName: String = "Test Material2"
  override def getItem: ItemStack = new ItemStack(Item.bootsChain)

}
