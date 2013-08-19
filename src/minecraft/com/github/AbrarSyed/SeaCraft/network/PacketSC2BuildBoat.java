package com.github.AbrarSyed.SeaCraft.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.github.AbrarSyed.SeaCraft.blocks.TileEntityBoatBuilder;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketSC2BuildBoat extends PacketSCBase
{
	public final int x, y, z;

	public PacketSC2BuildBoat(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public PacketSC2BuildBoat(ObjectInputStream stream) throws IOException
	{
		this.x = stream.readInt();
		this.y = stream.readInt();
		this.z = stream.readInt();
	}

	@Override
	public void writeToStream(ObjectOutputStream stream) throws IOException
	{
		stream.writeInt(x);
		stream.writeInt(y);
		stream.writeInt(z);
	}

	@Override
	public int getID()
	{
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionClient(World world, EntityPlayerMP player)
	{
		TileEntityBoatBuilder builder = (TileEntityBoatBuilder) world.getBlockTileEntity(x, y, z);
		builder.build();
	}

	@Override
	public void actionServer(World world, EntityPlayerMP player)
	{
		TileEntityBoatBuilder builder = (TileEntityBoatBuilder) world.getBlockTileEntity(x, y, z);
		builder.build();
	}

}
