package boatcraft.compatibility.nei

import codechicken.nei.recipe.ShapedRecipeHandler
import java.util.List
import net.minecraft.item.ItemStack
import java.util.ArrayList
import boatcraft.api.Registry
import boatcraft.api.modifiers.Material
import scala.collection.JavaConversions._
import codechicken.nei.PositionedStack
import boatcraft.core.RecipeBoat
import codechicken.nei.InventoryCraftingDummy
import net.minecraft.inventory.InventoryCrafting
import boatcraft.api.modifiers.Block
import net.minecraft.init.Blocks

object BoatRecipeHandler extends ShapedRecipeHandler {
	
	class CachedBoatRecipe(material: String, block: String)
		extends CachedShapedRecipe(0, 0, null, new ItemStack(Blocks.air))
	{
		var itemList: List[AnyRef] = new ArrayList[AnyRef]
		
		var materialItems: Array[ItemStack] = null
		if (material != null) materialItems = Array[ItemStack](Registry.findOfType[Material](material).getItem)
		else materialItems = (for {(item, value) <- Registry.materialItems} yield item.stack).toArray
		
		var blockItems: Array[ItemStack] = null
		if (block != null) blockItems = Array[ItemStack](Registry.findOfType[Block](block).getContent)
		else blockItems = (for {(item, value) <- Registry.blockItems} yield item.stack).toArray
		
		var inventory: InventoryCrafting = new InventoryCraftingDummy
		
		for (i <- 0 until 3; j <- 0 until 2)
		{
			if (i != 1 || j != 0) {
				itemList.add(materialItems)
				inventory.setInventorySlotContents(j * 3 + i, materialItems(0))
			}
			else {
				itemList.add(blockItems)
				inventory.setInventorySlotContents(j * 3 + i, blockItems(0))
			}
		}
		
		setIngredients(3, 2, itemList.toArray)
		
		result = new PositionedStack(RecipeBoat.getCraftingResult(inventory), 119, 24)
		
		override def setIngredients(width: Int, height: Int, items: Array[AnyRef]) {
            for (x <- 0 until width) {
                for (y <- 0 until height) {
                    if (items(y * width + x) != null) {
                    	var stack = new PositionedStack(items(y * width + x), 25 + x * 18, 6 + y * 18, true)
                    	stack.setMaxSize(1)
                    	ingredients.add(stack)
                    }
                }
            }
        }
	}
}