package k2b6s9j.BoatCraft.core.materials

import k2b6s9j.BoatCraft.core.traits.Modifier

trait Empty extends Modifier {

  override def isRideable: Boolean = true
  override def containsBlock: Boolean = false

}
