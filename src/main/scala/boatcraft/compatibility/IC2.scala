package boatcraft.compatibility

import boatcraft.api.modifiers.Block
import boatcraft.api.modifiers.Material
import boatcraft.compatibility.industrialcraft2.IC2GuiHandler
import boatcraft.compatibility.industrialcraft2.modifiers.blocks.Generator
import boatcraft.compatibility.industrialcraft2.modifiers.blocks.Nuke
import boatcraft.compatibility.industrialcraft2.modifiers.materials.Carbon
import boatcraft.compatibility.industrialcraft2.modifiers.materials.Rubber
import boatcraft.core.GUIHandler
import boatcraft.core.utilities.Helper
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import ic2.api.item.IC2Items

object IC2 extends CompatModule {
	
	@Optional.Method(modid = "IC2")
	override protected def doPreInit(e: FMLPreInitializationEvent) {
		GUIHandler.handlerMap.put(code, IC2GuiHandler)
	}
	
	@Optional.Method(modid = "IC2")
	override protected def doPostInit(e: FMLPostInitializationEvent) {
		try {
			Helper.Recipe.removeRecipe(IC2Items getItem "boatRubber")
			Helper.Recipe.removeRecipe(IC2Items getItem "boatCarbon")
		}
		catch {
			case ex: NoClassDefFoundError => //That's OK
			case err: NoSuchMethodError => //Fine
			case ex: NoSuchMethodException => //No problem
			case ex: NullPointerException => //Sure
			case thr: Throwable => thr printStackTrace() //Weird...
		}
	}
	
	@Optional.Method(modid = "IC2")
	override protected def getBlocks = Array[Block](
		Generator,
		Nuke
	)
}