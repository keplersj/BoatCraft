package k2b6s9j.BoatCraft.item.boat.wood.birch;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirch;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatBirch extends ItemCustomBoat {

	public static int ID;
	public static int shiftedID;
	
	public BoatBirch(int id) {
		super(id);
		setUnlocalizedName("boat.wood.birch.empty");
        func_111206_d("boatcraft:boat.wood.birch.empty");
    	GameRegistry.registerItem(this, "Birch Wood Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatBirch", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatBirch entity = new EntityBoatBirch(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
