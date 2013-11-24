package k2b6s9j.BoatCraft.render.boat.wood.birch;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirch;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderBirchWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
    	return new ResourceLocation("boatcraft:textures/boats/birch.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
        return null;
	}
}