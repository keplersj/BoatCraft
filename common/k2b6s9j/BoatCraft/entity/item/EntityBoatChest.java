package k2b6s9j.BoatCraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class EntityBoatChest extends EntityBoatContainer {

	public EntityBoatChest(World par1World)
    {
        super(par1World);
    }

    public EntityBoatChest(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 27;
    }

    public int getMinecartType()
    {
        return 1;
    }

    public Block getDefaultDisplayTile()
    {
        return Block.chest;
    }

    public int getDefaultDisplayTileOffset()
    {
        return 8;
    }

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}
	
	@Override
	public String getInvName() {
		return "Boat";
	}
}
