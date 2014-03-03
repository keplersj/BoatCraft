package k2b6s9j.boatcraft.test.api.traits.examples

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block

object ExampleModifier extends Modifier {

  override def getName: String = "Test Modifier"
  override def getBlock: Block = Block.bedrock

}
