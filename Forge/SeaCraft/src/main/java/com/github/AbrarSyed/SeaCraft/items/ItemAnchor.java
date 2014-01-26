package k2b6s9j.BoatCraft.items;

import k2b6s9j.BoatCraft.SeaCraft;
import net.minecraft.item.Item;

public class ItemAnchor extends Item
{
	public static int ID;
	public static int shiftedID;
	
	public ItemAnchor(int par1, boolean hasRope)
	{
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(SeaCraft.tab);
		this.setNoRepair();
		setHasSubtypes(false);
		setUnlocalizedName(SeaCraft.MODID + ":anchor"+ (hasRope ? "" : "_norope"));
		setCreativeTab(SeaCraft.tab);
	}

}
