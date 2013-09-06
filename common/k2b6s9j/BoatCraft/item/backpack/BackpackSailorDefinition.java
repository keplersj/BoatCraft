package k2b6s9j.BoatCraft.item.backpack;

import java.util.Collection;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import forestry.api.storage.IBackpackDefinition;

public class BackpackSailorDefinition implements IBackpackDefinition {

	@Override
	public String getKey() {
		return "Sailor";
	}

	@Override
	public String getName() {
		return "Sailor's Backpack";
	}

	@Override
	public int getPrimaryColour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSecondaryColour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addValidItem(ItemStack validItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<ItemStack> getValidItems(EntityPlayer player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidItem(EntityPlayer player, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
