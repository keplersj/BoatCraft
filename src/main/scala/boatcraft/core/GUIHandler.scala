package boatcraft.core

import java.util.HashMap

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object GUIHandler extends IGuiHandler
{
	var handlerMap = new HashMap[Int, IGuiHandler]
	
	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
	{
        BoatCraft.log info "Recieved local GUI request: " + ID + ", " + x
		return handlerMap.get(ID >> 6).getClientGuiElement(ID & 63, player, world, x, y, z)
	}
	
	override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
    {
        BoatCraft.log info "Recieved remote GUI request: " + ID + ", " + x
        return handlerMap.get(ID >> 6).getServerGuiElement(ID & 63, player, world, x, y, z)
    }
}