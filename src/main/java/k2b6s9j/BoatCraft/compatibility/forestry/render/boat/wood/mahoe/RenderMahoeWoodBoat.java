package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.mahoe;

import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.mahoe.EntityBoatMahoe;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderMahoeWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/mahoe.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatMahoe entity =  null;
		return entity;
	}
}