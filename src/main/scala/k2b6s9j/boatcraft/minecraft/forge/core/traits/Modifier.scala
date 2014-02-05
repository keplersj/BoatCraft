package k2b6s9j.boatcraft.core.traits

trait Modifier
{
	def isRideable: Boolean = false
	def containsBlock: Boolean = false
	def name: String = null
	
	override def toString: String = name.replaceAll(" ", "");
}