package k2b6s9j.boatcraft.api.registry

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.core.materials.Empty
import net.minecraft.item.ItemStack

/** Contains the methods needed to register Materials with BoatCraft:Core. */
object ModifierRegistry
{
  /** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
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

  /** Returns a registered Modifier associated with a certain name.
    *
    * @param name name of registered Modifier
    * @return registered Modifier
    */
	def getModifier(name: String) =
		modifiers get name

  /** Returns a registered Modifier associated with a certain ItemStack.
   *
   * @param stack ItemStack of registered Modifier
   * @return registered Modifier
   */
	def getModifier(stack: ItemStack) =
		if (stack.stackTagCompound == null) Empty
		else modifiers get (stack.stackTagCompound getString "modifier")
}