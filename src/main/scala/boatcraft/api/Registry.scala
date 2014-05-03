package boatcraft.api

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import boatcraft.api.traits.{Material, Modifier}
import boatcraft.core.modifiers.Empty
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

/** Contains the methods needed to register Materials and Modifiers with BoatCraft:Core. */
object Registry
{
	/** The Map containing all of the registered Materials for BoatCraft:Core to create boats with. */
	var materials: Map[String, Material] = new HashMap[String, Material]
	
	/** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
	var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]
	
	/**
	  * Adds materials or modifiers to the Map used by BoatCraft:Core for boat creation.
	  *
	  * @param registrar the object being registered
	  */
	def register(registrar: Any): Unit = registrar match
	{
		case _: Material =>
			materials put (registrar.toString, registrar.asInstanceOf[Material])
		case _: Modifier =>
			modifiers put (registrar.toString, registrar.asInstanceOf[Modifier])
		case x: List[_] =>
			x foreach (obj => register(obj))
		case _ =>
			System.err println "Was unable to register: " + registrar.toString
	}
	
	/**
	  * Removes materials or modifiers from the Map used by BoatCraft:Core for boat creation.
	  *
	  * @param unregistrant the object being unregistered
	  */
	def unregister(unregistrant: Any): Unit = unregistrant match
	{
		case _: Material =>
			materials remove unregistrant.toString
		case _: Modifier =>
			modifiers remove unregistrant.toString
		case x: List[_] =>
			x foreach (obj => unregister(obj))
		case _ =>
			System.err println "There was nothing to unregister: " + unregistrant.toString
	}

  /**
   * Returns a registered object associated with a certain name.
   *
   * @param name name of registered object
   * @return registered object
   */
  def find(name: String) = name match
  {
	case x if materials.containsKey(name) =>
	  (materials get name).asInstanceOf[Material]
	case x if modifiers.containsKey(name) =>
	  (modifiers get name).asInstanceOf[Modifier]
	case _ =>
	  System.err println "Could not find: " + name
  }

	/**
	  * Returns a registered Material associated with a certain ItemStack.
	  *
	  * @param stack ItemStack of registered Material
	  * @return registered Material
	  */
	def getMaterial(stack: ItemStack) =
		if (stack.stackTagCompound == null) NoMaterial
		else materials get (stack.stackTagCompound getString "material")

	private object NoMaterial extends Material
	{
		override def getTexture =
			new ResourceLocation("minecraft", "textures/entity/boat.png")
	}

	/**
	  * Returns a registered Modifier associated with a certain ItemStack.
	  *
	  * @param stack ItemStack of registered Modifier
	  * @return registered Modifier
	  */
	def getModifier(stack: ItemStack) =
		if (stack.stackTagCompound == null) Empty
		else modifiers get (stack.stackTagCompound getString "modifier")

}
