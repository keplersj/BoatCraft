package boatcraft.api.modifiers

abstract class Mountable extends Modifier {
	import boatcraft.api.modifiers.Mountable._
	
	@inline def canMountOn(pos: Position): Boolean
	
	@inline def getModel: ModelBase
}

object Mountable {
	type Position = Position.Value
	
	object Position extends Enumeration {
		
		val LEFT, RIGHT, FRONT, BACK, CENTER = Value
	}
}