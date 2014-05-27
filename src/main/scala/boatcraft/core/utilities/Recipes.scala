package boatcraft.core.utilities

import scala.collection.JavaConversions.{asScalaBuffer, mapAsScalaMap}
import cpw.mods.fml.common.registry.GameRegistry
import boatcraft.api.Registry
import boatcraft.core.BoatCraft
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.{CraftingManager, IRecipe}
import net.minecraft.nbt.NBTTagCompound
import boatcraft.api.boat.ItemCustomBoat
import java.util

object Recipes {
	
	def addBoatRecipes {
		val stack = new ItemStack(ItemCustomBoat)
		stack.stackTagCompound = new NBTTagCompound
		for ((nameMat, material) <- Registry.materials) {
			stack.stackTagCompound.setString("material", nameMat)
			for ((blockName, block) <- Registry.blocks) {
				stack.stackTagCompound.setString("block", blockName)
				if (block.getContent != null)
					GameRegistry addRecipe(stack.copy,
						"MmM",
						"MMM",
						'M': Character, material.getItem,
						'm': Character, block.getContent)
				else GameRegistry addRecipe(stack.copy,
					"M M",
					"MMM",
					'M': Character, material.getItem)
			}
		}
	}
}
