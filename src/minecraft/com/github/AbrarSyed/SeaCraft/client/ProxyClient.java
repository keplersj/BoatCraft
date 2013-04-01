package com.github.AbrarSyed.SeaCraft.client;

import com.github.AbrarSyed.SeaCraft.ProxyServer;
import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatKayak;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyServer
{
	@Override
	public void registerRenderStuff()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, new RenderBoatFurnace());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatKayak.class, new RenderBoatKayak());
	}
}
