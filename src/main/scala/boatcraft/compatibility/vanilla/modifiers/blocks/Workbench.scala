package boatcraft.compatibility.vanilla.modifiers.blocks

import boatcraft.api.modifiers.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.world.World
import boatcraft.compatibility.Vanilla
import net.minecraft.client.gui.inventory.GuiCrafting
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.util.StatCollector
import net.minecraft.util.BlockPos

object Workbench extends Block {
	override def getBlock = Blocks.crafting_table.getDefaultState
	
	override def getUnlocalizedName: String = "Workbench"
	
	override def getLocalizedName = "vanilla.blocks.workbench.name"
	
	override def getContent: ItemStack = new ItemStack(Blocks.crafting_table)
	
	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
	{
		if (player.isInstanceOf[EntityPlayerMP])
			player openGui("boatcraft", Vanilla.code << 6, boat.worldObj, boat.getEntityId, -1, 0)
		
		true
	}
	
	private[vanilla] class Container(inventoryPlayer: InventoryPlayer, world: World, pos: BlockPos)
		extends ContainerWorkbench(inventoryPlayer, world, pos)
	{
		override def canInteractWith(player: EntityPlayer) = true
	}
	
	private[vanilla] class Gui(inventoryPlayer: InventoryPlayer, world: World, pos: BlockPos)
		extends GuiCrafting(inventoryPlayer, world, pos)
	{
		inventorySlots = new Container(inventoryPlayer, world, pos)
		
		override protected def drawGuiContainerForegroundLayer(arg1: Int, arg2: Int)
		{
			fontRendererObj.drawString(StatCollector.translateToLocal("vanilla.blocks.workbench.gui.title"), 28, 6, 4210752)
			fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752)
		}
	}
	
}
