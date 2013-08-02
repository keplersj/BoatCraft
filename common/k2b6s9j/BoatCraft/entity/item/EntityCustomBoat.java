package k2b6s9j.BoatCraft.entity.item;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.world.World;

public class EntityCustomBoat extends EntityBoat {

	public EntityCustomBoat(World world, double par2, double par4, double par6, int typeID) {
		super(world, par2, par4, par6);
		BoatMaterialTexture(typeID);
	}
	
	public void BoatMaterialTexture(int typeID) {
		if (typeID == 0) {
			FMLLog.log(Level.INFO, "This should be an Oak textured boat!");
		}
		if (typeID == 1) {
			FMLLog.log(Level.INFO, "This should be a Spruce textured boat!");
		}
		if (typeID == 2) {
			FMLLog.log(Level.INFO, "This should be a Birch textured boat!");
		}
		if (typeID == 3) {
			FMLLog.log(Level.INFO, "This should be a Jungle textured boat!");
		}
		else {
			FMLLog.log(Level.INFO, "I'm not sure what texture you want this boat to be!");
		}
	}

}
