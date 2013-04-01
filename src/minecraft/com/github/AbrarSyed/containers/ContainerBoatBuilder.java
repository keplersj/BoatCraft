package com.github.AbrarSyed.containers;

import com.github.AbrarSyed.SeaCraft.blocks.TileEntityBoatBuilder;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

public class ContainerBoatBuilder extends Container
{
	private TileEntityBoatBuilder builder;
	
	public ContainerBoatBuilder(InventoryPlayer player, TileEntityBoatBuilder builder)
	{
		this.builder = builder;
		this.addSlotToContainer(new Slot(builder, 0, 32, 16));
		this.addSlotToContainer(new SlotFurnace(player.player, builder, 1, 84, 32));
		this.addSlotToContainer(new Slot(builder, 2, 14, 53));
		this.addSlotToContainer(new Slot(builder, 3, 32, 53));
		this.addSlotToContainer(new Slot(builder, 4, 50, 53));
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

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return builder.isUseableByPlayer(entityplayer);
	}

}
