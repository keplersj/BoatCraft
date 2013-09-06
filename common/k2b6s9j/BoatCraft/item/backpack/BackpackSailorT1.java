package k2b6s9j.BoatCraft.item.backpack;

import net.minecraft.item.Item;
import forestry.api.storage.EnumBackpackType;
import forestry.api.storage.IBackpackDefinition;
import forestry.api.storage.IBackpackInterface;

public class BackpackSailorT1 extends Item implements IBackpackInterface {

	public static int ID;
	public static int shiftedID;
	
	public BackpackSailorT1(int par1) {
		super(par1);
		shiftedID = this.itemID;
	}

	@Override
	public Item addBackpack(int itemID, IBackpackDefinition definition, EnumBackpackType type) {
		return this;
	}

}
