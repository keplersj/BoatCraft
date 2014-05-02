package boatcraft.compatibility

import org.apache.logging.log4j.Logger
import boatcraft.api.Registry
import boatcraft.compatibility.industrialcraft.materials._
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event._
import boatcraft.compatibility.industrialcraft.blocks.Generator
import boatcraft.core.utilities.Recipes
import ic2.api.item.IC2Items

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
			addBlocks
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
			Recipes.removeRecipe(IC2Items getItem "boatRubber")
			Recipes.removeRecipe(IC2Items getItem "boatCarbon")
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
	private def addBlocks
	{
		Registry register Generator
	}
}