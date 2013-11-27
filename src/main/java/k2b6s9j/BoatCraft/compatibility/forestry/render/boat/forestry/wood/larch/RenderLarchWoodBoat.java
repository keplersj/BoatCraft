package k2b6s9j.BoatCraft.compatibility.forestry.render.boat.forestry.wood.larch;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.larch.EntityBoatLarch;
import k2b6s9j.BoatCraft.render.RenderBoat;
import net.minecraft.util.ResourceLocation;

public class RenderLarchWoodBoat extends RenderBoat {
	
	@Override
	public ResourceLocation getTexture() {
		return new ResourceLocation("boatcraft:textures/boats/forestry/larch.png");
    }
	
	@Override
	public EntityCustomBoat getEntity() {
		EntityBoatLarch entity =  null;
		return entity;
	}
}