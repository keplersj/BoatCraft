package com.github.AbrarSyed.SeaCraft.blocks;

import com.github.AbrarSyed.SeaCraft.FunctionHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoatBuilder extends TileEntity implements IInventory
{
	private static final String UNLOCALIZED = "SeaCraft.boatbuilder";
	private static final String INVENTORY = "inventory";
	
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbt)
    {
    	super.readFromNBT(nbt);
    	stacks = FunctionHelper.readInventoryFromNBT(nbt.getTagList(INVENTORY), 11);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    	nbt.setTag(INVENTORY, FunctionHelper.writeInventoryToNBT(stacks));
    }
	
    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
    	//TODO: make a packet.
        return null;
    }
    
	/*
	 * INVENTORY STUFF
	 * ---------------------------------------------------------------------------
	 */
    
    private ItemStack[] stacks = new ItemStack[11];

	@Override
	public int getSizeInventory()
	{
		return 11;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return stacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		return FunctionHelper.decrStackSize(i, j, stacks, this);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		stacks[i] = itemstack;
	}

	@Override
	public String getInvName()
	{
		return UNLOCALIZED;
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return true;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistance(xCoord, yCoord, zCoord) <= 10;
	}

	@Override
	public void openChest()
	{
		// useless
	}

	@Override
	public void closeChest()
	{
		// useless
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
}
