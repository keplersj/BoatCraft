package com.github.AbrarSyed.SeaCraft.api;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class SeaCraftAPI
{
	private static HashMap<Integer, ItemStack>	destroyables	= new HashMap<Integer, ItemStack>();

	static
	{
		addDestroyableBlock(Block.snow.blockID, null);
		addDestroyableBlock(Block.waterlily.blockID, new ItemStack(Block.waterlily));
	}

	private SeaCraftAPI()
	{
	}

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
}
