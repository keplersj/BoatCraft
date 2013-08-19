package com.github.AbrarSyed.SeaCraft.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.boats.EntityBoatBase;

public abstract class PacketSCBoatBase extends PacketSCBase
{
	private final int	entityID;

	public PacketSCBoatBase(int entityID)
	{
		this.entityID = entityID;
	}
	
	public PacketSCBoatBase(ObjectInputStream stream) throws IOException
	{
		super(stream);
		this.entityID = stream.readInt();
	}

	@Override
	public void writeToStream(ObjectOutputStream stream) throws IOException
	{
		stream.writeInt(entityID);
	}

	protected EntityBoatBase getBoat(World world)
	{
		return (EntityBoatBase) world.getEntityByID(entityID);
	}

}
