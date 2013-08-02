package k2b6s9j.BoatCraft.render;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;

public class RenderCustomBoat extends RenderBoat {
	
	static ResourceLocation resource;
	
	public static void setCustomBoatTexture(ResourceLocation texture)
	{
		resource = texture;
		FMLLog.log(Level.INFO, "Trying to render " + texture);
	}
	
	@Override
	public ResourceLocation func_110781_a(EntityBoat par1EntityBoat)
    {
        return resource;
    }

}