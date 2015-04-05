package boatcraft.compatibility

import boatcraft.api.modifiers.Block
import boatcraft.compatibility.industrialcraft2.IC2GuiHandler
import boatcraft.compatibility.industrialcraft2.modifiers.blocks.Generator
import boatcraft.compatibility.industrialcraft2.modifiers.blocks.Nuke
import boatcraft.core.GUIHandler
import boatcraft.core.utilities.Helper
import cpw.mods.fml.common.Optional
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import ic2.api.item.IC2Items
import ic2.core.Ic2Items
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import boatcraft.api.boat.ItemCustomBoat
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.nbt.NBTTagCompound

object IC2 extends CompatModule {
	
	@Optional.Method(modid = "IC2")
	override protected def doPreInit(e: FMLPreInitializationEvent) {
		GUIHandler.handlerMap.put(code, IC2GuiHandler)
	}
	
	@Optional.Method(modid = "IC2")
	override protected def doPostInit(e: FMLPostInitializationEvent) {
		try {
			replaceBoatRecipes()
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
	
	private def replaceBoatRecipes() {
		Helper.Recipe.removeRecipe(Ic2Items.boatCarbon)
		Helper.Recipe.removeRecipe(Ic2Items.boatRubber)
		
		val stack = new ItemStack(ItemCustomBoat)
		
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString("material", "carbonfibre")
		stack.stackTagCompound setString("block", Block.Empty.toString)

		GameRegistry addShapelessRecipe(stack, Ic2Items.boatCarbon)
		
		stack.stackTagCompound setString("material", "rubber")
		GameRegistry addShapelessRecipe(stack, Ic2Items.boatRubber)
	}
}