package k2b6s9j.boatcraft.test.api.traits.examples

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block

object ExampleModifier2 extends Modifier {

  override def getName: String = "Test2"
  override def getBlock: Block = net.minecraft.init.Blocks.air

}
