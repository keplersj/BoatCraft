package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.wood.plum;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.plum.EntityBoatPlum;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderPlumWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/plum.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatPlum entity =  null;
		return entity;
	}
}