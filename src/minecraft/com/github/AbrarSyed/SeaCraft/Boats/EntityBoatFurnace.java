package com.github.AbrarSyed.SeaCraft.Boats;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.SeaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBoatFurnace extends EntityBoatBase implements IInventory
{
	public static final String	UNLOCALIZED			= "SeaCraft.boats.furnace";

	public boolean				canMove				= false;

	private static final int	COOK_WATCHER		= 20;
	private static final int	BURN_WATCHER		= 21;
	private static final int	BURN_TOTAL_WATCHER	= 22;

	public EntityBoatFurnace(World world)
	{
		super(world);
	}

	public EntityBoatFurnace(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(COOK_WATCHER, new Integer(0));
		dataWatcher.addObject(BURN_WATCHER, new Integer(0));
		dataWatcher.addObject(BURN_TOTAL_WATCHER, new Integer(0));
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer player)
	{
		player.openGui(SeaCraft.instance, 0, worldObj, this.entityId, 0, 0);
		return true;

		//		// already has something riding? DENIED
		//		if (riddenByEntity != null &&
		//				// ridden by player.
		//				riddenByEntity instanceof EntityPlayer &&
		//				// ridden by player thats not this player
		//				riddenByEntity != player)
		//			return true;
		//		else if (isMountableByPlayer())
		//		{
		//			if (!worldObj.isRemote)
		//			{
		//				player.mountEntity(this);
		//			}
		//
		//			return true;
		//		}
		//		
		//		return true;
	}

	@Override
	protected void calcMotion(double waterFloor)
	{
		double headingX, headingZ;
		// powerred movement.
		if (canMove && this.isBurningFuel())
		{

			// set ridding controls.
			if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
			{
				//this.rotationYaw = this.riddenByEntity.rotationYaw;
				EntityPlayer rider = (EntityPlayer) riddenByEntity;

				headingX = rider.getLookVec().xCoord; // in radians
				headingZ = rider.getLookVec().zCoord; // in radians

				{
					// new value
					float lookingAngle = (float) (270f - Math.atan2(headingX, headingZ) * 180.0D / Math.PI);
					float changed = (float) MathHelper.wrapAngleTo180_double(lookingAngle - rotationYaw);

					// get value changed
					//float changed = lookingAngle - rotationYaw;

					if (changed > getMaxRotationChange())
						changed = getMaxRotationChange();
					else if (changed < -getMaxRotationChange())
						changed = -getMaxRotationChange();

					lookingAngle = this.rotationYaw + changed;

					rotationYaw = lookingAngle;
					this.riddenByEntity.prevRotationYaw = this.riddenByEntity.rotationYaw -= changed;
					this.setRotation(lookingAngle, rotationPitch);
					this.setRotation(lookingAngle);

				}
			}
			
			double rotation = Math.toRadians(rotationYaw);

			headingX = -Math.cos(rotation);
			headingZ = -Math.sin(rotation);
			
			motionX = getCurrentSpeed() * headingX;
			motionZ = getCurrentSpeed() * headingZ;
		}
		
		// verify gravity.
		if (waterFloor < 1.0D)
		{
			double num = waterFloor * 2.0D - 1.0D;
			motionY += 0.03999999910593033D * num;
		}
		else
		{
			if (motionY < 0.0D)
			{
				motionY /= 2.0D;
			}

			motionY += 0.007000000216066837D;
		}

		// regardless of powerred or not
		if (onGround)
		{
			setGroundDrag();
		}

		moveEntity(motionX, motionY, motionZ);
	}

	@Override
	public void dropItemsOnBreak()
	{
		dropItemWithOffset(SeaCraft.furnace.itemID, 1, 1);

		for (int i = 0; i < stacks.length; i++)
			if (stacks[i] != null)
				entityDropItem(stacks[i], 1);
	}

	@Override
	public void dropItemsOnCrash()
	{
		dropItemWithOffset(SeaCraft.furnace.itemID, 1, 1);

		for (int i = 0; i < stacks.length; i++)
			if (stacks[i] != null)
				entityDropItem(stacks[i], 1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.updateFurnace();
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

	@Override
	public float getMaxRotationChange()
	{
		return 5;
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
	private ItemStack[]			stacks		= new ItemStack[5];
	private static final int	INPUT_SLOT	= 0;
	private static final int	OUTPUT_SLOT	= 1;
	private static final int[]	FUEL_SLOTS	= new int[] { 4, 3, 2 };

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
	private static final int	ITEM_BURN_TIME	= 200;

	public final void setItemCookingTime(int ammount)
	{
		dataWatcher.updateObject(COOK_WATCHER, Integer.valueOf(ammount));
	}

	public final int getItemCookingTime()
	{
		return dataWatcher.getWatchableObjectInt(COOK_WATCHER);
	}

	public final void setBurningLeft(int ammount)
	{
		dataWatcher.updateObject(BURN_WATCHER, Integer.valueOf(ammount));
	}

	public final int getBurningLeft()
	{
		return dataWatcher.getWatchableObjectInt(BURN_WATCHER);
	}

	public final void setTotalBurnTime(int ammount)
	{
		dataWatcher.updateObject(BURN_TOTAL_WATCHER, Integer.valueOf(ammount));
	}

	public final int getTotalBurnTime()
	{
		return dataWatcher.getWatchableObjectInt(BURN_TOTAL_WATCHER);
	}

	/**
	 * Returns an integer between 0 and the passed value representing how close the current item is to being completely
	 * cooked
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int num)
	{
		double cookTime = getItemCookingTime();
		double burn = cookTime / ITEM_BURN_TIME;
		return (int) (num * burn);
	}

	/**
	 * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
	 * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
	 */
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int num)
	{
		return (int) (num * ((double) this.getBurningLeft() / this.getTotalBurnTime()));
	}

	private void updateFurnace()
	{
		// update burning status.
		{
			int burning = this.getBurningLeft();
			if (burning > 0)
				burning--;

			this.setBurningLeft(burning);
		}

		// SERVER ONLY
		if (!this.worldObj.isRemote)
		{
			int burning = this.getBurningLeft();
			// CHECK FUEL AND CONSUME FUEL
			// isBurning && canSmelt
			if (burning == 0 && (this.canSmelt() || this.canMove))
			{
				int index = getFuelIndex();
				if (index != -1)
				{
					burning = TileEntityFurnace.getItemBurnTime(this.stacks[index]);
					this.setBurningLeft(burning);
					this.setTotalBurnTime(burning);

					if (burning > 0)
					{
						if (this.stacks[index] != null)
						{
							--this.stacks[index].stackSize;

							if (this.stacks[index].stackSize == 0)
							{
								this.stacks[index] = this.stacks[index].getItem().getContainerItemStack(stacks[index]);
							}
						}
					}
				}
			}

			// items creattion and smelting
			if (this.isBurningFuel() && this.canSmelt())
			{
				int cookTime = this.getItemCookingTime() + 1;

				if (cookTime == ITEM_BURN_TIME)
				{
					cookTime = 0;
					this.smeltItem();
				}

				this.setItemCookingTime(cookTime);
			}
			else
			{
				this.setItemCookingTime(0);
			}
		}
	}

	/**
	 * -1 if no fuel.
	 */
	private int getFuelIndex()
	{
		for (int i : FUEL_SLOTS)
		{
			if (stacks[i] != null)
				return i;
		}
		return -1;
	}

	/**
	 * returns TRUE if an item can be smelted.
	 */
	private boolean canSmelt()
	{
		if (this.stacks[INPUT_SLOT] == null)
		{
			return false;
		}
		else
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.stacks[INPUT_SLOT]);
			// no output;
			if (itemstack == null)
				return false;

			// empty output
			if (stacks[OUTPUT_SLOT] == null)
				return true;

			// not the same outputs
			if (!this.stacks[OUTPUT_SLOT].isItemEqual(itemstack))
				return false;

			// calculate final 
			int result = stacks[OUTPUT_SLOT].stackSize + itemstack.stackSize;

			return result <= itemstack.getMaxStackSize();
		}
	}

	/**
	 * Smelts an input into an output
	 */
	public void smeltItem()
	{
		if (this.canSmelt())
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.stacks[0]);

			if (this.stacks[OUTPUT_SLOT] == null)
			{
				this.stacks[OUTPUT_SLOT] = itemstack.copy();
			}
			else if (this.stacks[OUTPUT_SLOT].isItemEqual(itemstack))
			{
				stacks[OUTPUT_SLOT].stackSize += itemstack.stackSize;
			}

			--this.stacks[INPUT_SLOT].stackSize;

			if (this.stacks[INPUT_SLOT].stackSize <= 0)
			{
				this.stacks[INPUT_SLOT] = null;
			}
		}
	}

	public boolean isBurningFuel()
	{
		return this.getBurningLeft() > 0;
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
