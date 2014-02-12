package k2b6s9j.boatcraft.core.asm

import java.util.Iterator
import scala.util.control.Breaks.break
import org.objectweb.asm.{ClassReader, ClassWriter, Opcodes}
import org.objectweb.asm.tree.{AbstractInsnNode, ClassNode, LdcInsnNode, MethodNode}
import net.minecraft.launchwrapper.IClassTransformer
import net.minecraft.item.ItemPiston

class BoatCraftClassTransformer extends IClassTransformer {
	override def transform(className: String, str: String, bytes: Array[Byte]): Array[Byte] =
	{
		bytes
	}
	
	def patchClassASM(name: String, bytes: Array[Byte], obfuscated: Boolean): Array[Byte] =
	{		
		bytes
	}
}
