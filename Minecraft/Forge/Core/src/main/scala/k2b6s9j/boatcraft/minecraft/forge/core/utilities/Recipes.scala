package k2b6s9j.BoatCraft.core.utilities

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ShapelessRecipes, ShapedRecipes, CraftingManager, IRecipe}
import java.util
import k2b6s9j.BoatCraft.core.BoatCraft

object Recipes {

  def RemoveRecipe(resultItem: ItemStack) {
    var recipeResult: ItemStack = null
    val recipes = CraftingManager.getInstance.getRecipeList.asInstanceOf[util.ArrayList[Any]]
    for (scan <- 0 until recipes.size) {
      val tmpRecipe = recipes.get(scan).asInstanceOf[IRecipe]
      tmpRecipe match {
        case recipe: ShapedRecipes =>
          recipeResult = recipe.getRecipeOutput
        case _ =>
      }

      tmpRecipe match {
        case recipe: ShapelessRecipes =>
          recipeResult = recipe.getRecipeOutput
        case _ =>
      }

      if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
      {
        BoatCraft.log.info("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult)
        recipes.remove(scan)
      }
    }
  }
}
