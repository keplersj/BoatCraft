package boatcraft.api.modifiers

import net.minecraft.client.model.ModelBase

abstract class Mountable extends Modifier {
	import Mountable._
	
	@inline def canMountOn(pos: Position): Boolean
	
	@inline def getModel: ModelBase
}

object Mountable {
	type Position = Position.Value
	
	object Position extends Enumeration {
		
		val LEFT, RIGHT, FRONT, BACK, CENTER = Value
	}
}