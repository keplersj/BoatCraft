package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.wood.acacia;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.acacia.EntityBoatAcacia;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderAcaciaWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/acacia.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatAcacia entity =  null;
		return entity;
	}
}