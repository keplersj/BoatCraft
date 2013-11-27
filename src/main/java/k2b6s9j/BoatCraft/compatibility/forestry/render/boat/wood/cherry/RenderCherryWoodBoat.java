package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.wood.cherry;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.cherry.EntityBoatCherry;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderCherryWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/cherry.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatCherry entity =  null;
		return entity;
	}
}