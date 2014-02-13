package k2b6s9j.boatcraft.api.registry

import java.util.{Map, HashMap, List}
import k2b6s9j.boatcraft.api.traits.Material
import k2b6s9j.boatcraft.core.BoatCraft
import net.minecraft.item.ItemStack
import scala.collection.JavaConversions._
import cpw.mods.fml.common.Mod

object MaterialRegistry
{
	var materials: Map[String, Material] = new HashMap[String, Material]
	
	def addMaterial(newMaterial: Material)
	{
		materials put(newMaterial toString, newMaterial)
		BoatCraft.log info "Added " + newMaterial.getName + " to the Material set."
	}

	def addMaterials(newMaterials: List[Material])
	{
		for (material <- newMaterials)
		{
			materials put(material toString, material)
			BoatCraft.log info "Added " + material.getName + " to the Material set."
		}
	}

	def getMaterial(name: String) =
		materials get name

	def getMaterial(stack: ItemStack) =
		materials get (stack.stackTagCompound getString "material")
}