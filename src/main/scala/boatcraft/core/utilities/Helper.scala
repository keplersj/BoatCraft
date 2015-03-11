package boatcraft.core.utilities

import java.util.ArrayList
import scala.collection.JavaConversions.asScalaBuffer
import boatcraft.core.BoatCraft
import cpw.mods.fml.common.Mod
import net.minecraft.init.Items
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.CraftingManager
import net.minecraft.item.crafting.IRecipe
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.MathHelper
import net.minecraft.world.World
import net.minecraftforge.common.util.Constants
import io.netty.buffer.ByteBuf
import net.minecraft.item.Item

object Helper {
	
	object NBT {
		def writeChestToNBT(inv: IInventory, tag: NBTTagCompound) {
			val list = new NBTTagList
			for (i <- 0 until inv.getSizeInventory) {
				if (inv.getStackInSlot(i) != null) {
					val _tag = new NBTTagCompound
					(inv getStackInSlot i) writeToNBT _tag
					_tag setByte("Slot", i.toByte)
					list appendTag _tag
				}
			}
			tag.setTag("Items", list)
		}
	
		def readChestFromNBT(inv: IInventory, tag: NBTTagCompound) {
			val list = tag getTagList("Items", Constants.NBT.TAG_COMPOUND)
	
			for (i <- 0 until list.tagCount) {
				val _tag = list getCompoundTagAt i
				inv setInventorySlotContents(_tag getByte "Slot", ItemStack loadItemStackFromNBT _tag)
			}
		}
	}
	
	object Packet {
		
		var nextID = 0
		
		def getNextID: Int =
		{
			nextID += 1
			return nextID
		}
	}
	
	object Recipe {
		def removeRecipe(resultItem: ItemStack) {
			val toRemove = new ArrayList[IRecipe]
			
			for (recipe <- CraftingManager.getInstance.getRecipeList) {
				recipe match {
					case recipe: IRecipe if (recipe.getRecipeOutput != null) && (recipe.getRecipeOutput.getItem == Items.boat) =>
						toRemove add recipe
						BoatCraft.log info "Removed vanilla Boat recipe: " + recipe
					case _ =>
				}
			}
			
			CraftingManager.getInstance.getRecipeList removeAll toRemove
		}
	}
	
	object Netty {
		
		def writeItemStack(buffer: ByteBuf, stack: ItemStack) {
			
			buffer.writeInt(Item.getIdFromItem(stack.getItem))
					.writeShort(stack.stackSize)
					.writeInt(stack.getItemDamage)
		}
		
		def readItemStack(buffer: ByteBuf): ItemStack = {
			
			var stack = new ItemStack(Item.getItemById(buffer.readInt()))
			
			stack.stackSize = buffer.readShort()
			stack.setItemDamage(buffer.readInt())
			
			return stack
		}
	}
}