package k2b6s9j.BoatCraft.item.boat.vanilla.wood;

import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class Oak extends ItemCustomBoat {
	
	public static int ID;
	public final int shiftedID = this.itemID;

	public Oak(int par1) {
		super(par1);
		setUnlocalizedName("boatOak");
        func_111206_d("boatcraft:boatOak");
    	GameRegistry.registerItem(this, "Oak Wood Boat");
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
	}

}
