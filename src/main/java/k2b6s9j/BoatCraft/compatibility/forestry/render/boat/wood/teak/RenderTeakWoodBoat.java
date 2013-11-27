package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.teak;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.teak.EntityBoatTeak;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderTeakWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/teak.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatTeak entity =  null;
		return entity;
	}
}