package k2b6s9j.BoatCraft.client;

import k2b6s9j.BoatCraft.ProxyServer;
import k2b6s9j.BoatCraft.boats.EntityBoatChest;
import k2b6s9j.BoatCraft.boats.EntityBoatFurnace;
import k2b6s9j.BoatCraft.boats.EntityBoatKayak;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ProxyClient extends ProxyServer
{
	@Override
	public void registerRenderStuff()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatFurnace.class, new RenderBoatFurnace());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatKayak.class, new RenderBoatKayak());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatChest.class, new RenderBoatChest());
	}
	
	@Override
	public void registerKeyBindings()
	{
		KeyBindingRegistry.registerKeyBinding(new KeyHandlerAnchor());
	}
}
