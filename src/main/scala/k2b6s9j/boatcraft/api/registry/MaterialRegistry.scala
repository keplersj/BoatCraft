package k2b6s9j.boatcraft.api.registry

import java.util.{ List, Map }

import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

/** Contains the methods needed to register Materials with BoatCraft:Core. */
@deprecated
object MaterialRegistry
{
	/** The Map containing all of the registered Materials for BoatCraft:Core to create boats with. */
	var materials: Map[String, Material] = Registry.materials

	/**
	  * Adds a single Material to the Map used by BoatCraft:Core for boat creation.
	  *
	  * @param newMaterial the Material being registered
	  */
	def addMaterial(newMaterial: Material) 
	{
		Registry register newMaterial
	}

	/**
	  * Adds a list of Materials to the Map used by BoatCraft:Core for boat creation.
	  *
	  * @param newMaterials list of Materials being registered
	  */
	def addMaterials(newMaterials: List[Material])
	{
		Registry register newMaterials
	}

	/**
	  * Returns a registered Material associated with a certain name.
	  *
	  * @param name name of registered Material
	  * @return registered Material
	  */
	def getMaterial(name: String) =
		materials get name

	/**
	  * Returns a registered Material associated with a certain ItemStack.
	  *
	  * @param stack ItemStack of registered Material
	  * @return registered Material
	  */
	def getMaterial(stack: ItemStack) =
		if (stack.stackTagCompound == null) NoMaterial
		else materials get (stack.stackTagCompound getString "material")
	
	private object NoMaterial extends Material
	{
		override def getTexture =
			new ResourceLocation("minecraft", "textures/entity/boat.png")
	}
}