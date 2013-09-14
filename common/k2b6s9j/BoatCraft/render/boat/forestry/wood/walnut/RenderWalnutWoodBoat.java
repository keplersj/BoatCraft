package k2b6s9j.BoatCraft.render.boat.forestry.wood.walnut;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.walnut.EntityBoatWalnut;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderWalnutWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/walnut.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatWalnut entity =  null;
		return entity;
	}
}