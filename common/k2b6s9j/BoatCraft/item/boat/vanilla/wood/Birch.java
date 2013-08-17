package k2b6s9j.BoatCraft.item.boat.vanilla.wood;

import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class Birch extends ItemCustomBoat {
	
	public static int ID;
	public final int shiftedID = this.itemID;

	public Birch(int par1) {
		super(par1);
		setUnlocalizedName("boatBirch");
        func_111206_d("boatcraft:boatBirch");
    	GameRegistry.registerItem(this, "Birch Wood Boat");
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
	}

}
