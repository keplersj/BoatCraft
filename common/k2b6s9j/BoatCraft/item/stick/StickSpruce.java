package k2b6s9j.BoatCraft.item.stick;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StickSpruce extends Item {

	public static int ID;
	
	public StickSpruce(int id) {
		super(id);
		setUnlocalizedName("stickSpruce");
		setCreativeTab(CreativeTabs.tabMaterials);
        func_111206_d("boatcraft:stickSpruce");
    	GameRegistry.registerItem(this, "Spruce Wood Sticks");
	}

}
