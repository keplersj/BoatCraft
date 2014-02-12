package k2b6s9j.boatcraft.core.registry

import java.util.{Map, HashMap, List}
import k2b6s9j.boatcraft.core.traits.Material
import k2b6s9j.boatcraft.core.BoatCraft
import net.minecraft.item.ItemStack
import scala.collection.JavaConversions._

object MaterialRegistry
{
	var materials: Map[String, Material] = new HashMap[String, Material]
	
	def addMaterial(newMaterial: Material)
	{
		materials put(newMaterial toString, newMaterial)
		BoatCraft.log.info("Added " + newMaterial.getName + " to the Material set.")
	}

	def addMaterials(newMaterials: List[Material])
	{
		for (material: Material <- newMaterials)
		{
			materials put(material toString, material)
			BoatCraft.log.info("Added " + material.getName + " to the Material set.")
		}
	}

	def getMaterial(name: String): Material =
		materials get name

	def getMaterial(stack: ItemStack): Material =
		materials get stack.stackTagCompound.getString("material")
}