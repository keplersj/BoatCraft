package k2b6s9j.BoatCraft.entity.item;

import net.minecraft.tileentity.Hopper;
import net.minecraft.world.World;

public class EntityBoatHopper extends EntityBoatContainer implements Hopper {
	
	public EntityBoatHopper(World par1World)
    {
        super(par1World);
    }

    public EntityBoatHopper(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }

	@Override
	public World getWorldObj() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getXPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getYPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZPos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

}
