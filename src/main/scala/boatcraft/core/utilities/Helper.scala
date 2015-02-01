package boatcraft.core.utilities

import java.util.ArrayList

import boatcraft.core.BoatCraft

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
	
	object AABB {
		def isAABBInFluid(world: World, aabb: AxisAlignedBB): Boolean = {
			val i = MathHelper.floor_double(aabb.minX)
			val j = MathHelper.floor_double(aabb.maxX + 1)
			val k = MathHelper.floor_double(aabb.minY)
			val l = MathHelper.floor_double(aabb.maxY + 1)
			val i1 = MathHelper.floor_double(aabb.minZ)
			val j1 = MathHelper.floor_double(aabb.maxZ + 1)
			
			for (k1 <- i until j)
			{
				for (l1 <- k until l)
				{
					for (i2 <- i1 until j1)
					{
						val block = world.getBlock(k1, l1, i2)
	
						if (block.getMaterial isLiquid)
						{
							val j2 = world.getBlockMetadata(k1, l1, i2)
							var d0 = (l1 + 1).toDouble
	
							if (j2 < 8)
							{
								d0 = l1 + 1.0 - j2.toDouble / 8.0D
							}
	
							if (d0 >= aabb.minY)
							{
								return true
							}
						}
					}
				}
			}
			
			return false
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
}