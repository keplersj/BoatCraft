package k2b6s9j.boatcraft.core.asm

import org.objectweb.asm.{ClassReader, ClassWriter}
import org.objectweb.asm.tree.ClassNode
import net.minecraft.launchwrapper.IClassTransformer
import net.minecraft.item.ItemPiston

class BoatCraftClassTransformer extends IClassTransformer
 {
	override def transform(className: String, str: String, bytes: Array[Byte]) =
	{
		bytes
	}
	
	def patchClassASM(name: String, bytes: Array[Byte], obfuscated: Boolean) =
	{		
		bytes
	}
}
