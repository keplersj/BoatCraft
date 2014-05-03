package boatcraft.compatibility.ironchest

import boatcraft.api.boat.EntityCustomBoat
import cpw.mods.fml.common.network.IGuiHandler
import cpw.mods.ironchest.{ContainerIronChest, IronChestType, TileEntityIronChest}
import cpw.mods.ironchest.client.GUIChest
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object IronChestsGuiHandler extends IGuiHandler
{
	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
		GUIChest.GUI.buildGUI(IronChestType.values()(ID), player.inventory,
			world.getEntityByID(x).asInstanceOf[EntityCustomBoat].getInventory.asInstanceOf[TileEntityIronChest])
	
	override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
		new ContainerIronChest(player.inventory,
			world.getEntityByID(x).asInstanceOf[EntityCustomBoat].getInventory,
			IronChestType.values()(ID), 0, 0)
}