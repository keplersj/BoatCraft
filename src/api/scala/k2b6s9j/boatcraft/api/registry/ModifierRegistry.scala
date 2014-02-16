package k2b6s9j.boatcraft.api.registry

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.item.ItemStack

/** Contains the methods needed to register Materials with BoatCraft:Core. */
object ModifierRegistry
{
	var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]

  /** Adds a single Modifier to the Map used by BoatCraft:Core for boat creation.
    *
    * @param newModifier the Modifier being registered
    */
  def addModifier(newModifier: Modifier)
	{
		modifiers put(newModifier toString, newModifier)
	}

  /** Adds a List of Modifiers to the Map used by BoatCraft:Core for boat creation.
    *
    * @param newModifiers list of Modifiers being registered
    */
	def addModifiers(newModifiers: List[Modifier])
	{
		for (modifier <- newModifiers)
			modifiers put(modifier toString, modifier)
	}

	def getModifier(name: String) =
		modifiers get name

	def getModifier(stack: ItemStack) =
		modifiers get (stack.stackTagCompound getString "modifier")
}