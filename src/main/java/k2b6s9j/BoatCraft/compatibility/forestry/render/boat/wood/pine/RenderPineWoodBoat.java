package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.wood.pine;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.pine.EntityBoatPine;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderPineWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/pine.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatPine entity =  null;
		return entity;
	}
}