package k2b6s9j.boatcraft.api.registry

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack

/** Contains the methods needed to register Materials with BoatCraft:Core. */
object MaterialRegistry
{
	var materials: Map[String, Material] = new HashMap[String, Material]
	
	def addMaterial(newMaterial: Material)
	{
		materials put(newMaterial toString, newMaterial)
	}

	def addMaterials(newMaterials: List[Material])
	{
		for (material <- newMaterials)
			materials put(material toString, material)
	}

	def getMaterial(name: String) =
		materials get name

	def getMaterial(stack: ItemStack) =
		materials get (stack.stackTagCompound getString "material")
}