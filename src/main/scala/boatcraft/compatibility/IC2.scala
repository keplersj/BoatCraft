package boatcraft.compatibility

import java.util.{Arrays, List}

import boatcraft.api.traits.{Material, Modifier}
import boatcraft.compatibility.industrialcraft2.materials.{Carbon, Rubber}
import boatcraft.compatibility.industrialcraft2.modifiers.Generator
import boatcraft.core.utilities.Recipes
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import ic2.api.item.IC2Items

object IC2 extends CompatModule
{
	override protected def doPostInit(e: FMLPostInitializationEvent)
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
	override protected def getMaterials: List[Material] =
		Arrays asList(Rubber, Carbon)
	
	@Optional.Method(modid = "IC2")
	override protected def getModifiers: List[Modifier] =
		Arrays asList Generator
}