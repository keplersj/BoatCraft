package boatcraft.core.utilities

import java.util.ArrayList
import scala.collection.JavaConversions.{ asScalaBuffer, mapAsScalaMap }
import cpw.mods.fml.common.registry.GameRegistry
import boatcraft.api.Registry
import boatcraft.core.BoatCraft
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{ CraftingManager, IRecipe }
import net.minecraft.nbt.NBTTagCompound
import boatcraft.api.boat.ItemCustomBoat

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
				&& (recipe.asInstanceOf[IRecipe].getRecipeOutput.getItem == Items.boat))
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
		var stack = new ItemStack(ItemCustomBoat)
		stack.stackTagCompound = new NBTTagCompound
		for ((nameMat, material) <- Registry.materials)
		{
			stack.stackTagCompound.setString("material", nameMat)
			for ((blockName, block) <- Registry.blocks)
			{
				stack.stackTagCompound.setString("block", blockName)
				if (block.getContent != null)
					GameRegistry addRecipe (stack copy,
						"MmM",
						"MMM",
						'M': Character, material getItem,
						'm': Character, block getContent)
				else GameRegistry addRecipe (stack copy,
					"M M",
					"MMM",
					'M': Character, material getItem)
			}
		}
	}
}
