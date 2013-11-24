package k2b6s9j.BoatCraft.render.boat.wood.spruce;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruce;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderSpruceWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
    	return new ResourceLocation("boatcraft:textures/boats/spruce.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatSpruce entity =  null;
		return entity;
	}
}