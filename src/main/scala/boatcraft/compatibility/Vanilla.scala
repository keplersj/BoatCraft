package boatcraft.compatibility

import scala.collection.JavaConversions.asScalaBuffer
import boatcraft.api.boat.ItemCustomBoat
import boatcraft.api.traits.{Block, Material}
import boatcraft.compatibility.vanilla.VanillaGuiHandler
import boatcraft.compatibility.vanilla.materials.crystal.{Diamond, Emerald, Obsidian}
import boatcraft.compatibility.vanilla.materials.metal.{Gold, Iron}
import boatcraft.compatibility.vanilla.materials.wood.{Acacia, Birch, DarkOak, Jungle, Oak, OreDict_Wood, Spruce}
import boatcraft.compatibility.vanilla.blocks.{Furnace, Chest, Workbench}
import boatcraft.core.{BoatCraft, GUIHandler}
import boatcraft.core.utilities.Recipes
import cpw.mods.fml.common.event.{FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.oredict.ShapedOreRecipe
import boatcraft.core.blocks.Empty
import java.util

object Vanilla extends CompatModule {

	private var useOreDictWood = false

	var materials = List[Material]()

	override def doPreInit(event: FMLPreInitializationEvent) {
		readConfig()

		replaceBoatRecipe()

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

	override protected def getMaterials: List[Material] = materials

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

	override protected def getBlocks: List[Block] =
		List[Block](
			Chest,
			Furnace,
			Workbench)

	private def replaceBoatRecipe() {
		Recipes removeRecipe new ItemStack(Items.boat)

		val stack = new ItemStack(ItemCustomBoat)

		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString("material", Oak.toString)
		stack.stackTagCompound setString("block", Empty.toString)

		GameRegistry addShapelessRecipe(stack, Items.boat)
	}

	private def readConfig() {
		useOreDictWood = BoatCraft.config get("Vanilla.General", "useOreDictWoods", false,
			"If set to true, the different wood types will not be generated.\n" +
				"Instead, there will be only one \"wood\" Boat") getBoolean false

		if (BoatCraft.config.hasChanged)
			BoatCraft.config save()
	}
}
