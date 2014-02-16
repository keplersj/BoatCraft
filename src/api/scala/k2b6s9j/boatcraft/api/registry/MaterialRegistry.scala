package k2b6s9j.boatcraft.api.registry

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack

/** Contains the methods needed to register Materials with BoatCraft:Core. */
object MaterialRegistry
{
  /** The Map containing all of the registered Materials for BoatCraft:Core to create boats with. */
  var materials: Map[String, Material] = new HashMap[String, Material]
	
  /** Adds a single Material to the Map used by BoatCraft:Core for boat creation.
    *
    * @param newMaterial the Material being registered
    */
	def addMaterial(newMaterial: Material)
	{
		materials put(newMaterial toString, newMaterial)
	}

  /** Adds a list of Materials to the Map used by BoatCraft:Core for boat creation.
    *
    * @param newMaterials list of Materials being registered
    */
	def addMaterials(newMaterials: List[Material])
	{
		for (material <- newMaterials)
			materials put(material toString, material)
	}

  /** Returns a registered Material associated with a certain name.
    *
    * @param name name of registered Material
    * @return registered Material
    */
	def getMaterial(name: String) =
		materials get name

  /** Returns a registered Material associated with a certain ItemStack.
    *
    * @param stack ItemStack of registered Material
    * @return registered Material
    */
	def getMaterial(stack: ItemStack) =
		materials get (stack.stackTagCompound getString "material")
}