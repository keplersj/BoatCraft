package k2b6s9j.BoatCraft.compatibility.forestry.item.boat.forestry.wood.wenge;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.wenge.EntityBoatWenge;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatWenge extends ItemCustomBoat {

	public static int ID;
	public static int shiftedID;
	
	public BoatWenge(int id) {
		super(id);
		setUnlocalizedName("boat.forestry.wood.wenge.empty");
        func_111206_d("boatcraft:boat.forestry.wood.wenge.empty");
    	GameRegistry.registerItem(this, "Wenge Wood Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatWenge", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatWenge entity = new EntityBoatWenge(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
