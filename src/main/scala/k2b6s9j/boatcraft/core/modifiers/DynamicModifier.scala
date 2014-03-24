package k2b6s9j.boatcraft.core.modifiers

import java.util.Map
import scala.collection.JavaConversions.mapAsScalaMap
import scala.util.control.Breaks.break
import cpw.mods.fml.common.{FMLCommonHandler, ModContainer}
import cpw.mods.fml.common.network.{IGuiHandler, NetworkRegistry}
import cpw.mods.fml.relauncher.{ReflectionHelper, Side}
import k2b6s9j.boatcraft.api.boat.EntityCustomBoat
import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.entity.player.EntityPlayerMP
import io.netty.channel.embedded.EmbeddedChannel
import cpw.mods.fml.common.network.internal.FMLMessage
import net.minecraft.inventory.Container
import net.minecraft.client.gui.Gui

class DynamicModifier(val block: Block, val meta: Int, val gui: String, val container: String)
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
			val TEName = block.createTileEntity(
                FMLCommonHandler.instance.getMinecraftServerInstance.getEntityWorld,
                meta).getClass.getName
			new Thingy(TEName).doCompilation
            return Class.forName("k2b6s9j.boatcraft.core.modifiers.dynamic." +
            		TEName.substring(TEName.lastIndexOf(".") + 1) + "BoatInv").
            		newInstance.asInstanceOf[IInventory]
		}
		null
	}
    
	override def getName: String = block getUnlocalizedName
    
	override def getContent: ItemStack = new ItemStack(block, 1, meta)
    
	override def interact(player: EntityPlayer, boat: EntityCustomBoat)
	{
		if (FMLCommonHandler.instance.getEffectiveSide == Side.CLIENT)
		{
			FMLCommonHandler.instance.showGuiScreen(
				Class.forName(gui).getConstructor(
					classOf[IInventory], classOf[IInventory]).
					    newInstance(player.inventory, boat.getInventory))
		}
		else
		{
			var entityPlayerMP = player.asInstanceOf[EntityPlayerMP]
            var remoteGuiContainer = Class.forName(container).getConstructor(
                    classOf[IInventory], classOf[IInventory]).
                        newInstance(player.inventory, boat.getInventory).
                        asInstanceOf[Container]
            if (remoteGuiContainer != null)
            {
                entityPlayerMP.getNextWindowId
                entityPlayerMP.closeContainer
                val windowId = entityPlayerMP.currentWindowId
                entityPlayerMP.openContainer = remoteGuiContainer
                entityPlayerMP.openContainer.windowId = windowId
                entityPlayerMP.openContainer addCraftingToCrafters entityPlayerMP
            }
		}
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