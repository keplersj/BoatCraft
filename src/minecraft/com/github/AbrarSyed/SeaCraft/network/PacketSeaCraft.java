package com.github.AbrarSyed.SeaCraft.network;

import java.io.InputStream;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.network.packet.Packet250CustomPayload;

public abstract class PacketSeaCraft
{
	public static final String CHANNEL = "SeaCraft";
	
	/**
	 * For reading packets off of the stream.
	 * Another constructor is ually good as well.
	 * @param stream
	 */
	public PacketSeaCraft(InputStream stream)
	{
		// to be filled by something else...
	}
	
	public abstract Packet250CustomPayload getPacket250();
	
	public abstract int getID();
	
	@SideOnly(Side.CLIENT)
	public abstract void actionClient();
	
	public abstract void actionServer();
}
