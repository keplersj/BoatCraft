package k2b6s9j.BoatCraft.entity.boat.vanilla.wood;

import net.minecraft.world.World;
import k2b6s9j.BoatCraft.entity.boat.EntityCustomBoat;

public class EntitySpruce extends EntityCustomBoat {

	public EntitySpruce(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}
}
