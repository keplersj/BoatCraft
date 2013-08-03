package k2b6s9j.BoatCraft.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CommonProxy {
	
	@SideOnly(Side.CLIENT)
	public static void registerRenderers() {
		ClientProxy.registerRenderers();
	}

}
