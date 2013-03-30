package com.github.AbrarSyed.SeaCraft.Boats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.SeaCraft;
import com.github.AbrarSyed.SeaCraft.api.SeaCraftAPI;

public class EntityBoatFurnace extends EntityBoatBase implements IInventory
{
	public static final String UNLOCALIZED = "SeaCraft.boats.furnace";

	public EntityBoatFurnace(World world)
	{
		super(world);
	}

	public EntityBoatFurnace(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	public void dropItemsOnBreak()
	{
		dropItemWithOffset(SeaCraft.kayak.itemID, 1, 1);
	}

	@Override
	public void dropItemsOnCrash()
	{
		dropItemWithOffset(SeaCraft.kayak.itemID, 1, 1);
	}

	@Override
	public boolean isMountableByPlayer()
	{
		return true;
	}

	@Override
	public double getCurrentWeight()
	{
		return 1;
	}

	@Override
	public int getBaseWeight()
	{
		return 1;
	}

	@Override
	public int getMaxDamage()
	{
		return 40;
	}

	@Override
	public int getRegenRate()
	{
		return 1;
	}

	@Override
	public double getMinSplashSpeed()
	{
		return 1 / 3.0;
	}

	@Override
	public double getMaxSpeed()
	{
		return .35;
	}

	@Override
	public double getCurrentSpeed()
	{
		return .15;
	}

	@Override
	public double getCrashSpeed()
	{
		return .5;
	}
	
	/*
	 * INVENTORY STUFF
	 * ---------------------------------------------------------------------------
	 */
	
	/*
	 * 0 = input item slot.
	 * 1 = output
	 * 2, 3, 4 = fuels
	 */
	private ItemStack[] stacks = new ItemStack[5];
	
	@Override
	public int getSizeInventory()
	{
		return 5;
	}
	
	@Override
	public ItemStack getStackInSlot(int i)
	{
		return stacks[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
        if (this.stacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.stacks[slot].stackSize <= amount)
            {
                itemstack = this.stacks[slot];
                this.stacks[slot] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.stacks[slot].splitStack(amount);

                if (this.stacks[slot].stackSize == 0)
                {
                    this.stacks[slot] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack)
	{
		stacks[i] = stack;
	}

	@Override
	public String getInvName()
	{
		return UNLOCALIZED;
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void onInventoryChanged()
	{
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return this.getDistanceToEntity(entityplayer) < 8;
	}

	@Override
	public void openChest()
	{
	}

	@Override
	public void closeChest()
	{
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack stack)
	{
		return SeaCraftAPI.isFuelType(stack);
	}
	
	/*
	 * SAVING STUFF
	 * ---------------------------------------------------------------------------
	 */
	
	private static final String INVENTORY = "inventory";
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		NBTTagCompound stackNBT;
		NBTTagList list = new NBTTagList();
		for (ItemStack stack : stacks)
		{
			stackNBT = new NBTTagCompound();
			stack.writeToNBT(stackNBT);
			list.appendTag(stackNBT);
		}
		nbt.setTag(INVENTORY, list);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		NBTTagList list = nbt.getTagList(INVENTORY);
		for(int i = 0; i < 5; i++)
			stacks[i] = ItemStack.loadItemStackFromNBT((NBTTagCompound)list.tagAt(i));
	}
}
