package com.github.AbrarSyed.SeaCraft.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerBase implements IPacketHandler
{
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		// no packet stuff
		if (!packet.channel.equals(PacketSeaCraft.CHANNEL))
			return;
	}
	

}
