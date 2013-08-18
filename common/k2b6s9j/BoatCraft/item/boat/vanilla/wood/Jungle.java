package k2b6s9j.BoatCraft.item.boat.vanilla.wood;

import k2b6s9j.BoatCraft.entity.boat.vanilla.wood.EntityJungle;
import k2b6s9j.BoatCraft.item.boat.ItemCustomBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class Jungle extends ItemCustomBoat {
	
	public static int ID;
	public final int shiftedID = this.itemID;

	public Jungle(int par1) {
		super(par1);
		setUnlocalizedName("boatJungle");
        func_111206_d("boatcraft:boatJungle");
    	GameRegistry.registerItem(this, "Jungle Wood Boat");
    	OreDictionary.registerOre("itemBoat", new ItemStack(this));
	}
	
	public ItemStack deployEntity(ItemStack itemStack, World world, EntityPlayer player, int x, int y, int z) {
		EntityJungle entity = new EntityJungle(world, (double)((float)x + 0.5F), (double)((float)y + 1.0F), (double)((float)z + 0.5F));
        entity.rotationYaw = (float)(((MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);
        
        if (!world.getCollidingBoundingBoxes(entity, entity.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
        {
            return itemStack;
        }

        if (!world.isRemote)
        {
            world.spawnEntityInWorld(entity);
        }

        if (!player.capabilities.isCreativeMode)
        {
            --itemStack.stackSize;
        }
        return itemStack;
	}

}
