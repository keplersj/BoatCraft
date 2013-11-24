package k2b6s9j.BoatCraft.item.boat.wood.oak;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatOakChest;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatOakChest extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatOakChest(int id) {
		super(id);
		setUnlocalizedName("boat.wood.oak.chest");
        func_111206_d("boatcraft:boat.wood.oak.chest");
    	GameRegistry.registerItem(this, "Oak Chest Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatOak", new ItemStack(this));
    	OreDictionary.registerOre("boatChest", new ItemStack(this));
    	OreDictionary.registerOre("boatOakChest", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
        return new EntityBoatOakChest(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
	}
}
