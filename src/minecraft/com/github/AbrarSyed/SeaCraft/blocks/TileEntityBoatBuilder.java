package com.github.AbrarSyed.SeaCraft.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBoatBuilder extends TileEntity implements IInventory
{
	
    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbt)
    {
    	super.readFromNBT(nbt);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbt)
    {
    	super.writeToNBT(nbt);
    }
	
    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        return null;
    }
    
	/*
	 * INVENTORY STUFF
	 * ---------------------------------------------------------------------------
	 */

	@Override
	public int getSizeInventory()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInvName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvNameLocalized()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openChest()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
