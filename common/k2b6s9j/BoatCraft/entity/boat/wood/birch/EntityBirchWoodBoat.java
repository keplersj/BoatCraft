package k2b6s9j.BoatCraft.entity.boat.wood.birch;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.wood.birch.BoatBirch;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBirchWoodBoat extends EntityCustomBoat {
	
	public BoatBirch item;
	
	public EntityBirchWoodBoat(World par1World)
    {
		super(par1World);
    }
	
	public EntityBirchWoodBoat(World world, double par2, double par4, double par6) {
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
		return new ItemStack(Block.planks, 1, 2);
	}
	
}
