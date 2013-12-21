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
      if (tmpRecipe.isInstanceOf[ShapedRecipes])
      {
        val recipe: ShapedRecipes = tmpRecipe.asInstanceOf[ShapedRecipes]
        recipeResult = recipe.getRecipeOutput
      }

      if (tmpRecipe.isInstanceOf[ShapelessRecipes])
      {
        val recipe: ShapelessRecipes = tmpRecipe.asInstanceOf[ShapelessRecipes]
        recipeResult = recipe.getRecipeOutput
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
      GameRegistry.addRecipe(new ItemStack(new oak.Empty.Item(oak.Empty.Item.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0))
      AddShapelessRecipe(new ItemStack(new oak.Chest.Item(oak.Chest.Item.ID)), new ItemStack(Block.chest), "boatOak")
      AddShapelessRecipe(new ItemStack(new oak.Furnace.Item(oak.Furnace.Item.ID)), new ItemStack(Block.furnaceIdle), "boatOak")
      AddShapelessRecipe(new ItemStack(new oak.TNT.Item(oak.TNT.Item.ID)), new ItemStack(Block.tnt), "boatOak")
      AddShapelessRecipe(new ItemStack(new oak.Hopper.Item(oak.Hopper.Item.ID)), new ItemStack(Block.hopperBlock), "boatOak")
      GameRegistry.addRecipe(new ItemStack(new spruce.Empty.Item(spruce.Empty.Item.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1))
      AddShapelessRecipe(new ItemStack(new spruce.Chest.Item(spruce.Chest.Item.ID)), new ItemStack(Block.chest), "boatSpruce")
      AddShapelessRecipe(new ItemStack(new spruce.Furnace.Item(spruce.Furnace.Item.ID)), new ItemStack(Block.furnaceIdle), "boatSpruce")
      AddShapelessRecipe(new ItemStack(new spruce.TNT.Item(spruce.TNT.Item.ID)), new ItemStack(Block.tnt), "boatSpruce")
      AddShapelessRecipe(new ItemStack(new spruce.Hopper.Item(spruce.Hopper.Item.ID)), new ItemStack(Block.hopperBlock), "boatSpruce")
      GameRegistry.addRecipe(new ItemStack(new birch.Empty.Item(birch.Empty.Item.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2))
      AddShapelessRecipe(new ItemStack(new birch.Chest.Item(birch.Chest.Item.ID)), new ItemStack(Block.chest), "boatBirch")
      AddShapelessRecipe(new ItemStack(new birch.Furnace.Item(birch.Furnace.Item.ID)), new ItemStack(Block.furnaceIdle), "boatBirch")
      AddShapelessRecipe(new ItemStack(new birch.TNT.Item(birch.TNT.Item.ID)), new ItemStack(Block.tnt), "boatBirch")
      AddShapelessRecipe(new ItemStack(new birch.Hopper.Item(birch.Hopper.Item.ID)), new ItemStack(Block.hopperBlock), "boatBirch")
      GameRegistry.addRecipe(new ItemStack(new jungle.Empty.Item(jungle.Empty.Item.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3))
      AddShapelessRecipe(new ItemStack(new jungle.Chest.Item(jungle.Chest.Item.ID)), new ItemStack(Block.chest), "boatJungle")
      AddShapelessRecipe(new ItemStack(new jungle.Furnace.Item(jungle.Furnace.Item.ID)), new ItemStack(Block.furnaceIdle), "boatJungle")
      AddShapelessRecipe(new ItemStack(new jungle.TNT.Item(jungle.TNT.Item.ID)), new ItemStack(Block.tnt), "boatJungle")
      AddShapelessRecipe(new ItemStack(new jungle.Hopper.Item(jungle.Hopper.Item.ID)), new ItemStack(Block.hopperBlock), "boatJungle")
    }
    if (ConfigRegistry.OreDictWoodBoat) {
      RemoveRecipe(new ItemStack(Item.boat))
      AddRecipe(new ItemStack(new oak.Empty.Item(oak.Empty.Item.ID)), "W W", "WWW", Character.valueOf('W'), "plankWood")
      AddShapelessRecipe(new ItemStack(new oak.Chest.Item(oak.Chest.Item.ID)), new ItemStack(Block.chest), "boat")
      AddShapelessRecipe(new ItemStack(new oak.Furnace.Item(oak.Furnace.Item.ID)), new ItemStack(Block.furnaceIdle), "boat")
      AddShapelessRecipe(new ItemStack(new oak.TNT.Item(oak.TNT.Item.ID)), new ItemStack(Block.tnt), "boat")
      AddShapelessRecipe(new ItemStack(new oak.Hopper.Item(oak.Hopper.Item.ID)), new ItemStack(Block.hopperBlock), "boat")
    }
  }
}
