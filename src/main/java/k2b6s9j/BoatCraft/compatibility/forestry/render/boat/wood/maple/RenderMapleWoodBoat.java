package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.maple;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.maple.EntityBoatMaple;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderMapleWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/maple.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatMaple entity =  null;
		return entity;
	}
}