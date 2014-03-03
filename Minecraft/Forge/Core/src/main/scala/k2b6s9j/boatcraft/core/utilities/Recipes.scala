package k2b6s9j.boatcraft.core.utilities

import java.util.ArrayList
import scala.collection.JavaConversions._
import k2b6s9j.boatcraft.core.BoatCraft
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import cpw.mods.fml.common.registry.GameRegistry
import k2b6s9j.boatcraft.api.Registry

/**
 * Contains miscellaneous methods used throughout the mod related to crafting.
 */
object Recipes
{
  /**
   * Removes recipes from the master crafting recipe list.
   * Primarily used to remove the vanilla boat recipe from the game.
   *
   * @param resultItem the item whose recipe is being removed
   */
  def removeRecipe(resultItem: ItemStack)
	{
		var toRemove = new ArrayList[IRecipe]

		for (recipe <- CraftingManager.getInstance getRecipeList)
		{
			if (recipe.isInstanceOf[IRecipe]
				&& (recipe.asInstanceOf[IRecipe].getRecipeOutput != null)
				&& (recipe.asInstanceOf[IRecipe].getRecipeOutput.getItem equals Item.boat))
			{
				toRemove add recipe.asInstanceOf[IRecipe]
				BoatCraft.log info "Removed vanilla Boat recipe: " + recipe
			}
		}

		CraftingManager.getInstance.getRecipeList removeAll toRemove
	}

  /**
   * Adds recipes and NBT tag compounds for all registered boats.
   */
  def addBoatRecipes
	{
		var stack = new ItemStack(BoatCraft.itemBoat)
		stack.stackTagCompound = new NBTTagCompound
		for ((nameMat, material) <- Registry.materials)
		{
			stack.stackTagCompound.setString("material", nameMat)
			for ((nameMod, modifier) <- Registry.modifiers)
			{
				stack.stackTagCompound.setString("modifier", nameMod)
				if (modifier.getContent != null)
					GameRegistry addRecipe(stack copy,
						"MmM",
						"MMM",
						'M': Character, material getItem,
						'm': Character, modifier.getContent)
				else GameRegistry addRecipe(stack copy,
						"M M",
						"MMM",
						'M': Character, material getItem)
			}
		}
	}
}
