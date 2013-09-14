package k2b6s9j.BoatCraft.render.boat.forestry.wood.ebony;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.ebony.EntityBoatEbony;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderEbonyWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/ebony.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatEbony entity =  null;
		return entity;
	}
}