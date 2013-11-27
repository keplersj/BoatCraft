package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.baobob;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.baobob.EntityBoatBaobob;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderBaobobWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/baobob.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatBaobob entity =  null;
		return entity;
	}
}