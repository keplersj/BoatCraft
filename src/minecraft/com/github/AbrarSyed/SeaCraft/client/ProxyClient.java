package com.github.AbrarSyed.SeaCraft.client;

import com.github.AbrarSyed.SeaCraft.EntityBoatKayak;
import com.github.AbrarSyed.SeaCraft.ProxyServer;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyServer
{
	@Override
	public void registerRenderStuff()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatKayak.class, new RenderBoatKayak());
	}
}
