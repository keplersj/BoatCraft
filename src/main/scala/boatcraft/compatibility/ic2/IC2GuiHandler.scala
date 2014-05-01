package boatcraft.compatibility.ic2

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object IC2GuiHandler extends IGuiHandler
{
	override def getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Object  =
		//TODO
		???
	override def getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Object =
		//TODO
		???
}