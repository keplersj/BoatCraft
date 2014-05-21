package boatcraft.compatibility

import java.util

import scala.collection.JavaConversions.asScalaBuffer

import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.api.modifiers.Material
import boatcraft.compatibility.vanilla.VanillaGuiHandler
import boatcraft.compatibility.vanilla.modifiers.blocks.Chest
import boatcraft.compatibility.vanilla.modifiers.blocks.Furnace
import boatcraft.compatibility.vanilla.modifiers.blocks.TNT
import boatcraft.compatibility.vanilla.modifiers.blocks.Workbench
import boatcraft.compatibility.vanilla.modifiers.materials.crystal.Diamond
import boatcraft.compatibility.vanilla.modifiers.materials.crystal.Emerald
import boatcraft.compatibility.vanilla.modifiers.materials.crystal.Obsidian
import boatcraft.compatibility.vanilla.modifiers.materials.metal.Gold
import boatcraft.compatibility.vanilla.modifiers.materials.metal.Iron
import boatcraft.compatibility.vanilla.modifiers.materials.wood.Acacia
import boatcraft.compatibility.vanilla.modifiers.materials.wood.Birch
import boatcraft.compatibility.vanilla.modifiers.materials.wood.DarkOak
import boatcraft.compatibility.vanilla.modifiers.materials.wood.Jungle
import boatcraft.compatibility.vanilla.modifiers.materials.wood.Oak
import boatcraft.compatibility.vanilla.modifiers.materials.wood.OreDict_Wood
import boatcraft.compatibility.vanilla.modifiers.materials.wood.Spruce
import boatcraft.core.BoatCraft
import boatcraft.core.GUIHandler
import boatcraft.core.modifiers.blocks.Empty
import boatcraft.core.utilities.Recipes
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

	private var useOreDictWood = false

	var materials = Array[Material]()

	override def doPreInit(event: FMLPreInitializationEvent) {
		readConfig

		replaceBoatRecipe

		GUIHandler.handlerMap.put(code, VanillaGuiHandler)

		if (!useOreDictWood) {
			materials = materials ++ woodMaterials
		}
		else {
			materials = materials ++ List[Material](OreDict_Wood)
		}
		materials = materials ++ metalMaterials
		materials = materials ++ crystalMaterials
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

	override protected def getMaterials = materials

	private val woodMaterials: List[Material] = List[Material](
		Oak,
		Spruce,
		Birch,
		Jungle,
		Acacia,
		DarkOak
	)

	private val metalMaterials: List[Material] = List[Material](
		Iron,
		Gold
	)

	private val crystalMaterials: List[Material] = List[Material](
		Obsidian,
		Diamond,
		Emerald
	)

	override protected def getBlocks =
		Array[Block](
			Chest,
			Furnace,
			Workbench,
			TNT)

	private def replaceBoatRecipe {
		Recipes removeRecipe new ItemStack(Items.boat)

		val stack = new ItemStack(ItemCustomBoat)

		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString("material", Oak.toString)
		stack.stackTagCompound setString("block", Empty.toString)

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
