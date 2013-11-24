package k2b6s9j.BoatCraft.item.boat.wood.spruce;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.entity.boat.wood.spruce.EntityBoatSpruceTNT;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class BoatSpruceTNT extends ItemCustomBoat {
	
	public static int ID;
	public static int shiftedID;
	
	public BoatSpruceTNT(int id) {
		super(id);
		setUnlocalizedName("boat.wood.spruce.tnt");
        func_111206_d("boatcraft:boat.wood.spruce.tnt");
    	GameRegistry.registerItem(this, "Spruce TNT Boat");
    	shiftedID = this.itemID;
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
    	OreDictionary.registerOre("boat", new ItemStack(this));
    	OreDictionary.registerOre("boatSpruce", new ItemStack(this));
    	OreDictionary.registerOre("boatTNT", new ItemStack(this));
    	OreDictionary.registerOre("boatSpruceTNT", new ItemStack(this));
	}
	
	@Override
	public EntityCustomBoat getEntity(World world, int x, int y, int z) {
        return new EntityBoatSpruceTNT(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
	}
}
