package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.wood.balsa;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.balsa.EntityBoatBalsa;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderBalsaWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/balsa.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatBalsa entity =  null;
		return entity;
	}
}