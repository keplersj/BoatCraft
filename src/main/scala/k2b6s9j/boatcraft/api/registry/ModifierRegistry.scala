package k2b6s9j.boatcraft.api.registry

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.core.materials.Empty
import net.minecraft.item.ItemStack
import k2b6s9j.boatcraft.api.Registry

/** Contains the methods needed to register Materials with BoatCraft:Core. */
@deprecated
object ModifierRegistry
{
  /** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
  @deprecated
  var modifiers: Map[String, Modifier] = Registry.modifiers

  /** Adds a single Modifier to the Map used by BoatCraft:Core for boat creation.
    *
    * @param newModifier the Modifier being registered
    */
  @deprecated
  def addModifier(newModifier: Modifier)
	{
		Registry register newModifier
	}

  /** Adds a List of Modifiers to the Map used by BoatCraft:Core for boat creation.
    *
    * @param newModifiers list of Modifiers being registered
    */
	def addModifiers(newModifiers: List[Modifier])
	{
		Registry register newModifiers
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