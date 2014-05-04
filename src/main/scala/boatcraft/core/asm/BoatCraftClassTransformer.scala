package boatcraft.core.asm

import net.minecraft.launchwrapper.IClassTransformer

class BoatCraftClassTransformer extends IClassTransformer {
  override def transform(className: String, str: String, bytes: Array[Byte]) = {
    bytes
  }

  def patchClassASM(name: String, bytes: Array[Byte], obfuscated: Boolean) = {
    bytes
  }
}
