package com.github.AbrarSyed.SeaCraft.api;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class SeaCraftAPI
{
	private static HashMap<Integer, ItemStack>	destroyables	= new HashMap<Integer, ItemStack>();
	private static HashMap<ItemHolder, Integer>	fuels	= new HashMap<ItemHolder, Integer>();

	static
	{
		addDestroyableBlock(Block.snow.blockID, null);
		addDestroyableBlock(Block.waterlily.blockID, new ItemStack(Block.waterlily));
	}

	private SeaCraftAPI()
	{
	}
	
	/*
	 * DESTROYABLES  ---------------------------------------------------
	 */

	public static void addDestroyableBlock(int block, ItemStack drop)
	{
		destroyables.put(block, drop);
	}

	public static boolean isBlockDestroyable(int id)
	{
		return destroyables.containsKey(id);
	}

	public static ItemStack getDestroyableDrop(int id)
	{
		return destroyables.get(id);
	}
	
	/*
	 * FUELS  ---------------------------------------------------
	 */
	
	public static void addFuelType(ItemStack stack, int duration)
	{
		fuels.put(new ItemHolder(stack), duration);
	}

	public static boolean isFuelType(ItemStack stack)
	{
		return fuels.containsKey(new ItemHolder(stack));
	}

	public static int getFuelDuration(ItemStack stack)
	{
		Integer i = fuels.get(new ItemHolder(stack));
		return i == null ? 0 : i;
	}
	
	
	private static class ItemHolder
	{
		public final int meta;
		public final int ID;
		
		public ItemHolder(ItemStack stack)
		{
			this.ID = stack.itemID;
			this.meta = stack.getItemDamage();
		}
		
		@Override
		public boolean equals(Object obj)
		{
			return false;
		}
		
		public boolean equals(ItemHolder holder)
		{
			return meta == holder.meta && ID == holder.ID;
		}
		
		@Override
		public int hashCode()
		{
			return (10000*ID) + meta;
		}
	}
}
