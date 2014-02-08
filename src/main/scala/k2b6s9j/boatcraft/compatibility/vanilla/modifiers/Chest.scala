package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.core.traits.Modifier
import net.minecraft.item.ItemStack
import net.minecraft.inventory.IInventory
import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.tileentity.TileEntityChest
import net.minecraft.entity.player.EntityPlayer

trait Chest extends Modifier
{
	override def getBlock: Block = Blocks.chest
	override def getMeta: Int = 0
	override def getInventory: IInventory = null//NOPE new TileEntityChest
	override def getName: String = "Chest"
	override def getContent: ItemStack = new ItemStack(Blocks.chest)
	override def openGUI(player: EntityPlayer) =
		player displayGUIChest getInventory
}