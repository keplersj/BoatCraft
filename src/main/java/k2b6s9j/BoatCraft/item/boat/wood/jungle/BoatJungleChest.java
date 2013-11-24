package k2b6s9j.BoatCraft.item.boat.wood.jungle;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.jungle.EntityBoatJungleChest;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatJungleChest extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatJungleChest(int id) {
		super(id);
		setUnlocalizedName("boat.wood.jungle.chest");
        func_111206_d("boatcraft:boat.wood.jungle.chest");
    	GameRegistry.registerItem(this, "Jungle Chest Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatJungle", new ItemStack(this));
    	OreDictionary.registerOre("boatChest", new ItemStack(this));
    	OreDictionary.registerOre("boatJungleChest", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
        return new EntityBoatJungleChest(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
	}
}
