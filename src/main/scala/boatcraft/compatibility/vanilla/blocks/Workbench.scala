package boatcraft.compatibility.vanilla.blocks

import boatcraft.api.traits.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.world.World
import boatcraft.compatibility.Vanilla
import net.minecraft.client.gui.inventory.GuiCrafting
import net.minecraft.client.resources.I18n
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayerMP
import boatcraft.core.BoatCraft

object Workbench extends Block
{
	override def getBlock = Blocks.crafting_table
	
	override def getMeta: Int = 0
	
	override def getName: String = "Workbench"
	
	override def getContent: ItemStack = new ItemStack(Blocks.crafting_table)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat)
	{
		if (player.isInstanceOf[EntityPlayerMP])
			player openGui ("boatcraft", Vanilla.code << 6, boat.worldObj, boat.getEntityId, -1, 0)
	}
	
	private[vanilla] class Container(inventoryPlayer: InventoryPlayer, world: World,
		x: Int, y: Int, z: Int)
		extends ContainerWorkbench(inventoryPlayer, world, x, y, z)
	{
		override def canInteractWith(player: EntityPlayer) = true
	}
	
	private[vanilla] class Gui(inventoryPlayer: InventoryPlayer, world: World,
		x: Int, y: Int, z: Int)
		extends GuiCrafting(inventoryPlayer, world, x, y, z)
	{
		inventorySlots = new Container(inventoryPlayer, world, x, y, z)
		
		override protected def drawGuiContainerForegroundLayer(arg1: Int, arg2: Int)
		{
			fontRendererObj.drawString(I18n.format("boatcraft.container.crafting", Array()), 28, 6, 4210752)
			fontRendererObj.drawString(I18n.format("container.inventory", Array()), 8, ySize - 96 + 2, 4210752)
		}
	}
}
