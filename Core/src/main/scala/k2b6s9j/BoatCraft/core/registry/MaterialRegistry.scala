package k2b6s9j.BoatCraft.core.registry

import k2b6s9j.BoatCraft.core.traits.Material

object MaterialRegistry {

  var materials: Array[Material] = new Array[Material](0)

  def addMaterial(newMaterial: Material) {
    newMaterial +: materials
  }

  def addMaterials(newMaterials: Array[Material]) {
    newMaterials ++: materials
  }

}