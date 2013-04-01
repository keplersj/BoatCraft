package com.github.AbrarSyed.SeaCraft.items;

import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatBase;
import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatFurnace;

public class ItemBoatFurnace extends ItemBoatBase
{
	public ItemBoatFurnace(int par1)
	{
		super(par1);
	}

	@Override
	protected String getNameForTexture()
	{
		return "furnace";
	}

	@Override
	protected EntityBoatBase getBoat(World world, float x, float y, float z)
	{
		return new EntityBoatFurnace(world, x, y, z);
	}
}
