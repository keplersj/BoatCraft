package boatcraft.compatibility

import org.apache.logging.log4j.Logger
import boatcraft.api.Registry
import boatcraft.compatibility.ic2.materials._
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event._
import boatcraft.compatibility.ic2.modifiers.Generator

object IC2 extends CompatModule
{
	var log: Logger = null
	
	@Optional.Method(modid = "IC2")
	override def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog

		try
		{
			addMaterials
			addModifiers
		}
		catch
		{
			case ex: NoClassDefFoundError => //That's OK
			case err: NoSuchMethodError => //Fine
			case ex: NoSuchMethodException => //No problem
			case ex: NullPointerException => //Sure
			case thr: Throwable => thr printStackTrace //Weird...
		}
	}
	
	@Optional.Method(modid = "IC2")
	override def postInit(e: FMLPostInitializationEvent)
	{
		try
		{
			//TODO for some reason I can't access the IC2Items class from here, investigate
			//Recipes.removeRecipe(IC2Items getItem "boatRubber")
			//Recipes.removeRecipe(IC2Items getItem "boatCarbon")
		}
		catch
		{
			case ex: NoClassDefFoundError => //That's OK
			case err: NoSuchMethodError => //Fine
			case ex: NoSuchMethodException => //No problem
			case ex: NullPointerException => //Sure
			case thr: Throwable => thr printStackTrace //Weird...
		}
	}
	
	@Optional.Method(modid = "IC2")
	private def addMaterials
	{
		Registry register Rubber
		Registry register Carbon
	}
	
	@Optional.Method(modid = "IC2")
	private def addModifiers
	{
		Registry register Generator
	}
}