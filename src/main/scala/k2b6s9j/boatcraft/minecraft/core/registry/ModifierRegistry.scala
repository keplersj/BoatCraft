package k2b6s9j.boatcraft.core.registry

import java.util.{Map, HashMap, List}
import k2b6s9j.boatcraft.core.traits.Modifier
import k2b6s9j.boatcraft.core.BoatCraft
import net.minecraft.item.ItemStack
import scala.collection.JavaConversions._

object ModifierRegistry
{
	var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]
	
	def addModifier(newMaterial: Modifier)
	{
		modifiers put(newMaterial toString, newMaterial)
		BoatCraft.log.info("Added " + newMaterial.name + " to the Modifier set.")
	}

	def addModifiers(newMaterials: List[Modifier])
	{
		for (modifier: Modifier <- newMaterials)
		{
			modifiers put(modifier toString, modifier)
			BoatCraft.log.info("Added " + modifier.name + " to the Modifier set.")
		}
	}

	def getModifier(name: String): Modifier =
		modifiers get name

	def getModifier(stack: ItemStack): Modifier =
		modifiers get stack.stackTagCompound.getString("modifier")
}