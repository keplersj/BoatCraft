package k2b6s9j.BoatCraft.item.stick;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class StickJungle extends Item {

	public static int ID;
	
	public StickJungle(int id) {
		super(id);
		setUnlocalizedName("stickJungle");
		setCreativeTab(CreativeTabs.tabMaterials);
        func_111206_d("boatcraft:stickJungle");
    	GameRegistry.registerItem(this, "Jungle Wood Sticks");
	}

}
