package boatcraft.compatibility

import java.util

import scala.collection.JavaConversions.asScalaBuffer

import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.compatibility.vanilla.VanillaGuiHandler
import boatcraft.compatibility.vanilla.modifiers.blocks.Chest
import boatcraft.compatibility.vanilla.modifiers.blocks.Furnace
import boatcraft.compatibility.vanilla.modifiers.blocks.TNT
import boatcraft.compatibility.vanilla.modifiers.blocks.Workbench
import boatcraft.compatibility.vanilla.modifiers.materials.wood.OreDict_Wood
import boatcraft.core.BoatCraft
import boatcraft.core.GUIHandler
import boatcraft.core.utilities.Helper
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.IRecipe
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.ShapedOreRecipe

object Vanilla extends CompatModule {

	var useOreDictWood = false

	override def doPreInit(event: FMLPreInitializationEvent) {
		readConfig

		replaceBoatRecipe

		GUIHandler.handlerMap.put(code, VanillaGuiHandler)
	}

	override def doPostInit(event: FMLPostInitializationEvent) {
		val toRemove = new util.ArrayList[IRecipe]

		for (recipe <- CraftingManager.getInstance.getRecipeList) {
			recipe match {
				case recipe: IRecipe if !recipe.isInstanceOf[ShapedOreRecipe] && recipe.getRecipeOutput != null && recipe.getRecipeOutput.stackTagCompound != null && recipe.getRecipeOutput.stackTagCompound.getString("material")
					!= null && recipe.getRecipeOutput.stackTagCompound.getString("material")
					.equals(OreDict_Wood toString) =>
					toRemove add recipe
					log info "Removed non-oredict ore-dict wood boat recipe: " + recipe
				case _ =>
			}
		}

		CraftingManager.getInstance.getRecipeList removeAll toRemove
	}

	override protected def getBlocks =
		Array[Block](
			Chest,
			Furnace,
			Workbench,
			TNT)
	
	private def replaceBoatRecipe {
		Helper.Recipe.removeRecipe(new ItemStack(Items.boat))

		val stack = new ItemStack(ItemCustomBoat)

		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString("material", "minecraft:planks@1")
		stack.stackTagCompound setString("block", Block.Empty.toString)

		GameRegistry addShapelessRecipe(stack, Items.boat)
	}
	
	private def readConfig {
		useOreDictWood = BoatCraft.config get("Vanilla.General", "useOreDictWoods", false,
			"If set to true, the different wood types will not be generated.\n" +
				"Instead, there will be only one \"wood\" Boat") getBoolean false

		if (BoatCraft.config.hasChanged)
			BoatCraft.config.save
	}
}
