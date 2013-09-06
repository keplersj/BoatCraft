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
		return 0;
	}

	@Override
	public int getSecondaryColour() {
		return 0;
	}

	@Override
	public void addValidItem(ItemStack validItem) {
		return;
	}

	@Override
	public Collection<ItemStack> getValidItems(EntityPlayer player) {
		return null;
	}

	@Override
	public boolean isValidItem(EntityPlayer player, ItemStack itemstack) {
		return false;
	}

}
