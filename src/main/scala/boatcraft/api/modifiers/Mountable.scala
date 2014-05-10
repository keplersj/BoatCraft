package boatcraft.api.modifiers

import net.minecraft.client.model.ModelBase

abstract class Mountable extends Modifier {
	import Mountable._
	
	def canMountOn(pos: Position): Boolean
	
	def getModel: ModelBase
}

object Mountable {
	type Position = Position.Value
	
	object Position extends Enumeration {
		
		val LEFT, RIGHT, FRONT, BACK, CENTER = Value
	}
}