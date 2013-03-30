package com.github.AbrarSyed.SeaCraft.Boats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import com.github.AbrarSyed.SeaCraft.SeaCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBoatKayak extends EntityBoatBase
{

	public EntityBoatKayak(World world)
	{
		super(world);
	}
	
	public EntityBoatKayak(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	public void dropItemsOnBreak()
	{
		this.dropItemWithOffset(SeaCraft.kayak.itemID, 1, 1);
	}

	@Override
	public void dropItemsOnCrash()
	{
		this.dropItemWithOffset(SeaCraft.kayak.itemID, 1, 1);
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
		return 1/3.0;
	}

	@Override
	public double getMaxSpeed()
	{
		return .35;
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound par1nbtTagCompound)
	{
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound par1nbtTagCompound)
	{
	}
}
