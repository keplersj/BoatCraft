package boatcraft.api

import java.util.HashMap
import java.util.Map
import scala.collection.JavaConversions.iterableAsScalaIterable
import boatcraft.api.modifiers.Block
import boatcraft.api.modifiers.Material
import boatcraft.api.modifiers.Mountable
import boatcraft.core.blocks.Empty
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import boatcraft.api.modifiers.Modifier
import scala.reflect.classTag
import scala.reflect.ClassTag

/** Contains the methods needed to register Materials and Modifiers with BoatCraft:Core. */
object Registry {
	/** The Map containing all of the registered Materials for BoatCraft:Core to create boats with. */
	var materials: Map[String, Material] = new HashMap[String, Material]

	/** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
	var blocks: Map[String, Block] = new HashMap[String, Block]

	/** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
	var mountables: Map[String, Mountable] = new HashMap[String, Mountable]

	/**
	 * Adds materials or modifiers to the Map used by BoatCraft:Core for boat creation.
	 *
	 * @param registrar the object being registered
	 */
	def register(registrar: Any): Unit = registrar match {
		case material: Material =>
			materials put(material toString, material)
		case block: Block =>
			blocks put(block toString, block)
		case mount: Mountable =>
			mountables put(mount toString, mount)
		case x: java.lang.Iterable[_] =>
			x foreach (obj => register(obj))
		case x: Traversable[_] =>
			x foreach (obj => register(obj))
		case x: Array[_] =>
			x foreach (obj => register(obj))
		case _ =>
			System.err println "Was unable to register: " + registrar.toString
	}

	/**
	 * Removes materials or modifiers from the Map used by BoatCraft:Core for boat creation.
	 *
	 * @param unregistrant the object being unregistered
	 */
	def unregister(unregistrant: Any): Unit = unregistrant match {
		case material: Material =>
			materials remove material.toString
		case block: Block =>
			blocks remove block.toString
		case mount: Mountable =>
			mountables remove mount.toString
		case x: java.lang.Iterable[_] =>
			x foreach (obj => unregister(obj))
		case x: Traversable[_] =>
			x foreach (obj => unregister(obj))
		case x: Array[_] =>
			x foreach (obj => unregister(obj))
		case _ =>
			System.err println "There was nothing to unregister: " + unregistrant.toString
	}

	/**
	 * Returns a registered object associated with a certain name.
	 *
	 * @param name name of registered object
	 * @return registered object
	 */
	def find(name: String): Modifier = name match {
		case x if materials.containsKey(name) =>
			materials get name
		case x if blocks.containsKey(name) =>
			blocks get name
		case _ =>
			{
				System.err println "Could not find: " + name
				null
			}
	}
	
	def findOfType[T <: Modifier](name: String): T =
			try
			{
				return find(name).asInstanceOf[T]
			}
			catch
			{
				case ex: ClassCastException => return null.asInstanceOf[T]
			}

	/**
	 * Returns a registered Material associated with a certain ItemStack.
	 *
	 * @param stack ItemStack of registered Material
	 * @return registered Material
	 */
	def getMaterial(stack: ItemStack) =
		if (stack.stackTagCompound == null) NoMaterial
		else materials get (stack.stackTagCompound getString "material")

	private object NoMaterial extends Material {
		override def getTexture =
			new ResourceLocation("minecraft", "textures/entity/boat.png")
	}

	/**
	 * Returns a registered Block associated with a certain ItemStack.
	 *
	 * @param stack ItemStack of registered Block
	 * @return registered Block
	 */
	def getBlock(stack: ItemStack) =
		if (stack.stackTagCompound == null) Empty
		else if (stack.stackTagCompound hasKey "block") blocks get (stack.stackTagCompound getString "block")
		else blocks get (stack.stackTagCompound getString "modifier")

}
