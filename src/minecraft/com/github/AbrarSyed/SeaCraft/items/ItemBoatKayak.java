package com.github.AbrarSyed.SeaCraft.items;

import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatBase;
import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatKayak;

public class ItemBoatKayak extends ItemBoatBase
{
	public ItemBoatKayak(int par1)
	{
		super(par1);
	}

	@Override
	protected String getNameForTexture()
	{
		return "kayak";
	}

	@Override
	protected EntityBoatBase getBoat(World world, float x, float y, float z)
	{
		return new EntityBoatKayak(world, x, y, z);
	}
}
