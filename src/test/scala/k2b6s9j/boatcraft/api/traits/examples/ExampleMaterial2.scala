package k2b6s9j.boatcraft.api.traits.examples

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack

object ExampleMaterial2 extends Material {

  override def getName: String = "Test2"
  override def getItem: ItemStack = new ItemStack(net.minecraft.init.Items.chainmail_boots)

}
