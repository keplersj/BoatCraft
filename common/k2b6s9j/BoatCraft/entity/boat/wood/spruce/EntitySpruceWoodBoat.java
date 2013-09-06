package k2b6s9j.BoatCraft.entity.boat.wood.spruce;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruce;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntitySpruceWoodBoat extends EntityCustomBoat {
	
	public BoatSpruce item;
	
	public EntitySpruceWoodBoat(World par1World)
    {
		super(par1World);
    }
	
	public EntitySpruceWoodBoat(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
	}
	
	@Override
	public boolean isCustomBoat()
    {
    	return true;
    }
    
	@Override
	public boolean useItemID()
	{
		return true;
	}
    
	@Override
    public int customBoatItemID()
    {
    	return item.shiftedID;
    }
	
	@Override
	public ItemStack customPlank()
	{
		return new ItemStack(Block.planks, 1, 1);
	}
}
