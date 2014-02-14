package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.api.Boat.EntityCustomBoat
import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.world.World
import k2b6s9j.boatcraft.compatibility.Vanilla
import net.minecraft.client.gui.inventory.GuiCrafting
import net.minecraft.client.resources.I18n

class Workbench extends Modifier
{
	override def getBlock: Block = Blocks.crafting_table
	override def getMeta: Int = 0
	
	override def getName: String = "Workbench"
	
	override def getContent: ItemStack = new ItemStack(Blocks.crafting_table)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat)
	{
		//player displayGUIWorkbench(boat.x toInt, boat.y toInt, boat.z toInt)
		player openGui(Vanilla, 0,
				boat.worldObj, boat.posX toInt, boat.posY toInt, boat.posZ toInt)
	}
}

object Workbench
{
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
