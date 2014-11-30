package boatcraft.core

import boatcraft.api.Registry
import boatcraft.api.getCustomBoat
import boatcraft.api.modifiers.Block
import boatcraft.api.modifiers.Material
import net.minecraft.inventory.InventoryCrafting
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.IRecipe
import net.minecraft.world.World
import net.minecraftforge.oredict.RecipeSorter

object RecipeBoat extends IRecipe
{
	RecipeSorter.register("boatcraft:boat", getClass, RecipeSorter.Category.SHAPED,
							"after:minecraft:shaped before:forge:shapedore")
	
	var cacheResult: ItemStack = null
	
	override def matches(inventory: InventoryCrafting, world: World) = getCraftingResult(inventory) != null
	
	private def check(inventory: InventoryCrafting, startI: Int, startJ: Int): (Boolean, Material, Block) =
	{
		val fail = (false, null, null)
		
		val start = inventory getStackInRowAndColumn(startJ, startI)
		var current: ItemStack = null
		for (i <- startI until startI + 2) for (j <- startJ until startJ + 3)
		{
			current = inventory getStackInRowAndColumn(j, i)
			
			if ((i != startI || j != startJ + 1) && current == null) {
//				println("\tnull at " + i + ", " + j)
				return fail
			}
			
			if ((i != startI || j != startJ + 1) && !current.isItemEqual(start)) {
//				println("\tNot the same material at " + i + ", " + j)
				return fail
			}
		}
		
		val material = Registry.getMaterial(start)
		if (material == null) {
//			println("\tIncorrect material")
//			println("\t\t" + Registry.materialItems)
//			println("\t\t" + start)
			return fail
		}
		
		val block = Registry.getBlock(inventory getStackInRowAndColumn(startJ + 1, startI))
		if (block == null) {
//			println("\tIncorrect block")
			return fail
		}
		
		return (true, material, block)
	}
	
	override def getCraftingResult(inventory: InventoryCrafting): ItemStack =
	{
		if (inventory == null) return cacheResult
		
		cacheResult = null
		
		for (startI <- 0 until inventory.getSizeInventory / 3) for (startJ <- 0 until inventory.getSizeInventory / 2)
		{
//			println("Starting at " + startI + ", " + startJ)
			val result = check(inventory, startI, startJ)
			if (result._1)
			{
//				println("Found " + result._2 + ", " + result._3)
				cacheResult = getCustomBoat(result._2, result._3)
				return cacheResult
			}
		}
		
		return null
	}
	
	override def getRecipeSize = 6
	
	override def getRecipeOutput = cacheResult
	
	override def func_179532_b(inventory: InventoryCrafting): Array[ItemStack] = 
    {
        val aitemstack = new Array[ItemStack](inventory.getSizeInventory);

        for (i <- 0 until aitemstack.length)
        {
            val itemstack = inventory.getStackInSlot(i);
            aitemstack(i) = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
        }

        return aitemstack;
    }
}