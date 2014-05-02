package boatcraft.compatibility.ironchest

import cpw.mods.fml.common.network.IGuiHandler
import cpw.mods.ironchest.{ ContainerIronChest, IronChestType, TileEntityIronChest }
import cpw.mods.ironchest.client.GUIChest
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.AxisAlignedBB
import net.minecraft.world.World
import boatcraft.compatibility.IronChests

object IronChestsGuiHandler extends IGuiHandler
{
	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
	{
        IronChests.log info "Recieved local IronChests GUI request: " + ID + ", " + x
		return GUIChest.GUI.buildGUI(IronChestType.values()(ID), player.inventory,
			world.getEntityByID(x).asInstanceOf[EntityCustomBoat].getInventory.asInstanceOf[TileEntityIronChest])
    }
	
	override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
	{
        IronChests.log info "Recieved remote IronChests GUI request: " + ID + ", " + x
		return new ContainerIronChest(player.inventory,
			world.getEntityByID(x).asInstanceOf[EntityCustomBoat].getInventory,
			IronChestType.values()(ID), 0, 0)
    }
}