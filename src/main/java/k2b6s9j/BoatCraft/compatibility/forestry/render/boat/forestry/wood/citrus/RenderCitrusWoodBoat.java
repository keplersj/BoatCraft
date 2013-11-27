package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.citrus;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.citrus.EntityBoatCitrus;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderCitrusWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/citrus.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatCitrus entity =  null;
		return entity;
	}
}