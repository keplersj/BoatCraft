package k2b6s9j.BoatCraft.core.registry

import k2b6s9j.BoatCraft.core.traits.Modifier

object ModifierRegistry {

  var modifiers: Array[Modifier] = new Array[Modifier](0)

  def addModifier(newModifier: Modifier) {
    newModifier +: modifiers
  }

  def addModifiers(newModifiers: Array[Modifier]) {
    newModifiers ++: modifiers
  }

}