package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.wenge;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.wenge.EntityBoatWenge;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderWengeWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/wenge.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatWenge entity =  null;
		return entity;
	}
}