package com.github.AbrarSyed.SeaCraft.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSC1StartBoat extends PacketSCBase
{
	private final int		entityID;
	private final boolean	start;
	
	public PacketSC1StartBoat(EntityBoatFurnace boat, boolean start)
	{
		super();
		entityID = boat.entityId;
		this.start = start;
	}

	public PacketSC1StartBoat(ObjectInputStream stream) throws IOException
	{
		super(stream);
		entityID = stream.readInt();
		start = stream.readBoolean();
		System.out.println(" IN >> "+start);
	}

	@Override
	public void writeToStream(ObjectOutputStream stream) throws IOException
	{
		stream.writeInt(entityID);
		stream.writeBoolean(start);
		System.out.println(" OUT >> "+start);
	}

	@Override
	public int getID()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayerMP player)
	{
		EntityBoatFurnace boat = (EntityBoatFurnace) world.getEntityByID(entityID);
		boat.canMove = start;
	}

	@Override
	public void actionServer(World world, EntityPlayerMP player)
	{
		EntityBoatFurnace boat = (EntityBoatFurnace) world.getEntityByID(entityID);
		boat.canMove = start;
	}

}
