package k2b6s9j.boatcraft.core.registry

import k2b6s9j.boatcraft.core.traits.Material
import k2b6s9j.boatcraft.core.BoatCraft

object MaterialRegistry
{
	var materials: Array[Material] = new Array[Material](0)

	def addMaterial(newMaterial: Material)
	{
		materials :+= newMaterial
		BoatCraft.log.info("Added " + newMaterial.name + " to the Material array.")
	}

	def addMaterials(newMaterials: Array[Material])
	{
		materials ++= newMaterials
		for (material: Material <- newMaterials)
			BoatCraft.log.info("Added " + material.name + " to the Material array.")
	}

	def getMaterial(meta: Int): Material =
		materials(meta >> 8)
}