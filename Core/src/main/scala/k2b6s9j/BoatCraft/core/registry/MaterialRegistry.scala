package k2b6s9j.BoatCraft.core.registry

import k2b6s9j.BoatCraft.core.traits.Material
import k2b6s9j.BoatCraft.core.BoatCraft

object MaterialRegistry {

  var materials: Array[Material] = new Array[Material](0)

  def addMaterial(newMaterial: Material) {
    newMaterial +: materials
    BoatCraft.log.info("Added %s to the Material array.", newMaterial.toString)
  }

  def addMaterials(newMaterials: Array[Material]) {
    newMaterials ++: materials
    for (material: Material <- newMaterials) {
      BoatCraft.log.info("Added %s to the Material array.", material.toString)
    }
  }

}