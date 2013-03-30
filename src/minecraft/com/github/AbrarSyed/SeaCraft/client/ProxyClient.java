package com.github.AbrarSyed.SeaCraft.client;

import com.github.AbrarSyed.SeaCraft.ProxyServer;
import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatKayak;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyServer
{
	@Override
	public void registerRenderStuff()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatKayak.class, new RenderBoatKayak());
	}
}
