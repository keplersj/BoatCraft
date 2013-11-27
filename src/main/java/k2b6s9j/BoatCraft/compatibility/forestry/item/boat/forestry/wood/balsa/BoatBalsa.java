package k2b6s9j.BoatCraft.compatibility.forestry.item.boat.forestry.wood.balsa;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.balsa.EntityBoatBalsa;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatBalsa extends ItemCustomBoat {

	public static int ID;
	public static int shiftedID;
	
	public BoatBalsa(int id) {
		super(id);
		setUnlocalizedName("boat.foresty.wood.balsa.empty");
        func_111206_d("boatcraft:boat.forestry.wood.balsa.empty");
    	GameRegistry.registerItem(this, "Balsa Wood Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatBalsa", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
		EntityBoatBalsa entity = new EntityBoatBalsa(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
		return entity;
	}
}
