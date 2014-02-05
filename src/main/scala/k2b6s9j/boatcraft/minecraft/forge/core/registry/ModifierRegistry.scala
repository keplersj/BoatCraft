package k2b6s9j.boatcraft.core.registry

import k2b6s9j.boatcraft.core.traits.Modifier
import k2b6s9j.boatcraft.core.BoatCraft

object ModifierRegistry {

	var modifiers: Array[Modifier] = new Array[Modifier](0)

	def addModifier(newModifier: Modifier)
	{
		modifiers :+= newModifier
		BoatCraft.log.info("Added " + newModifier.name + " to the Modifier array.")
	}

	def addModifiers(newModifiers: Array[Modifier])
	{
		modifiers ++= newModifiers
		for (modifier: Modifier <- newModifiers)
			BoatCraft.log.info("Added " + modifier.name + " to the Modifier array.")
	}

	def getModifier(meta: Int): Modifier =
		modifiers(meta & (1 << 8 - 1))

}