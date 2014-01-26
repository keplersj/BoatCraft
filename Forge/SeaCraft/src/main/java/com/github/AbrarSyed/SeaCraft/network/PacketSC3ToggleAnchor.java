package com.github.AbrarSyed.SeaCraft.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.github.AbrarSyed.SeaCraft.boats.EntityBoatBase;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatFurnace;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSC3ToggleAnchor extends PacketSCBase
{
	public PacketSC3ToggleAnchor()
	{
		super();
	}

	public PacketSC3ToggleAnchor(ObjectInputStream stream) throws IOException
	{
		super(stream);
	}

	@Override
	public int getID()
	{
		return 3;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayerMP player)
	{
		Entity e = player.ridingEntity;
		if (e instanceof EntityBoatBase)
		{
			((EntityBoatBase) e).toggleAnchor();
		}
	}

	@Override
	public void actionServer(World world, EntityPlayerMP player)
	{
		Entity e = player.ridingEntity;
		if (e instanceof EntityBoatBase)
		{
			((EntityBoatBase) e).toggleAnchor();
		}
	}

	@Override
	public void writeToStream(ObjectOutputStream stream) throws IOException
	{
		// nothin
	}

}
