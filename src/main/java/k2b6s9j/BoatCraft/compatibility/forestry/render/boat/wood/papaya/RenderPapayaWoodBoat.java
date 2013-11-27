package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.papaya;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.papaya.EntityBoatPapaya;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderPapayaWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/papaya.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatPapaya entity =  null;
		return entity;
	}
}