package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.lime;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.lime.EntityBoatLime;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderLimeWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/lime.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatLime entity =  null;
		return entity;
	}
}