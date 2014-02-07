package k2b6s9j.boatcraft.core.materials

import k2b6s9j.boatcraft.core.traits.Modifier

trait Empty extends Modifier
{
	override def isRideable: Boolean = true
	override def containsBlock: Boolean = false
	override def getName: String = "Empty"
}
