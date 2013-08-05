package k2b6s9j.BoatCraft.entity.item;

import k2b6s9j.BoatCraft.item.boat.BoatTNT;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBoatTNT extends EntityCustomBoat {
	
	public BoatTNT item;
	
	public EntityBoatTNT(World par1World)
    {
        super(par1World);
    }

    public EntityBoatTNT(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    public boolean func_130002_c(EntityPlayer player)
    {
    	//Do not mount this boat on right click!
        return false;
    }
    
    public Block getDefaultDisplayTile()
    {
        return Block.tnt;
    }
    
    @Override
	public boolean isCustomBoat()
    {
    	return true;
    }
    
	@Override
    public ItemStack customBoatItem()
    {
    	return new ItemStack(item, 1, 0);
    }

}
