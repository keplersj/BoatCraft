package k2b6s9j.BoatCraft.item.boat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBoat;

public class BoatOak extends ItemBoat {

	public static int ID;
	
	public BoatOak(int id) {
		super(id);
		setUnlocalizedName("boatOak");
        func_111206_d("boatcraft:boatOak");
    	GameRegistry.registerItem(this, "Oak Wood Boat");
	}

}
