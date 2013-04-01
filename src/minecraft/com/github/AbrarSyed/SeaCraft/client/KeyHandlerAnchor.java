package com.github.AbrarSyed.SeaCraft.client;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import com.github.AbrarSyed.SeaCraft.SeaCraft;
import com.github.AbrarSyed.SeaCraft.network.PacketSC3ToggleAnchor;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class KeyHandlerAnchor extends KeyHandler
{
	private static final KeyBinding key = new KeyBinding("Toggle Boat Anchor", Keyboard.KEY_R);

	public KeyHandlerAnchor()
	{
		super(new KeyBinding[] {key}, new boolean[] {false});
	}

	@Override
	public String getLabel()
	{
		return SeaCraft.MODID+"_AnchorKeyHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		// nothing.
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		PacketDispatcher.sendPacketToServer(new PacketSC3ToggleAnchor().getPacket250());
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}

}
