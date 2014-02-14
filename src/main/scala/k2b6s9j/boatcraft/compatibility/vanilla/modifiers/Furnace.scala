package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.api.Boat.{EntityCustomBoat, EntityBoatContainer}
import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntityFurnace
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList

class Furnace extends Modifier
{
	override def getBlock = Blocks.lit_furnace
	override def getMeta = 0
	
	override def getName = "Furnace"
	
	override def getContent = new ItemStack(Blocks.furnace)
	
	override def hasInventory = true
	override def getInventory(boat: EntityBoatContainer): IInventory =
		new Furnace.Inventory(boat)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
		player func_146101_a(boat.asInstanceOf[EntityBoatContainer]
							.getInventory.asInstanceOf[Furnace.Inventory])
	
	override def update(boat: EntityCustomBoat) =
		(boat.asInstanceOf[EntityBoatContainer].getInventory.asInstanceOf[Furnace.Inventory]
			updateEntity)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
	{
		var inventory = boat.asInstanceOf[EntityBoatContainer]
						.getInventory.asInstanceOf[Furnace.Inventory]
		
        val nbttaglist = tag getTagList("Items", 10)

        for (i <- 0 until nbttaglist.tagCount)
        {
            val _tag = nbttaglist getCompoundTagAt i
            val b0 = _tag getByte "Slot"

            if (b0 >= 0 && b0 < inventory.getSizeInventory)
            {
                inventory setInventorySlotContents(b0,
                		ItemStack loadItemStackFromNBT _tag)
            }
        }
		
        inventory.furnaceBurnTime = tag.getShort("BurnTime");
        inventory.furnaceCookTime = tag.getShort("CookTime");
        inventory.currentItemBurnTime = TileEntityFurnace getItemBurnTime
        		(inventory getStackInSlot 1)
        
        if (tag hasKey("CustomName", 8))
        {
            //inventory.field_145958_o = p_145839_1_.getString("CustomName");
        }
	}
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
	{
		var inventory = boat.asInstanceOf[EntityBoatContainer]
						.getInventory.asInstanceOf[Furnace.Inventory]
		
		tag setShort("BurnTime", inventory.furnaceBurnTime toShort)
		tag setShort("CookTime", inventory.furnaceCookTime toShort)
		val list = new NBTTagList

		for (i <- 0 until inventory.getSizeInventory)
		{
			if ((inventory getStackInSlot i) != null)
			{
				var _tag = new NBTTagCompound
				_tag setByte("Slot", i toByte)
				(inventory getStackInSlot i) writeToNBT _tag
				list appendTag _tag
			}
		}

		tag setTag("Items", list);

		if (inventory hasCustomInventoryName)
		{
			//tag setString("CustomName", inventory.field_145958_o);
		}
	}
}

object Furnace
{
	private[Furnace] class Inventory(boat: EntityBoatContainer) extends TileEntityFurnace
	{
		worldObj = boat.worldObj
		
		override def getInventoryName: String = "Furnace Boat"
		
		override def isUseableByPlayer(player: EntityPlayer): Boolean =
			(player getDistanceSqToEntity boat) <= 64
		
		override def updateEntity
		{
			var flag = furnaceBurnTime > 0

			if (furnaceBurnTime > 0)
				furnaceBurnTime = furnaceBurnTime - 1

			if (!worldObj.isRemote)
			{
				if (furnaceBurnTime == 0 && canSmelt)
				{
					currentItemBurnTime = TileEntityFurnace getItemBurnTime getStackInSlot(1)
					furnaceBurnTime = TileEntityFurnace getItemBurnTime getStackInSlot(1)

					if (furnaceBurnTime > 0)
					{
						if (getStackInSlot(1) != null)
						{
							getStackInSlot(1).stackSize = getStackInSlot(1).stackSize - 1

							if (getStackInSlot(1).stackSize == 0)
							{
								setInventorySlotContents(1,
									getStackInSlot(1).getItem getContainerItem getStackInSlot(1))
							}
						}
					}
				}

				if (isBurning && canSmelt)
				{
					furnaceCookTime = furnaceCookTime + 1

					if (furnaceCookTime == 200)
					{
						furnaceCookTime = 0
						smeltItem
					}
				}
				else
					furnaceCookTime = 0
			}
		}
		
		private def canSmelt: Boolean =
		{
			if (getStackInSlot(0) == null)
				false
			else
			{
				val itemstack = FurnaceRecipes.smelting.getSmeltingResult(getStackInSlot(0))
				if (itemstack == null) return false
				if (getStackInSlot(2) == null) return true
				if (!getStackInSlot(2).isItemEqual(itemstack)) return false
				val result = getStackInSlot(2).stackSize + itemstack.stackSize
				result <= getInventoryStackLimit &&
					result <= getStackInSlot(2).getMaxStackSize
			}
		}
	}
	/*
	object Inventory
	{
		private final val slotsTop = Array(0)
		private final val slotsBottom = Array(2, 1)
		private final val slotsSides = Array(1)
	}
	
	private[vanilla] class Container(inventoryPlayer: InventoryPlayer,
			boat: EntityBoatContainer)
		extends ContainerFurnace(inventoryPlayer, null)
	{
		inventorySlots.clear
		
		addSlotToContainer(new Slot(boat, 0, 56, 17))
		addSlotToContainer(new Slot(boat, 1, 56, 53))
		addSlotToContainer(new SlotFurnace(inventoryPlayer.player, boat, 2, 116, 35))
		
		for (i <- 0 until 3) for (j <- 0 until 9)
			addSlotToContainer(new Slot(inventoryPlayer,
				j + i * 9 + 9, 8 + j * 18, 84 + i * 18))
		
		for (i <- 0 until 9)
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142))
	}
	
	private[vanilla] class Gui(inventoryPlayer: InventoryPlayer,
			boat: EntityBoatContainer) extends GuiFurnace(inventoryPlayer, null)
	{
		protected override def drawGuiContainerForegroundLayer(arg1: Int, arg2: Int)
		{
			val s = /*boat.hasCustomInventoryName() ? boat.getInventoryName() : */
				I18n format(boat getInventoryName, Array())
			fontRendererObj drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752)
			fontRendererObj drawString(I18n format("container.inventory", Array()), 8, ySize - 96 + 2, 4210752)
		}
		
		protected override def drawGuiContainerBackgroundLayer(p_146976_1_ : Float, p_146976_2_ : Int, p_146976_3_ : Int)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F)
			mc getTextureManager() bindTexture furnaceGuiTextures
			int k = (width - xSize) / 2
			int l = (height - ySize) / 2
			drawTexturedModalRect(k, l, 0, 0, xSize, ySize)
			int i1
			
			if (boat.getInventory.asInstanceOf[Inventory].isBurning)
			{
				i1 = tileFurnace.getBurnTimeRemainingScaled(12)
				drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2)
			}
		
			i1 = tileFurnace.getCookProgressScaled(24)
			drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16)
		}
	}
	
	private final val furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png")*/
}