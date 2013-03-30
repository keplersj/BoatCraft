package com.github.AbrarSyed.SeaCraft.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSC0MountEntity extends PacketSCBase
{
	public final int		entityID;
	public final boolean	mount;

	/**
	 * FROM CLIENT ONLY!
	 * @param mounted
	 */
	public PacketSC0MountEntity(Entity mounted, boolean mount)
	{
		super();
		entityID = mounted.entityId;
		this.mount = mount;
	}

	public PacketSC0MountEntity(ObjectInputStream stream) throws IOException
	{
		super(stream);
		entityID = stream.readInt();
		mount = stream.readBoolean();
		System.out.println("IN >> "+entityID);
	}

	@Override
	public void writeToStream(ObjectOutputStream stream) throws IOException
	{
		stream.writeInt(entityID);
		stream.writeBoolean(mount);
		System.out.println("OUT >> "+entityID);
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
		// should never happen....
	}

	@Override
	public void actionServer(World world, EntityPlayerMP player)
	{
		Entity e = world.getEntityByID(entityID);
		if (mount)
			player.mountEntity(e);
		else
			player.unmountEntity(e);
	}

}
