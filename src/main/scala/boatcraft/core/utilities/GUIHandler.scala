package boatcraft.core.utilities

import java.util.EnumMap
import cpw.mods.fml.common.{FMLCommonHandler, FMLLog}
import cpw.mods.fml.common.network.{FMLEmbeddedChannel, FMLOutboundHandler}
import cpw.mods.fml.common.network.FMLOutboundHandler.OutboundTarget
import cpw.mods.fml.common.network.NetworkRegistry
import cpw.mods.fml.common.network.internal.FMLMessage.OpenGui
import cpw.mods.fml.common.network.internal.FMLNetworkHandler
import cpw.mods.fml.relauncher.{ReflectionHelper, Side}
import net.minecraft.entity.player.{EntityPlayer, EntityPlayerMP}
import net.minecraft.world.World
import cpw.mods.fml.common.network.internal.FMLMessage
import boatcraft.core.BoatCraft

object GUIHandler
{
	def openGui(entityPlayer: EntityPlayer, mod: AnyRef, modGuiId: Int, world: World, x: Int, y: Int, z: Int)
	{
		var mc = FMLCommonHandler.instance findContainerFor mod
		if (entityPlayer.isInstanceOf[EntityPlayerMP])
		{
			var entityPlayerMP = entityPlayer.asInstanceOf[EntityPlayerMP]
			var remoteGuiContainer = NetworkRegistry.INSTANCE.getRemoteGuiContainer(mc, entityPlayerMP, modGuiId, world, x, y, z)
			if (remoteGuiContainer != null)
			{
				entityPlayerMP.getNextWindowId
				entityPlayerMP.closeContainer
				val windowId = entityPlayerMP.currentWindowId
				var openGui = null //TODO
				var embeddedChannel = BoatCraft.channels get Side.SERVER
				(embeddedChannel attr FMLOutboundHandler.FML_MESSAGETARGET) set OutboundTarget.PLAYER
				(embeddedChannel attr FMLOutboundHandler.FML_MESSAGETARGETARGS) set entityPlayerMP
				embeddedChannel writeOutbound openGui
				entityPlayerMP.openContainer = remoteGuiContainer
				entityPlayerMP.openContainer.windowId = windowId
				entityPlayerMP.openContainer addCraftingToCrafters entityPlayerMP
			}
		}
		else if (FMLCommonHandler.instance.getSide.equals(Side.CLIENT))
		{
			var guiContainer = NetworkRegistry.INSTANCE.getLocalGuiContainer(mc, entityPlayer, modGuiId, world, x, y, z)
			FMLCommonHandler.instance showGuiScreen guiContainer
		}
		else
		{
			FMLLog.fine("Invalid attempt to open a local GUI on a dedicated server. This is likely a bug. GUIID: %s,%d", mc.getModId, modGuiId: Integer)
		}
	}
}