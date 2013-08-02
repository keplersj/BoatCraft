package k2b6s9j.BoatCraft.utilities;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingUtilities {

	public static void RemoveRecipe(ItemStack resultItem)
	{
	    ItemStack recipeResult = null;
	    ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();

	    for (int scan = 0; scan < recipes.size(); scan++)
	    {
	        IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
	        recipeResult = tmpRecipe.getRecipeOutput();

		if (ItemStack.areItemStacksEqual(resultItem, recipeResult))
	        {
	            System.out.println("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult);
	            recipes.remove(scan);
	        }
	    }
	}

	public static void AddRecipe(ItemStack item, Object... parts)
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(item, parts));
	}

	public static void AddShapelessRecipe(ItemStack item, Object... parts)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(item, parts));
	}
}