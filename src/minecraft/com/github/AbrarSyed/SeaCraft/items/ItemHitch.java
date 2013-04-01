package com.github.AbrarSyed.SeaCraft.items;

import com.github.AbrarSyed.SeaCraft.SeaCraft;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHitch extends Item
{
	public ItemHitch(int par1)
	{
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(SeaCraft.tab);
		this.setNoRepair();
		setHasSubtypes(false);
		setUnlocalizedName(SeaCraft.MODID + ":hitch");
		setCreativeTab(SeaCraft.tab);
	}
}
