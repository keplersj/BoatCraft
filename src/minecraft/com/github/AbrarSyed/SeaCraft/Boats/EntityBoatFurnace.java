package com.github.AbrarSyed.SeaCraft.Boats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.SeaCraft;
import com.github.AbrarSyed.SeaCraft.api.SeaCraftAPI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBoatFurnace extends EntityBoatBase implements IInventory
{
	public static final String	UNLOCALIZED	= "SeaCraft.boats.furnace";

	public EntityBoatFurnace(World world)
	{
		super(world);
	}

	public EntityBoatFurnace(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer player)
	{
		//player.openGui(SeaCraft.instance, 0, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
		//return true;

				// already has something riding? DENIED
				if (riddenByEntity != null &&
						// ridden by player.
						riddenByEntity instanceof EntityPlayer &&
						// ridden by player thats not this player
						riddenByEntity != player)
					return true;
				else if (isMountableByPlayer())
				{
					if (!worldObj.isRemote)
					{
						player.mountEntity(this);
					}
		
					return true;
				}
				
				return true;
	}

	protected void calcRidingMotion()
	{
		//if (burningLeft > 0)
			super.calcRidingMotion();
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
	private ItemStack[]	stacks	= new ItemStack[5];

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
		// input smelting
		if (i == 0)
			return true;

		// the output
		else if (i == 1)
			return false;

		// fuel
		else
			return TileEntityFurnace.isItemFuel(stack);
	}

	/*
	 * FURNACE STUFF
	 * ---------------------------------------------------------------------------
	 */

	private int					burningLeft		= 0;
	private int					itemBurnTime	= 0;
	private static final int	ITEM_BURN_TIME	= 200;

	public int getBurningLeft()
	{
		return burningLeft;
	}

	public int getItemBurnTime()
	{
		return itemBurnTime;
	}

	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int num)
	{
		return num * (this.burningLeft / ITEM_BURN_TIME);
	}

	/**
	 * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
	 * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
	 */
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int num)
	{
		if (this.itemBurnTime == 0)
		{
			this.itemBurnTime = 200;
		}

		return num * (this.burningLeft / this.itemBurnTime);
	}

	private void updateFurnace()
	{
		if (burningLeft > 0)
		{
			burningLeft--;
			return;
		}

		ItemStack burning = null;
		for (int i = 4; i > 1 && burning == null; i--)
			burning = stacks[i];

		if (burning != null)
		{
			burning.stackSize--;
			burningLeft = TileEntityFurnace.getItemBurnTime(burning);
		}

	}

	/*
	 * SAVING STUFF
	 * ---------------------------------------------------------------------------
	 */

	private static final String	INVENTORY	= "inventory";
	private static final String	SLOT		= "slot";

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		NBTTagCompound stackNBT;
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < 5; i++)
		{
			if (stacks[i] == null)
				continue;

			stackNBT = new NBTTagCompound();
			stackNBT.setInteger(SLOT, i);
			stacks[i].writeToNBT(stackNBT);
			list.appendTag(stackNBT);
		}
		nbt.setTag(INVENTORY, list);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		NBTTagList list = nbt.getTagList(INVENTORY);
		NBTTagCompound compound;
		int index;
		for (int i = 0; i < list.tagCount(); i++)
		{
			compound = (NBTTagCompound) list.tagAt(i);
			index = compound.getInteger(SLOT);
			stacks[index] = ItemStack.loadItemStackFromNBT(compound);
		}
	}
}
