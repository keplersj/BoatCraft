package k2b6s9j.BoatCraft.entity.item;

import k2b6s9j.BoatCraft.item.boat.BoatBirch;
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
    public int customBoatItem()
    {
    	return item.shiftedID;
    }
	
}
