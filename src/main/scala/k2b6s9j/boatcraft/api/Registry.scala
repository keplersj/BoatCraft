package k2b6s9j.boatcraft.api

import java.util.{HashMap, List, Map}

import scala.collection.JavaConversions.asScalaBuffer

import cpw.mods.fml.common.Mod
import k2b6s9j.boatcraft.api.traits.{Material, Modifier}
import k2b6s9j.boatcraft.core.BoatCraft

/** Contains the methods needed to register Materials and Modifiers with BoatCraft:Core. */
object Registry
{
	/** The Map containing all of the registered Materials for BoatCraft:Core to create boats with. */
	var materials: Map[String, Material] = new HashMap[String, Material]
	
	/** The Map containing all of the registered Modifiers for BoatCraft:Core to create boats with. */
	var modifiers: Map[String, Modifier] = new HashMap[String, Modifier]
	
	/**
	  * Adds materials or modifiers to the Map used by BoatCraft:Core for boat creation.
	  *
	  * @param registrar the object being registered
	  */
	def register(registrar: Any): Unit = registrar match
	{
		case _: Material =>
			materials put (registrar.toString, registrar.asInstanceOf[Material])
		case _: Modifier =>
			modifiers put (registrar.toString, registrar.asInstanceOf[Modifier])
		case x: List[_] =>
			x foreach (obj => register(obj))
		case _ =>
			System.err println "Was unable to register: " + registrar.toString
	}
	
	/**
	  * Removes materials or modifiers from the Map used by BoatCraft:Core for boat creation.
	  *
	  * @param registrar the object being unregistered
	  */
	def unregister(unregistrant: Any): Unit = unregistrant match
	{
		case _: Material =>
			materials remove unregistrant.toString
		case _: Modifier =>
			modifiers remove unregistrant.toString
		case x: List[_] =>
			x foreach (obj => unregister(obj))
		case _ =>
			System.err println "There was nothing to unregister: " + unregistrant.toString
	}

}
