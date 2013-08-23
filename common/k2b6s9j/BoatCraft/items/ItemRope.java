package k2b6s9j.BoatCraft.items;

import k2b6s9j.BoatCraft.SeaCraft;
import net.minecraft.item.Item;

public class ItemRope extends Item
{
	public static int ID;
	public static int shiftedID;
	
	public ItemRope(int par1)
	{
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(SeaCraft.tab);
		this.setNoRepair();
		setHasSubtypes(false);
		setUnlocalizedName(SeaCraft.MODID + ":rope");
		setCreativeTab(SeaCraft.tab);
	}
}
