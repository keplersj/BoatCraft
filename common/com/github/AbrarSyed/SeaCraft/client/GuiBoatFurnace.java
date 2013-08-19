package com.github.AbrarSyed.SeaCraft.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.github.AbrarSyed.SeaCraft.boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.network.PacketSC0MountEntity;
import com.github.AbrarSyed.SeaCraft.network.PacketSC1StartBoat;
import com.github.AbrarSyed.containers.ContainerBoatFurnace;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBoatFurnace extends GuiContainer
{
	private EntityBoatFurnace	furnace;

	private GuiButton			buttonMount;
	private GuiButton			buttonStart;

	public GuiBoatFurnace(InventoryPlayer player, EntityBoatFurnace boat)
	{
		super(new ContainerBoatFurnace(player, boat));
		this.furnace = boat;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		int x = (width - xSize) / 2, y = (height - ySize) / 2;
		int bw = xSize - 22;
		buttonMount = new GuiButton(1, x + 120, y + 20, bw / 3, 20, this.mc.thePlayer.ridingEntity == null ? "Mount" : "UnMount");
		buttonStart = new GuiButton(2, x + 120, y + 42, bw / 3, 20, furnace.canMove ? "Stop" : "Start");

		buttonList.add(buttonMount);
		buttonList.add(buttonStart);
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 1)
		{
			//Minecraft.getMinecraft().thePlayer.mountEntity(furnace);
			PacketDispatcher.sendPacketToServer(new PacketSC0MountEntity(furnace).getPacket250());

			buttonMount.displayString = this.mc.thePlayer.ridingEntity == null ? "Mount" : "UnMount";

		}
		else if (button.id == 2)
		{
			//true? > false
			// false? > true
			boolean start = !furnace.canMove;

			furnace.canMove = start;
			PacketDispatcher.sendPacketToServer(new PacketSC1StartBoat(furnace, start).getPacket250());

			buttonStart.displayString = start ? "Stop" : "Start";
		}
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

		if (this.furnace.isBurningFuel())
		{
			i1 = this.furnace.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k + 33, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
		}

		i1 = this.furnace.getCookProgressScaled(24);
		this.drawTexturedModalRect(k + 52, l + 33, 176, 14, i1 + 1, 16);
	}
}
