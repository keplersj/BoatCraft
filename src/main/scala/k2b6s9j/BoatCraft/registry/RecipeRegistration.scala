package k2b6s9j.BoatCraft.registry

import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.BoatCraft.boat.wood._
import k2b6s9j.BoatCraft.utilities.log.ModLogger
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ShapelessRecipes, ShapedRecipes, CraftingManager, IRecipe}
import net.minecraftforge.oredict.ShapedOreRecipe
import net.minecraftforge.oredict.ShapelessOreRecipe

import java.util

object RecipeRegistration {

  private def RemoveRecipe(resultItem: ItemStack) {
    var recipeResult: ItemStack = null
    val recipes = CraftingManager.getInstance.getRecipeList.asInstanceOf[util.ArrayList[Any]]
    for (scan <- 0 until recipes.size) {
      val tmpRecipe = recipes.get(scan).asInstanceOf[IRecipe]
      tmpRecipe match {
        case recipe: ShapedRecipes => recipeResult = recipe.getRecipeOutput
        case recipe: ShapelessRecipes => recipeResult = recipe.getRecipeOutput
      }

      if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
      {
        ModLogger.info("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult)
        recipes.remove(scan)
      }
    }
  }

  private def AddRecipe(item: ItemStack, parts: AnyRef*) {
    GameRegistry.addRecipe(new ShapedOreRecipe(item, parts))
  }

  private def AddShapelessRecipe(item: ItemStack, parts: AnyRef*) {
    CraftingManager.getInstance().getRecipeList.asInstanceOf[util.ArrayList[Any]].add(new ShapelessOreRecipe(item, parts))
  }


    def RegisterRecipes() {
    //Boat Recipes
    if (!ConfigRegistry.OreDictWoodBoat) {
      RemoveRecipe(new ItemStack(Item.boat))
      GameRegistry.addRecipe(new ItemStack(oak.Empty.Item.asInstanceOf[Item]), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0))
      AddShapelessRecipe(new ItemStack(oak.Chest.Item.asInstanceOf[Item]), new ItemStack(Block.chest), "boatOak")
      AddShapelessRecipe(new ItemStack(oak.Furnace.Item.asInstanceOf[Item]), new ItemStack(Block.furnaceIdle), "boatOak")
      AddShapelessRecipe(new ItemStack(oak.TNT.Item.asInstanceOf[Item]), new ItemStack(Block.tnt), "boatOak")
      AddShapelessRecipe(new ItemStack(oak.Hopper.Item.asInstanceOf[Item]), new ItemStack(Block.hopperBlock), "boatOak")
      GameRegistry.addRecipe(new ItemStack(spruce.Empty.Item.asInstanceOf[Item]), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1))
      AddShapelessRecipe(new ItemStack(spruce.Chest.Item.asInstanceOf[Item]), new ItemStack(Block.chest), "boatSpruce")
      AddShapelessRecipe(new ItemStack(spruce.Furnace.Item.asInstanceOf[Item]), new ItemStack(Block.furnaceIdle), "boatSpruce")
      AddShapelessRecipe(new ItemStack(spruce.TNT.Item.asInstanceOf[Item]), new ItemStack(Block.tnt), "boatSpruce")
      AddShapelessRecipe(new ItemStack(spruce.Hopper.Item.asInstanceOf[Item]), new ItemStack(Block.hopperBlock), "boatSpruce")
      GameRegistry.addRecipe(new ItemStack(birch.Empty.Item.asInstanceOf[Item]), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2))
      AddShapelessRecipe(new ItemStack(birch.Chest.Item.asInstanceOf[Item]), new ItemStack(Block.chest), "boatBirch")
      AddShapelessRecipe(new ItemStack(birch.Furnace.Item.asInstanceOf[Item]), new ItemStack(Block.furnaceIdle), "boatBirch")
      AddShapelessRecipe(new ItemStack(birch.TNT.Item.asInstanceOf[Item]), new ItemStack(Block.tnt), "boatBirch")
      AddShapelessRecipe(new ItemStack(birch.Hopper.Item.asInstanceOf[Item]), new ItemStack(Block.hopperBlock), "boatBirch")
      GameRegistry.addRecipe(new ItemStack(jungle.Empty.Item.asInstanceOf[Item]), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3))
      AddShapelessRecipe(new ItemStack(jungle.Chest.Item.asInstanceOf[Item]), new ItemStack(Block.chest), "boatJungle")
      AddShapelessRecipe(new ItemStack(jungle.Furnace.Item.asInstanceOf[Item]), new ItemStack(Block.furnaceIdle), "boatJungle")
      AddShapelessRecipe(new ItemStack(jungle.TNT.Item.asInstanceOf[Item]), new ItemStack(Block.tnt), "boatJungle")
      AddShapelessRecipe(new ItemStack(jungle.Hopper.Item.asInstanceOf[Item]), new ItemStack(Block.hopperBlock), "boatJungle")
    }
    if (ConfigRegistry.OreDictWoodBoat) {
      RemoveRecipe(new ItemStack(Item.boat))
      AddRecipe(new ItemStack(oak.Empty.Item.asInstanceOf[Item]), "W W", "WWW", Character.valueOf('W'), "plankWood")
      AddShapelessRecipe(new ItemStack(oak.Chest.Item.asInstanceOf[Item]), new ItemStack(Block.chest), "boat")
      AddShapelessRecipe(new ItemStack(oak.Furnace.Item.asInstanceOf[Item]), new ItemStack(Block.furnaceIdle), "boat")
      AddShapelessRecipe(new ItemStack(oak.TNT.Item.asInstanceOf[Item]), new ItemStack(Block.tnt), "boat")
      AddShapelessRecipe(new ItemStack(oak.Hopper.Item.asInstanceOf[Item]), new ItemStack(Block.hopperBlock), "boat")
    }
  }
}
