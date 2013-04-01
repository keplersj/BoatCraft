package com.github.AbrarSyed.SeaCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

import com.github.AbrarSyed.SeaCraft.boatsS.EntityBoatFurnace;

public class ContainerBoatFurnace extends Container
{
	private EntityBoatFurnace boat;
	
	public ContainerBoatFurnace(InventoryPlayer player, EntityBoatFurnace boat)
	{
		this.boat = boat;
		this.addSlotToContainer(new Slot(boat, 0, 32, 16));
		this.addSlotToContainer(new SlotFurnace(player.player, boat, 1, 84, 32));
		this.addSlotToContainer(new Slot(boat, 2, 14, 53));
		this.addSlotToContainer(new Slot(boat, 3, 32, 53));
		this.addSlotToContainer(new Slot(boat, 4, 50, 53));
		int i;

		
		// players inventory
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		//players quickBar
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
		}
	}
	
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.boat.getItemCookingTime());
        par1ICrafting.sendProgressBarUpdate(this, 1, this.boat.getBurningLeft());
    }

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return boat.isUseableByPlayer(entityplayer);
	}

}