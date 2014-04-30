package boatcraft.compatibility

import org.apache.logging.log4j.Logger
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import boatcraft.core.utilities.Recipes
import boatcraft.api.Registry
import boatcraft.compatibility.ic2.Carbon
import boatcraft.compatibility.ic2.Rubber

@Mod(modid = "boatcraft:compatibility:IC2", name = "BoatCraft IndustrialCraft^2 Compatibility",
	version = "2.0", dependencies = "required-after:boatcraft;after:IC2", modLanguage = "scala")
object IC2
{
	var log: Logger = null
	
	@EventHandler
	@Optional.Method(modid = "IC2")
	def preInit(e: FMLPreInitializationEvent)
	{
		log = e getModLog

		try
		{
			addMaterials
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
	
	@EventHandler
	@Optional.Method(modid = "IC2")
	def postInit(e: FMLPostInitializationEvent)
	{
		try
		{
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
}