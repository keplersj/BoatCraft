package k2b6s9j.BoatCraft.boats;

import k2b6s9j.BoatCraft.FunctionHelper;
import k2b6s9j.BoatCraft.SeaCraft;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
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
		setSize(3F, 1F);
		yOffset = height / 2.0F;
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
	public boolean playerInteract(EntityPlayer player)
	{
		player.openGui(SeaCraft.instance, 0, worldObj, this.entityId, 0, 0);
		return true;
	}

	@Override
	protected boolean calcPowerredMotion()
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

			motionX = getPoweredSpeed() * headingX;
			motionZ = getPoweredSpeed() * headingZ;
			return true;
		}

		return false;
	}

	@Override
	public void dropItemsOnBreak()
	{
		dropItemWithOffset(Block.furnaceIdle.blockID, 1, 1);

		for (int i = 0; i < stacks.length; i++)
			if (stacks[i] != null)
				entityDropItem(stacks[i], 1);
	}

	@Override
	public void dropItemsOnCrash()
	{
		dropItemWithOffset(Block.furnaceIdle.blockID, 1, 1);

		for (int i = 0; i < stacks.length; i++)
			if (stacks[i] != null)
				entityDropItem(stacks[i], 1);
		
		worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1, true, true);
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
	public double getPoweredSpeed()
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
		return 1;
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
		return FunctionHelper.decrStackSize(slot, amount, stacks, this);
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
		return this.getDistanceToEntity(entityplayer) <= 8;
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

	private static final String	INVENTORY			= "inventory";
	private static final String	SAVE_CURRENT_BURN	= "burn";
	private static final String	SAVE_TOTAL_BURN		= "totalBurn";
	private static final String	SAVE_CURRENT_COOK	= "cook";

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setTag(INVENTORY, FunctionHelper.writeInventoryToNBT(stacks));
		nbt.setInteger(SAVE_CURRENT_BURN, this.getBurningLeft());
		nbt.setInteger(SAVE_TOTAL_BURN, this.getTotalBurnTime());
		nbt.setInteger(SAVE_CURRENT_COOK, this.getItemCookingTime());
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		stacks = FunctionHelper.readInventoryFromNBT(nbt.getTagList(INVENTORY), this.getSizeInventory());
		this.setBurningLeft(nbt.getInteger(SAVE_CURRENT_BURN));
		this.setTotalBurnTime(nbt.getInteger(SAVE_TOTAL_BURN));
		this.setItemCookingTime(nbt.getInteger(SAVE_CURRENT_COOK));
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
}
