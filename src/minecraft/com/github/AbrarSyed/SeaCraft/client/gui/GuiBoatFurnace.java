package com.github.AbrarSyed.SeaCraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.container.ContainerBoatFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBoatFurnace extends GuiContainer
{
	private EntityBoatFurnace	furnace;

	public GuiBoatFurnace(InventoryPlayer player, EntityBoatFurnace boat)
	{
		super(new ContainerBoatFurnace(player, boat));
		this.furnace = boat;
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = StatCollector.translateToLocal(this.furnace.getInvName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/SeaCraft/textures/guis/boatFurnace.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;

		if (this.furnace.isBurning())
		{
			i1 = this.furnace.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.furnace.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 52, l + 34, 176, 14, i1 + 1, 16);
	}
}
