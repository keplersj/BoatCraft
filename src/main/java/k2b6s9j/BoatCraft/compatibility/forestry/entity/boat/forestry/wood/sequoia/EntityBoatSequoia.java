package k2b6s9j.BoatCraft.compatibility.forestry.entity.boat.forestry.wood.sequoia;

import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;
import k2b6s9j.BoatCraft.item.boat.forestry.wood.sequoia.BoatSequoia;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityBoatSequoia extends EntityCustomBoat {
	
	public BoatSequoia item;
	
	public EntityBoatSequoia(World par1World)
    {
		super(par1World);
    }
	
	public EntityBoatSequoia(World world, double par2, double par4, double par6) {
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
		return new ItemStack(Block.planks, 1, 0);
	}
	
}
