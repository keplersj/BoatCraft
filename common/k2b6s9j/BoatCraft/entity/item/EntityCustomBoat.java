package k2b6s9j.BoatCraft.entity.item;

import java.util.logging.Level;

import k2b6s9j.BoatCraft.render.RenderCustomBoat;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityCustomBoat extends EntityBoat {

	public EntityCustomBoat(World world, double par2, double par4, double par6, int typeID) {
		super(world, par2, par4, par6);
		BoatMaterialTexture(typeID);
	}
	
	public void BoatMaterialTexture(int typeID) {
		if (typeID == 0) {
			RenderCustomBoat.setCustomBoatTexture(new ResourceLocation("boatcraft/textures/boat/oak.png"));
		}
		if (typeID == 1) {
			RenderCustomBoat.setCustomBoatTexture(new ResourceLocation("boatcraft/textures/boat/spruce.png"));
		}
		if (typeID == 2) {
			RenderCustomBoat.setCustomBoatTexture(new ResourceLocation("boatcraft/textures/boat/birch.png"));
		}
		if (typeID == 3) {
			RenderCustomBoat.setCustomBoatTexture(new ResourceLocation("boatcraft/textures/boat/jungle.png"));
		}
		else {
			RenderCustomBoat.setCustomBoatTexture(new ResourceLocation("boatcraft/textures/boat/unknown.png"));
		}
	}

}
