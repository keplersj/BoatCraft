package k2b6s9j.BoatCraft.render.boat.wood.oak;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOak;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderOakWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
    	return new ResourceLocation("textures/entity/boat.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatOak entity =  null;
		return entity;
	}
}