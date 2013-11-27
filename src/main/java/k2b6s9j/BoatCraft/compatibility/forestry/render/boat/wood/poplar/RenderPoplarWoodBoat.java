package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.poplar;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.poplar.EntityBoatPoplar;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderPoplarWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/poplar.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatPoplar entity =  null;
		return entity;
	}
}