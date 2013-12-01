package k2b6s9j.BoatCraft.containers;

import k2b6s9j.BoatCraft.blocks.TileEntityBoatBuilder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBoatBuilder extends Container
{
	private TileEntityBoatBuilder builder;
	
	public ContainerBoatBuilder(InventoryPlayer player, TileEntityBoatBuilder builder)
	{
		this.builder = builder;
		this.addSlotToContainer(new Slot(builder, 0, -15, 36));
		this.addSlotToContainer(new Slot(builder, 1, 36, 36));
		this.addSlotToContainer(new Slot(builder, 2, 79, 36));
		this.addSlotToContainer(new Slot(builder, 3, 122, 36));
		
		this.addSlotToContainer(new Slot(builder, 4, 57, 13));
		this.addSlotToContainer(new Slot(builder, 5, 79, 13));
		this.addSlotToContainer(new Slot(builder, 6, 101, 13));
		
		this.addSlotToContainer(new Slot(builder, 7, 57, 58));
		this.addSlotToContainer(new Slot(builder, 8, 79, 58));
		this.addSlotToContainer(new Slot(builder, 9, 101, 58));
		
		
		int i;
		
		// players inventory
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 7 + j * 18, 97 + i * 18));
			}
		}

		//players quickBar
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(player, i, 7 + i * 18, 155));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return builder.isUseableByPlayer(entityplayer);
	}

}
