package com.github.AbrarSyed.SeaCraft.boats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.FunctionHelper;
import com.github.AbrarSyed.SeaCraft.SeaCraft;

public class EntityBoatChest extends EntityBoatBase implements IInventory
{
	private static final String	UNLOCALIZED	= "SeaCraft.boats.chest";
	private static final String	INVENTORY	= "inventory";

	public EntityBoatChest(World par1World)
	{
		super(par1World);
		setSize(1.5F, 1F);
		yOffset = height / 2.0F;
	}
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean playerInteract(EntityPlayer player)
	{
		player.openGui(SeaCraft.instance, 2, worldObj, this.entityId, 0, 0);
		return true;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setTag(INVENTORY, FunctionHelper.writeInventoryToNBT(stacks));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		stacks = FunctionHelper.readInventoryFromNBT(nbt.getTagList(INVENTORY), 27);
	}

	@Override
	public void dropItemsOnBreak()
	{
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i] == null)
				continue;

			this.entityDropItem(stacks[i], 1);
		}
	}

	@Override
	public void dropItemsOnCrash()
	{
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i] == null)
				continue;

			this.entityDropItem(stacks[i], 1);
		}
	}

	@Override
	public boolean isMountableByPlayer()
	{
		return false;
	}

	@Override
	public double getCurrentWeight()
	{
		int num = 0;
		for (int i = 0; i < stacks.length; i++)
		{
			if (stacks[i] == null)
				continue;

			num += stacks[i].stackSize;
		}

		return this.getBaseWeight() + num;
	}

	@Override
	public int getBaseWeight()
	{
		return 1;
	}

	@Override
	public int getMaxDamage()
	{
		return 100;
	}

	@Override
	public int getRegenRate()
	{
		return 2;
	}

	@Override
	public double getMinSplashSpeed()
	{
		return 1 / 3.0;
	}

	@Override
	public double getMaxSpeed()
	{
		return 1;
	}

	@Override
	public double getPoweredSpeed()
	{
		return 0;
	}

	@Override
	public double getCrashSpeed()
	{
		return .5;
	}

	@Override
	public float getMaxRotationChange()
	{
		return 1;
	}

	/*
	 * INVENTORY STUFF
	 * ---------------------------------------------------------------------------
	 */

	private ItemStack[]	stacks	= new ItemStack[27];

	@Override
	public int getSizeInventory()
	{
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return stacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		return FunctionHelper.decrStackSize(i, j, stacks, this);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		stacks[i] = itemstack;
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
		// useless
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return entityplayer.getDistanceToEntity(this) <= 8;
	}

	@Override
	public void openChest()
	{
		// useless
	}

	@Override
	public void closeChest()
	{
		// useless
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}

}
