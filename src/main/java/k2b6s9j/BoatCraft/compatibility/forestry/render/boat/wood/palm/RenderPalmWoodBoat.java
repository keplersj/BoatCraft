package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.palm;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.palm.EntityBoatPalm;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderPalmWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/palm.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatPalm entity =  null;
		return entity;
	}
}