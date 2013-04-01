package com.github.AbrarSyed.SeaCraft.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.github.AbrarSyed.SeaCraft.blocks.TileEntityBoatBuilder;
import com.github.AbrarSyed.containers.ContainerBoatBuilder;

public class GuiBoatBuilder extends GuiContainer
{
	private TileEntityBoatBuilder	builder;

	public GuiBoatBuilder(InventoryPlayer player, TileEntityBoatBuilder builder)
	{
		super(new ContainerBoatBuilder(player, builder));
		this.builder = builder;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int x = (width - xSize) / 2, y = (height - ySize) / 2;
		int bw = xSize - 22;

		buttonList.add(new GuiButton(1, x + 120, y + 20, bw / 3, 20, "build"));
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = StatCollector.translateToLocal(this.builder.getInvName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/SeaCraft/textures/guis/boatBuilder.png");
		int k = (this.width - this.xSize) / 2 - (this.width - this.xSize) / 6;
		int l = (this.height - this.ySize) / 2 - (this.height - this.ySize) / 6;

		int x = (int) (this.xSize * (256 / 176.0));
		int y = (int) (this.ySize * (191 / 165.0));

		this.drawTexturedModalRect(k, l, 0, 0, x, y);
	}

}
