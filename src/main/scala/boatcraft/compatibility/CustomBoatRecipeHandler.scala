package boatcraft.compatibility

import codechicken.nei.recipe.ShapedRecipeHandler
import java.util.List
import java.util.LinkedList
import net.minecraft.item.ItemStack
import net.minecraft.init.Items
import codechicken.nei.PositionedStack
import java.util.ArrayList
import boatcraft.api.Registry
import boatcraft.api.modifiers.Material

object CustomBoatRecipeHandler extends ShapedRecipeHandler {
	
	class CachedFireworkRecipe(material: String, block: String)
		extends CachedShapedRecipe(0, 0, null, null)
	{
		var itemList: List[AnyRef] = new ArrayList[AnyRef]
		
		var x: ItemStack = null
		if (material != null) x = Registry.findOfType[Material](material).getItem
		else x = ???
		for (i <- 0 until 3; j <- 0 until 2 if (i != 1 || j != 0)) itemList.add(j * 3 + i, x)
		
		setIngredients(3, 2, itemList.toArray())
	}
}