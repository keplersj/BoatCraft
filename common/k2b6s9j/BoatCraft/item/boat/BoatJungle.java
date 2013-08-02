package k2b6s9j.BoatCraft.item.boat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBoat;

public class BoatJungle extends ItemBoat {

	public static int ID;
	
	public BoatJungle(int id) {
		super(id);
		setUnlocalizedName("boatJungle");
        func_111206_d("boatcraft:boatJungle");
    	GameRegistry.registerItem(this, "Jungle Wood Boat");
	}

}
