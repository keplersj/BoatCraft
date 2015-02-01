package boatcraft.api

import java.util.{HashMap, Map}

import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.{Block, Material, Modifier, Mountable}
import boatcraft.core.modifiers.blocks.Empty

import scala.collection.JavaConversions.iterableAsScalaIterable

/** Contains the methods needed to register Materials and Modifiers with BoatCraft:Core. */
object Registry {
	/** The Map containing all of the registered Materials for BoatCraft:Core to create boats with. */
	var materials: Map[String, Material] = new HashMap[String, Material]
	
	var materialItems: Map[StackHashWrapper, Material] = new HashMap[StackHashWrapper, Material]
	
	/** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
	var blocks: Map[String, Block] = new HashMap[String, Block]
	
	var blockItems: Map[StackHashWrapper, Block] = new HashMap[StackHashWrapper, Block]
	
	/** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
	var mountables: Map[String, Mountable] = new HashMap[String, Mountable]
	
	var mountableItems: Map[StackHashWrapper, Mountable] = new HashMap[StackHashWrapper, Mountable]
	
	/**
	 * Adds materials or modifiers to the Map used by BoatCraft:Core for boat creation.
	 *
	 * @param registrar the object being registered
	 */
	def register(registrar: Any): Unit = registrar match {
		case material: Material =>
			materials put(material toString, material)
			materialItems.put(StackHashWrapper(material.getItem), material)
		case block: Block =>
			blocks put(block toString, block)
			blockItems.put(StackHashWrapper(block.getContent), block)
		case mount: Mountable =>
			mountables put(mount toString, mount)
			mountableItems.put(??? /*TODO*/, mount)
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
		case _ if materials containsKey name =>
			materials get name
		case _ if blocks containsKey name =>
			blocks get name
		case _ if mountables containsKey name =>
			mountables get name
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
	
	def isRegisteredMaterial(name: String) = materials containsKey name
	
	def isRegisteredBlock(name: String) = blocks containsKey name
	
	def isRegisteredMountable(name: String) = mountables containsKey name

	/**
	 * Returns a registered Material associated with a certain ItemStack.
	 *
	 * @param stack ItemStack of registered Material
	 * @return registered Material
	 */
	def getMaterial(stack: ItemStack) =
		if (stack.getItem.isInstanceOf[ItemCustomBoat])
		{
			if (stack.stackTagCompound == null) NoMaterial
			else materials get (stack.stackTagCompound getString "material")
		}
		else materialItems get StackHashWrapper(stack)

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
		if (stack == null) Empty
		else if (stack.getItem.isInstanceOf[ItemCustomBoat])
		{
			if (stack.stackTagCompound == null) Empty
			else blocks get (stack.stackTagCompound getString "block")
		}
		else blockItems get StackHashWrapper(stack)
	
	case class StackHashWrapper(var stack: ItemStack) {
			override def equals(other: Any): Boolean = 
				if (!other.isInstanceOf[StackHashWrapper]) false
				else if (stack == null) other.asInstanceOf[StackHashWrapper].stack == null
				else stack.isItemEqual(other.asInstanceOf[StackHashWrapper].stack)
			
			override def hashCode =
				if (stack == null || stack.getItem == null) 0
				else (stack.getItem.getUnlocalizedName.hashCode << 16) ^ stack.getItemDamage
		}
}
