package k2b6s9j.BoatCraft.entity.item;

import k2b6s9j.BoatCraft.render.RenderCustomBoat;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCustomBoat extends EntityBoat {
	
	public EntityCustomBoat(World world, double par2, double par4, double par6, ResourceLocation resource) {
		super(world, par2, par4, par6);
		RenderCustomBoat.setCustomBoatTexture(resource);
	}
}
