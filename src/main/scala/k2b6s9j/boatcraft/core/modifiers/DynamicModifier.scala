package k2b6s9j.boatcraft.core.modifiers

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block
import k2b6s9j.boatcraft.api.boat.EntityCustomBoat
import net.minecraft.inventory.IInventory
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.server.MinecraftServer
import cpw.mods.fml.common.FMLCommonHandler

class DynamicModifier(final val block: Block, final val meta: Int)
    extends Modifier
{
	override def isRideable = false
	
	override def getBlock: Block = block
    
	override def getMeta = meta
    
	override def getInventory(boat: EntityCustomBoat): IInventory =
	{
		if (block.createTileEntity(
				FMLCommonHandler.instance.getMinecraftServerInstance.getEntityWorld,
				meta) != null)
		{
			
		}
		null
	}
    
	override def getName: String = block getUnlocalizedName
    
	override def getContent: ItemStack = new ItemStack(block, 1, meta)
    
	override def interact(player: EntityPlayer, boat: EntityCustomBoat)
	{
		
	}
    
	override def update(boat: EntityCustomBoat)
	{
		
	}
    
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound)
	{
		
	}
    
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound)
	{
		
	}
}