package k2b6s9j.BoatCraft.item.boat;

import net.minecraft.item.ItemBoat;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatBirch extends ItemBoat {

	public static int ID;
	
	public BoatBirch(int id) {
		super(id);
		setUnlocalizedName("boatBirch");
        func_111206_d("boatcraft:boatBirch");
    	GameRegistry.registerItem(this, "Birch Wood Boat");
	}

}
