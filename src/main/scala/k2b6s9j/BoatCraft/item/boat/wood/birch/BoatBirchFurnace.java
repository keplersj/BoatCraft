package k2b6s9j.BoatCraft.item.boat.wood.birch;

import cpw.mods.fml.common.registry.GameRegistry;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.birch.EntityBoatBirchFurnace;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BoatBirchFurnace extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatBirchFurnace(int id) {
		super(id);
		setUnlocalizedName("boat.wood.birch.furnace");
        func_111206_d("boatcraft:boat.wood.birch.furnace");
    	GameRegistry.registerItem(this, "Birch Furnace Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatBirch", new ItemStack(this));
    	OreDictionary.registerOre("boatFurnace", new ItemStack(this));
    	OreDictionary.registerOre("boatBirchFurnace", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatBirchFurnace entity = new EntityBoatBirchFurnace(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
