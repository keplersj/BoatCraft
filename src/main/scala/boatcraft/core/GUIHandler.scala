package boatcraft.core

import java.util

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object GUIHandler extends IGuiHandler {
	var handlerMap = new util.HashMap[Int, IGuiHandler]

	override def getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
		BoatCraft.log info "Recieved client GUI request for ID " + id + ", x " + x + ", y " + y + " and z " + z
		var ret = handlerMap.get(id >> 6).getClientGuiElement(id & 63, player, world, x, y, z)
		BoatCraft.log info "Client returned " + ret
		return ret
	}

	override def getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
		BoatCraft.log info "Recieved server GUI request for ID " + id + ", x " + x + ", y " + y + " and z " + z
		var ret = handlerMap.get(id >> 6).getServerGuiElement(id & 63, player, world, x, y, z)
		BoatCraft.log info "Server returned " + ret
		return ret
	}
}