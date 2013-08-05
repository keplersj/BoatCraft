package k2b6s9j.BoatCraft.entity.item;

import k2b6s9j.BoatCraft.item.boat.BoatOak;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityOakWoodBoat extends EntityCustomBoat {
	
	public BoatOak item;
	
	public EntityOakWoodBoat(World par1World)
    {
		super(par1World);
    }
	
	public EntityOakWoodBoat(World world, double par2, double par4, double par6) {
		super(world, par2, par4, par6);
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
	
	@Override
	public ItemStack customPlank()
	{
		return new ItemStack(Block.planks, 1, 0);
	}
	
	@Override
	public ItemStack customStick()
	{
		return new ItemStack(Item.stick, 1, 0);
	}
	
}
