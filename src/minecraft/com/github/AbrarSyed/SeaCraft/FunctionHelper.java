package com.github.AbrarSyed.SeaCraft;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public abstract class FunctionHelper
{
	private static final String	SLOT	= "slot";

	private FunctionHelper()
	{

	}

	public static ItemStack[] readInventoryFromNBT(NBTTagList list, int size)
	{
		ItemStack[] stacks = new ItemStack[size];

		NBTTagCompound compound;
		int index;
		for (int i = 0; i < list.tagCount(); i++)
		{
			compound = (NBTTagCompound) list.tagAt(i);
			index = compound.getInteger(SLOT);
			stacks[index] = ItemStack.loadItemStackFromNBT(compound);
		}
		
		return stacks;
	}
	
	public static NBTTagList writeInventoryToNBT(ItemStack[] stacks)
	{
		NBTTagCompound stackNBT;
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < 5; i++)
		{
			if (stacks[i] == null)
				continue;

			stackNBT = new NBTTagCompound();
			stackNBT.setInteger(SLOT, i);
			stacks[i].writeToNBT(stackNBT);
			list.appendTag(stackNBT);
		}
		
		return list;
	}

}
