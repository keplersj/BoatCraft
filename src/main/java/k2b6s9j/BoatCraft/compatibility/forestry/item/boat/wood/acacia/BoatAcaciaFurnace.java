package k2b6s9j.BoatCraft.compatibility.forestry.item.boat.forestry.wood.acacia;

import cpw.mods.fml.common.registry.GameRegistry;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.acacia.EntityBoatAcaciaFurnace;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BoatAcaciaFurnace extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatAcaciaFurnace(int id) {
		super(id);
		setUnlocalizedName("boat.forestry.wood.acacia.furnace");
        func_111206_d("boatcraft:boat.forestry.wood.acacia.furnace");
    	GameRegistry.registerItem(this, "Acacia Furnace Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatAcacia", new ItemStack(this));
    	OreDictionary.registerOre("boatFurnace", new ItemStack(this));
    	OreDictionary.registerOre("boatAcaciaFurnace", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatAcaciaFurnace entity = new EntityBoatAcaciaFurnace(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
