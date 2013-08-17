package k2b6s9j.BoatCraft.item.boat.vanilla.wood;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class Spruce extends ItemCustomBoat {
	
	public static int ID;
	public final int shiftedID = this.itemID;

	public Spruce(int par1) {
		super(par1);
		setUnlocalizedName("boatSpruce");
        func_111206_d("boatcraft:boatSpruce");
    	GameRegistry.registerItem(this, "Spruce Wood Boat");
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
	}
}
