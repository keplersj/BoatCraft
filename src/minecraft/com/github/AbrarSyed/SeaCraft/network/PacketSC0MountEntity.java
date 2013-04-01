package com.github.AbrarSyed.SeaCraft.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.boats.EntityBoatBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSC0MountEntity extends PacketSCBoatBase
{
	/**
	 * FROM CLIENT ONLY!
	 */
	public PacketSC0MountEntity(Entity mounted)
	{
		super(mounted.entityId);
	}

	public PacketSC0MountEntity(ObjectInputStream stream) throws IOException
	{
		super(stream);
	}

	@Override
	public void writeToStream(ObjectOutputStream stream) throws IOException
	{
		super.writeToStream(stream);
	}

	@Override
	public int getID()
	{
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayerMP player)
	{
		EntityBoatBase base = this.getBoat(world);
		player.mountEntity(base);
	}

	@Override
	public void actionServer(World world, EntityPlayerMP player)
	{
		EntityBoatBase base = this.getBoat(world);
		player.mountEntity(base);
	}

}
