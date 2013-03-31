package com.github.AbrarSyed.SeaCraft.Boats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.SeaCraft;

public class EntityBoatKayak extends EntityBoatBase
{
	private int	ticksForFood	= 0;

	public EntityBoatKayak(World world)
	{
		super(world);
		setSize(2F, 0.6F);
		yOffset = height / 2.0F;
	}

	public EntityBoatKayak(World world, double x, double y, double z)
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

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	@Override
	public double getMountedYOffset()
	{
		return -0.50000001192092896D;
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
	public void onUpdate()
	{
		super.onUpdate();

		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			ticksForFood++;
			if (ticksForFood == 100)
			{
				FoodStats food = ((EntityPlayer) riddenByEntity).getFoodStats();
				food.addExhaustion(1);
			}
		}
	}

	@Override
	public double getCrashSpeed()
	{
		return .5;
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound par1nbtTagCompound)
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1nbtTagCompound)
	{
	}

	@Override
	public float getMaxRotationChange()
	{
		return 10;
	}

}
