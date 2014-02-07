package k2b6s9j.boatcraft.compatibility.vanilla.asm

import java.util.Iterator
import net.minecraft.launchwrapper.IClassTransformer
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.tree.AbstractInsnNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.InsnList
import org.objectweb.asm.tree.InsnNode
import org.objectweb.asm.tree.MethodNode
import scala.util.control.Breaks._
import org.objectweb.asm.tree.LdcInsnNode
import k2b6s9j.boatcraft.core.BoatCraft

class VanillaClassTransformer extends IClassTransformer
{
	override def transform(className: String, str: String, bytes: Array[Byte]): Array[Byte] =
	{
		if (className.equals("abn"))
		{
			BoatCraft.log info "********* INSIDE OBFUSCATED ITEM TRANSFORMER ABOUT TO PATCH: " + className
			return patchClassASM(className, bytes, true)
		}
		if (className.equals("net.minecraft.item.Item"))
		{
			BoatCraft.log info "********* INSIDE ITEM TRANSFORMER ABOUT TO PATCH: " + className
			return patchClassASM(className, bytes, false)
		}
		bytes
	}
	
	def patchClassASM(name: String, bytes: Array[Byte], obfuscated: Boolean): Array[Byte] =
	{
		var targetMethodName: String = ""
		
		if(obfuscated)
			targetMethodName = "l"
		else
			targetMethodName = "func_150900_l"
		
		var classNode = new ClassNode()
		var classReader = new ClassReader(bytes)
		classReader.accept(classNode, 0)
		
		var methods: Iterator[MethodNode] = classNode.methods.iterator()
		while (methods.hasNext())
		{
			var m: MethodNode = methods.next()
			System.out println "********* Method Name: " + m.name + " Desc:" + m.desc
			var iconst1_index = -1
			
			if (m.name.equals(targetMethodName) && m.desc.equals("()V"))
			{
				System.out println "********* Inside target method!"
				
				var currentNode: AbstractInsnNode = null
				var targetNode: AbstractInsnNode = null
				
				var iter = m.instructions.iterator()
				
				var index = -1
				
				while (iter hasNext)
				{
					index = index + 1
					currentNode = iter next()
					System.out println "********* index : " + index + " currentNode.getOpcode() = " + currentNode.getOpcode()
					
					if (currentNode.getOpcode() == Opcodes.LDC
							&& (currentNode.asInstanceOf[LdcInsnNode].cst equals "boat"))
					{
						targetNode = currentNode
						iconst1_index = index
					}
				}
				
				//BoatCraft.log info("********* iconst0_index should be 2 -> " + iconst1_index)
				
				if (targetNode == null || iconst1_index == -1)
				{
					System.out println "Did not find all necessary target nodes! ABANDON CLASS!"
					return bytes
				}
				
				m.instructions remove(targetNode getPrevious() getPrevious() getPrevious())
				m.instructions remove(targetNode getPrevious() getPrevious())
				m.instructions remove(targetNode getPrevious())
				
				m.instructions remove(targetNode getNext() getNext()getNext() getNext()
						getNext() getNext() getNext() getNext())
				m.instructions remove(targetNode getNext() getNext() getNext() getNext()
						getNext() getNext() getNext())
				m.instructions remove(targetNode getNext() getNext() getNext() getNext()
						getNext() getNext())
				m.instructions remove(targetNode getNext() getNext() getNext() getNext()
						getNext())
				m.instructions remove(targetNode getNext() getNext() getNext() getNext())
				m.instructions remove(targetNode getNext() getNext() getNext())
				m.instructions remove(targetNode getNext() getNext())
				m.instructions remove(targetNode getNext())
				
				m.instructions remove targetNode
				
				System.out println "Patching Complete!"
				break
			}
		}
		
		var writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES)
		classNode accept writer
		writer toByteArray
	}
}
