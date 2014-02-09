package k2b6s9j.BoatCraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.core.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks

trait Furnace extends Modifier
{
  override def getBlock: Block = Blocks.lit_furnace
  override def getMeta: Int = 2
  override def getName: String = "Furnace"
  override def getContent: ItemStack = new ItemStack(Blocks.lit_furnace)
}
