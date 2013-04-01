package com.github.AbrarSyed.SeaCraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.blocks.TileEntityBoatBuilder;
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
		switch (ID)
			{
				case 0:
					EntityBoatFurnace boat = (EntityBoatFurnace) player.worldObj.getEntityByID(x);
					return new ContainerBoatFurnace(player.inventory, boat);
				case 1:
					TileEntityBoatBuilder builder = (TileEntityBoatBuilder) world.getBlockTileEntity(x, y, z);
					return new ContainerBoatBuilder(player.inventory, builder);
			}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
			{
				case 0:
					EntityBoatFurnace boat = (EntityBoatFurnace) player.worldObj.getEntityByID(x);
					return new GuiBoatFurnace(player.inventory, boat);
				case 1:
					TileEntityBoatBuilder builder = (TileEntityBoatBuilder) world.getBlockTileEntity(x, y, z);
					return new GuiBoatBuilder(player.inventory, builder);
			}
		return null;
	}

}
