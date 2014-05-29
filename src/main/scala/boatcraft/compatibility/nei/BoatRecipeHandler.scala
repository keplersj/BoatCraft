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

class BoatRecipeHandler extends ShapedRecipeHandler {

	import BoatRecipeHandler._
	
	var mRecipes: List[CachedBoatRecipe] = new ArrayList[CachedBoatRecipe]
	
	for (material <- Registry.materials.keySet) {
		
		mRecipes.add(new CachedBoatRecipe(material, null))
		for (block <- Registry.blocks.keySet) mRecipes.add(new CachedBoatRecipe(material, block))
	}
	
	for (block <- Registry.blocks.keySet) mRecipes.add(new CachedBoatRecipe(null, block))
	
	override def loadCraftingRecipes(result: ItemStack) {
		
		val material = Registry.getMaterial(result) toString
		val block = Registry.getBlock(result) toString
		
		for (recipe <- mRecipes if recipe.material == material && recipe.block == block) {
			arecipes.add(recipe)
		}
	}
	
	override def loadCraftingRecipes(id: String, results: AnyRef*) {
		
		if (id == "crafting" && getClass == classOf[BoatRecipeHandler])
			arecipes.addAll(mRecipes)
		else super.loadCraftingRecipes(id, results)
	}
	
	override def loadUsageRecipes(ingredient: ItemStack) {
		
		for (recipe <- mRecipes
				if (Registry.materialItems.get(recipe.material).getItem.isItemEqual(ingredient)
						&& recipe.block == null)
				|| (Registry.blockItems.get(recipe.material).getContent.isItemEqual(ingredient)
						&& recipe.material == null)) {
			arecipes.add(recipe)
		}
	}
	
	/*override def loadUsageRecipes(id: String, results: AnyRef*) {
		if (id == "crafting" && getClass == BoatRecipeHandler.getClass)
			arecipes.addAll(mRecipes)
		else super.loadUsageRecipes(id, results)
	}*/
}

object BoatRecipeHandler {
	
	private var inventory: InventoryCrafting = new InventoryCraftingDummy
	
	class CachedBoatRecipe(val material: String, val block: String)
		extends ShapedRecipeHandler.CachedShapedRecipe(0, 0, null, new ItemStack(Blocks.air)) {
		
		var itemList: List[AnyRef] = new ArrayList[AnyRef]
		
		var materialItems: Array[ItemStack] = null
		if (material != null) materialItems = Array[ItemStack](Registry.findOfType[Material](material).getItem)
		else materialItems = (for {(item, value) <- Registry.materialItems} yield item.stack).toArray
		
		var blockItems: Array[ItemStack] = null
		if (block != null) blockItems = Array[ItemStack](Registry.findOfType[Block](block).getContent)
		else blockItems = (for {(item, value) <- Registry.blockItems} yield item.stack).toArray
		
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
		
		println
		for (i <- 0 until 3; j <- 0 until 3)
		{
			val stack = inventory.getStackInRowAndColumn(i, j)
			if (stack != null) print(stack.getUnlocalizedName + " " + stack.getItemDamage)
			if (j == 2) println
			else print('\t')
		}
		println
		
		result = new PositionedStack(RecipeBoat.getCraftingResult(inventory), 119, 24)
		
		override def setIngredients(width: Int, height: Int, items: Array[AnyRef]) {
			
            for (x <- 0 until width) {
                for (y <- 0 until height) {
                    if (items(y * width + x) != null) {
                    	var stack = new PositionedStack(items(y * width + x), 25 + x * 18, 6 + y * 18)
                    	stack.setMaxSize(1)
                    	ingredients.add(stack)
                    }
                }
            }
        }
	}
}