package boatcraft.core

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import java.util

object GUIHandler extends IGuiHandler {
	var handlerMap = new util.HashMap[Int, IGuiHandler]

	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
		handlerMap.get(ID >> 6).getClientGuiElement(ID & 63, player, world, x, y, z)

	override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
		handlerMap.get(ID >> 6).getServerGuiElement(ID & 63, player, world, x, y, z)
}