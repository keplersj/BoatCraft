package k2b6s9j.BoatCraft.item.boat;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBoat;

public class BoatSpruce extends ItemBoat {

	public static int ID;
	
	public BoatSpruce(int id) {
		super(id);
		setUnlocalizedName("boatSpruce");
        func_111206_d("boatcraft:boatSpruce");
    	GameRegistry.registerItem(this, "Spruce Wood Boat");
	}

}
