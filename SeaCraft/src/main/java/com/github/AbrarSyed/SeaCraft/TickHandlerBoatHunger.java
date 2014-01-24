package com.github.AbrarSyed.SeaCraft;

import java.util.EnumSet;
import java.util.HashMap;

import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerBoatHunger implements ITickHandler
{
	private HashMap<String, Integer>	map			= new HashMap<String, Integer>();
	private static final int			TICK_NUM	= 30;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		// nothin
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		EntityPlayer player = (EntityPlayer) tickData[0];

		if (player.ridingEntity != null && player.ridingEntity instanceof EntityBoat)
		{
			if (map.get(player.username) == null)
				map.put(player.username, 0);
			else if (map.get(player.username) >= TICK_NUM)
			{
				// hurt
				player.getFoodStats().addExhaustion(1);
				map.put(player.username, 0);
			}
			else
			{
				int num = map.get(player.username);
				map.put(player.username, num++);
			}
		}
		else
			map.remove(player);
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel()
	{
		return "Vanilla Boat Hunger Ticker";
	}

}
