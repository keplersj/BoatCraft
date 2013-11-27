package k2b6s9j.BoatCraft.compatibility.forestry.item.boat.forestry.wood.ebony;

import cpw.mods.fml.common.registry.GameRegistry;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.ebony.EntityBoatEbony;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class BoatEbony extends ItemCustomBoat {

	public static int ID;
	public static int shiftedID;
	
	public BoatEbony(int id) {
		super(id);
		setUnlocalizedName("boat.forestry.wood.ebony.empty");
        func_111206_d("boatcraft:boat.forestry.wood.ebony.empty");
    	GameRegistry.registerItem(this, "Ebony Wood Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatEbony", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatEbony entity = new EntityBoatEbony(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
