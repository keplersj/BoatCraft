package k2b6s9j.BoatCraft.client;

import k2b6s9j.BoatCraft.blocks.TileEntityBoatBuilder;
import k2b6s9j.BoatCraft.containers.ContainerBoatBuilder;
import k2b6s9j.BoatCraft.network.PacketSC2BuildBoat;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

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

		buttonList.add(new GuiButton(1, x + 160, y + 60, bw / 3, 20, "Build"));
	}

	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (builder.canBuild())
		{
			PacketDispatcher.sendPacketToServer(new PacketSC2BuildBoat(builder.xCoord, builder.yCoord, builder.zCoord).getPacket250());
		}
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		String s = StatCollector.translateToLocal(this.builder.getInvName());
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 0, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 83 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/SeaCraft/textures/guis/boatBuilder.png");
		int startX = (this.width - this.xSize) / 2 - (this.width - this.xSize) / 6;
		int startY = (this.height - this.ySize) / 2 - (this.height - this.ySize) / 6;

		int x = (int) (this.xSize * (256 / 176.0));
		int y = (int) (this.ySize * (191 / 165.0));

		this.drawTexturedModalRect(startX, startY, 0, 0, x, y);
	}

}
