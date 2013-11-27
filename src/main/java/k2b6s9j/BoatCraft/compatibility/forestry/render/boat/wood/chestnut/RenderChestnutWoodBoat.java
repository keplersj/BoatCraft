package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.chestnut;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.chestnut.EntityBoatChestnut;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderChestnutWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/chestnut.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatChestnut entity =  null;
		return entity;
	}
}