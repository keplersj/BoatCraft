package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.willow;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.willow.EntityBoatWillow;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderWillowWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/willow.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatWillow entity =  null;
		return entity;
	}
}