package k2b6s9j.boatcraft.core.traits

import net.minecraft.item.ItemStack
import net.minecraft.inventory.IInventory
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer

trait Modifier
{
	def isRideable: Boolean = false
	def getBlock: Block = null
	def getMeta: Int = 0
	def getInventory: IInventory = null 
	def getName: String = null
	def getContent: ItemStack = null
	
	def openGUI(player: EntityPlayer) {}
	
	override def toString: String = getName replaceAll(" ", "") toLowerCase
}