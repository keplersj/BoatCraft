package com.github.AbrarSyed.SeaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBoatKayak extends Item
{
	public ItemBoatKayak(int par1)
	{
		super(par1);
		this.setMaxStackSize(2);
		this.setHasSubtypes(false);
		this.canRepair = false;
		this.setUnlocalizedName(SeaCraft.MODID + ":kayak");
		this.setCreativeTab(SeaCraft.tab);
	}

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		MovingObjectPosition trace = SeaCraft.getPlayerLookingSpot(player, true);

		if (trace == null)
			return stack;

		double spawnX, spawnY, spawnZ;

		if (trace.typeOfHit.equals(EnumMovingObjectType.ENTITY))
		{
			if (trace.entityHit instanceof EntityBoatKayak)
				return stack;

			spawnX = trace.entityHit.posX;
			spawnY = trace.entityHit.posY + trace.entityHit.height + 1;
			spawnZ = trace.entityHit.posZ;
		}
		else
		{
			spawnX = trace.blockX;
			spawnY = trace.blockY + 1;
			spawnZ = trace.blockZ;
		}

		if (!world.isRemote)
		{
			EntityBoatKayak entity = new EntityBoatKayak(world, spawnX, spawnY, spawnZ);
			world.spawnEntityInWorld(entity);

			if (!player.capabilities.isCreativeMode)
				stack.stackSize--;
		}

		return stack;
	}
}
