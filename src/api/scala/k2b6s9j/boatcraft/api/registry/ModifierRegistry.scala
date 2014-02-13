package k2b6s9j.boatcraft.api.registry

import java.util.{Map, HashMap, List}
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.core.BoatCraft
import net.minecraft.item.ItemStack
import scala.collection.JavaConversions._
import cpw.mods.fml.common.Mod

object ModifierRegistry
{
	var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]
	
	def addModifier(newMaterial: Modifier)
	{
		modifiers put(newMaterial toString, newMaterial)
		BoatCraft.log info "Added " + newMaterial.getName + " to the Modifier set."
	}

	def addModifiers(newMaterials: List[Modifier])
	{
		for (modifier: Modifier <- newMaterials)
		{
			modifiers put(modifier toString, modifier)
			BoatCraft.log info "Added " + modifier.getName + " to the Modifier set."
		}
	}

	def getModifier(name: String) =
		modifiers get name

	def getModifier(stack: ItemStack) =
		modifiers get (stack.stackTagCompound getString "modifier")
}