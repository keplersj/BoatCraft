package com.github.AbrarSyed.SeaCraft.items;

import com.github.AbrarSyed.SeaCraft.SeaCraft;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRope extends Item
{
	public ItemRope(int par1)
	{
		super(par1);
		this.setMaxStackSize(1);
		this.setCreativeTab(SeaCraft.tab);
		this.setNoRepair();
		setHasSubtypes(false);
		setUnlocalizedName(SeaCraft.MODID + ":rope");
		setCreativeTab(SeaCraft.tab);
	}
}
