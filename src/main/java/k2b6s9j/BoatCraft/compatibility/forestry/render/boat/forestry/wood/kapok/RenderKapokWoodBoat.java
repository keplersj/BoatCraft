package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.kapok;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.kapok.EntityBoatKapok;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderKapokWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/kapok.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatKapok entity =  null;
		return entity;
	}
}