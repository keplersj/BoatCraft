package k2b6s9j.BoatCraft.item.boat.forestry.wood.acacia;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.forestry.wood.acacia.EntityBoatAcaciaChest;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatAcaciaChest extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatAcaciaChest(int id) {
		super(id);
		setUnlocalizedName("boat.forestry.wood.acacia.chest");
        func_111206_d("boatcraft:boat.forestry.wood.acacia.chest");
    	GameRegistry.registerItem(this, "Oak Chest Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatAcacia", new ItemStack(this));
    	OreDictionary.registerOre("boatChest", new ItemStack(this));
    	OreDictionary.registerOre("boatAcaciaChest", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatAcaciaChest entity = new EntityBoatAcaciaChest(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
