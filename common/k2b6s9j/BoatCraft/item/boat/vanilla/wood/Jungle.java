package k2b6s9j.BoatCraft.item.boat.vanilla.wood;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Jungle extends ItemBoat {
	
	public int ID;
	public final int shiftedID = this.itemID;

	public Jungle(int par1) {
		super(par1);
		setUnlocalizedName("boatJungle");
        func_111206_d("boatcraft:boatJungle");
    	GameRegistry.registerItem(this, "Jungle Wood Boat");
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
	}

}
