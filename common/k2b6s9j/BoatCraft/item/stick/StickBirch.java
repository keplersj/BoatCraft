package k2b6s9j.BoatCraft.item.stick;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class StickBirch extends Item {

	public static int ID;
	
	public StickBirch(int id) {
		super(id);
		setUnlocalizedName("stickBirch");
		setCreativeTab(CreativeTabs.tabMaterials);
        func_111206_d("boatcraft:stickBirch");
    	GameRegistry.registerItem(this, "Birch Wood Sticks");
	}

}
