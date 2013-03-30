package com.github.AbrarSyed.SeaCraft;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.client.GuiBoatFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case 0:
				List<EntityBoatFurnace> list = world.getEntitiesWithinAABB(EntityBoatFurnace.class, AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1));
				EntityBoatFurnace boat = list.get(0);
				return new ContainerBoatFurnace(player.inventory, boat);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID)
		{
			case 0:
				List<EntityBoatFurnace> list = world.getEntitiesWithinAABB(EntityBoatFurnace.class, AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1));
				EntityBoatFurnace boat = list.get(0);
				return new GuiBoatFurnace(player.inventory, boat);
		}
		return null;
	}

}
