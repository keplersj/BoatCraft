package k2b6s9j.BoatCraft.registry

import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.BoatCraft.utilities.log.ModLogger
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.oredict.ShapedOreRecipe
import net.minecraftforge.oredict.ShapelessOreRecipe

import k2b6s9j.BoatCraft.item.boat.wood.oak._
import k2b6s9j.BoatCraft.item.boat.wood.spruce._
import k2b6s9j.BoatCraft.item.boat.wood.birch._
import k2b6s9j.BoatCraft.item.boat.wood.jungle._
import java.util

object RecipeRegistration {

    def RemoveRecipe(resultItem:ItemStack)
    {
      ItemStack.recipeResult = null
      val recipes: util.ArrayLists = util.ArrayList.asScala.CraftingManager.getInstance().getRecipeList()

      for ( scan < recipes.size()<- scan.++)
        {
            IRecipe.tmpRecipe = IRecipe.recipes.get(scan)
          recipeResult = tmpRecipe.getRecipeOutput()

          if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
            {
                ModLogger.info("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult)
              recipes.remove(scan)
            }
        }
    }

    def AddRecipe(item:ItemStack, parts:Object*)
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(item, parts))
    }

    def AddShapelessRecipe(item:ItemStack, parts:Object*)
    {
        CraftingManager.getInstance().getRecipeList.add(new ShapelessOreRecipe(item, parts))
    }

    def RegisterRecipes() {
        //Boat Recipes
        if (!ConfigRegistry.OreDictWoodBoat) {
            RemoveRecipe(new ItemStack(Item.boat))
          GameRegistry.addRecipe(new ItemStack(new BoatOak(BoatOak.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 0))
          AddShapelessRecipe(new ItemStack(new BoatOakChest(BoatOakChest.ID)), new ItemStack(Block.chest), "boatOak")
          AddShapelessRecipe(new ItemStack(new BoatOakFurnace(BoatOakFurnace.ID)), new ItemStack(Block.furnaceIdle), "boatOak")
          AddShapelessRecipe(new ItemStack(new BoatOakTNT(BoatOakTNT.ID)), new ItemStack(Block.tnt), "boatOak")
          AddShapelessRecipe(new ItemStack(new BoatOakHopper(BoatOakHopper.ID)), new ItemStack(Block.hopperBlock), "boatOak")
          GameRegistry.addRecipe(new ItemStack(new BoatSpruce(BoatSpruce.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1))
          AddShapelessRecipe(new ItemStack(new BoatSpruceChest(BoatSpruceChest.ID)), new ItemStack(Block.chest), "boatSpruce")
          AddShapelessRecipe(new ItemStack(new BoatSpruceFurnace(BoatSpruceFurnace.ID)), new ItemStack(Block.furnaceIdle), "boatSpruce")
          AddShapelessRecipe(new ItemStack(new BoatSpruceTNT(BoatSpruceTNT.ID)), new ItemStack(Block.tnt), "boatSpruce")
          AddShapelessRecipe(new ItemStack(new BoatSpruceHopper(BoatSpruceHopper.ID)), new ItemStack(Block.hopperBlock), "boatSpruce")
          GameRegistry.addRecipe(new ItemStack(new BoatBirch(BoatBirch.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 2))
          AddShapelessRecipe(new ItemStack(new BoatBirchChest(BoatBirchChest.ID)), new ItemStack(Block.chest), "boatBirch")
          AddShapelessRecipe(new ItemStack(new BoatBirchFurnace(BoatBirchFurnace.ID)), new ItemStack(Block.furnaceIdle), "boatBirch")
          AddShapelessRecipe(new ItemStack(new BoatBirchTNT(BoatBirchTNT.ID)), new ItemStack(Block.tnt), "boatBirch")
          AddShapelessRecipe(new ItemStack(new BoatBirchHopper(BoatBirchHopper.ID)), new ItemStack(Block.hopperBlock), "boatBirch")
          GameRegistry.addRecipe(new ItemStack(new BoatJungle(BoatJungle.ID)), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 3))
          AddShapelessRecipe(new ItemStack(new BoatJungleChest(BoatJungleChest.ID)), new ItemStack(Block.chest), "boatJungle")
          AddShapelessRecipe(new ItemStack(new BoatJungleFurnace(BoatJungleFurnace.ID)), new ItemStack(Block.furnaceIdle), "boatJungle")
          AddShapelessRecipe(new ItemStack(new BoatJungleTNT(BoatJungleTNT.ID)), new ItemStack(Block.tnt), "boatJungle")
          AddShapelessRecipe(new ItemStack(new BoatJungleHopper(BoatJungleHopper.ID)), new ItemStack(Block.hopperBlock), "boatJungle")
        }
        if (ConfigRegistry.OreDictWoodBoat) {
            RemoveRecipe(new ItemStack(Item.boat))
          AddRecipe(new ItemStack(new BoatOak(BoatOak.ID)), "W W", "WWW", Character.valueOf('W'), "plankWood")
          AddShapelessRecipe(new ItemStack(new BoatOakChest(BoatOakChest.ID)), new ItemStack(Block.chest), "boat")
          AddShapelessRecipe(new ItemStack(new BoatOakFurnace(BoatOakFurnace.ID)), new ItemStack(Block.furnaceIdle), "boat")
          AddShapelessRecipe(new ItemStack(new BoatOakTNT(BoatOakTNT.ID)), new ItemStack(Block.tnt), "boat")
          AddShapelessRecipe(new ItemStack(new BoatOakHopper(BoatOakHopper.ID)), new ItemStack(Block.hopperBlock), "boat")
        }
    }
}
