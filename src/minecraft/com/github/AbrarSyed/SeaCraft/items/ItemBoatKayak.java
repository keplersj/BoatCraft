package com.github.AbrarSyed.SeaCraft.items;

import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.boats.EntityBoatBase;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatKayak;

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
