package k2b6s9j.BoatCraft.render.boat.wood.jungle;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungle;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderJungleWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
    	return new ResourceLocation("boatcraft:textures/boats/jungle.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatJungle entity =  null;
		return entity;
	}
}