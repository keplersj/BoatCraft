package k2b6s9j.boatcraft.core.utilities

import java.util.ArrayList
import scala.collection.JavaConversions._
import k2b6s9j.boatcraft.core.BoatCraft
import k2b6s9j.boatcraft.core.registry.MaterialRegistry
import k2b6s9j.boatcraft.core.traits.Material
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import k2b6s9j.boatcraft.core.registry.ModifierRegistry
import k2b6s9j.boatcraft.core.traits.Modifier
import cpw.mods.fml.common.registry.GameRegistry

object Recipes
{
	def removeRecipe(resultItem: ItemStack)
	{
		var toRemove = new ArrayList[IRecipe]

		for (recipe <- CraftingManager.getInstance getRecipeList)
		{
			if (recipe.isInstanceOf[IRecipe]
				&& (recipe.asInstanceOf[IRecipe].getRecipeOutput != null)
				&& (recipe.asInstanceOf[IRecipe].getRecipeOutput.getItem equals Items.boat))
			{
				toRemove add recipe.asInstanceOf[IRecipe]
				BoatCraft.log info "Removed vanilla Boat recipe: " + recipe
			}
		}

		CraftingManager.getInstance.getRecipeList removeAll toRemove
	}

	def addBoatRecipes
	{
		var stack = new ItemStack(BoatCraft.itemBoat)
		stack.stackTagCompound = new NBTTagCompound
		for ((nameMat: String, material: Material) <- MaterialRegistry.materials)
		{
			stack.stackTagCompound.setString("material", nameMat)
			for ((nameMod: String, modifier: Modifier) <- ModifierRegistry.modifiers)
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
