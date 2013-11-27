package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.mahogany;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.mahogany.EntityBoatMahogany;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderMahoganyWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/mahogany.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatMahogany entity =  null;
		return entity;
	}
}