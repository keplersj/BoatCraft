package k2b6s9j.boatcraft.api.registry

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.item.ItemStack

/** Contains the methods needed to register Materials with BoatCraft:Core. */
object ModifierRegistry
{
	var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]
	
	def addModifier(newMaterial: Modifier)
	{
		modifiers put(newMaterial toString, newMaterial)
	}

	def addModifiers(newMaterials: List[Modifier])
	{
		for (modifier <- newMaterials)
			modifiers put(modifier toString, modifier)
	}

	def getModifier(name: String) =
		modifiers get name

	def getModifier(stack: ItemStack) =
		modifiers get (stack.stackTagCompound getString "modifier")
}