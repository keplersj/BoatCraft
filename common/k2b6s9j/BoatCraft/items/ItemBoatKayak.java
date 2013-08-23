package k2b6s9j.BoatCraft.items;

import k2b6s9j.BoatCraft.boats.EntityBoatBase;
import k2b6s9j.BoatCraft.boats.EntityBoatKayak;
import net.minecraft.world.World;

public class ItemBoatKayak extends ItemBoatBase
{
	public static int ID;
	public static int shiftedID;
	
	public ItemBoatKayak(int par1)
	{
		super(par1);
	}

	@Override
	protected String getNameForTexture()
	{
		return "kayak";
	}

	@Override
	protected EntityBoatBase getBoat(World world)
	{
		return new EntityBoatKayak(world);
	}
}
