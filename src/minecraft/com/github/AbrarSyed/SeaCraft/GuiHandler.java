package com.github.AbrarSyed.SeaCraft;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.blocks.TileEntityBoatBuilder;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatBase;
import com.github.AbrarSyed.SeaCraft.boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.client.GuiBoatBuilder;
import com.github.AbrarSyed.SeaCraft.client.GuiBoatFurnace;
import com.github.AbrarSyed.containers.ContainerBoatBuilder;
import com.github.AbrarSyed.containers.ContainerBoatFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		EntityBoatBase boat;
		switch (ID)
			{
				case 0:
					boat = (EntityBoatBase) player.worldObj.getEntityByID(x);
					return new ContainerBoatFurnace(player.inventory, (EntityBoatFurnace) boat);
				case 1:
					TileEntityBoatBuilder builder = (TileEntityBoatBuilder) world.getBlockTileEntity(x, y, z);
					return new ContainerBoatBuilder(player.inventory, builder);
				case 2:
					boat = (EntityBoatBase) player.worldObj.getEntityByID(x);
					return new ContainerChest(player.inventory, (IInventory) boat);
			}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		EntityBoatBase boat;
		switch (ID)
			{
				case 0:
					boat = (EntityBoatBase) player.worldObj.getEntityByID(x);
					return new GuiBoatFurnace(player.inventory, (EntityBoatFurnace) boat);
				case 1:
					TileEntityBoatBuilder builder = (TileEntityBoatBuilder) world.getBlockTileEntity(x, y, z);
					return new GuiBoatBuilder(player.inventory, builder);
				case 2:
					boat = (EntityBoatBase) player.worldObj.getEntityByID(x);
					return new GuiChest(player.inventory, (IInventory) boat);
			}
		return null;
	}

}
