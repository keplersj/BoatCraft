package boatcraft.compatibility.industrialcraft2

import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import boatcraft.api.boat.EntityCustomBoat
/*import ic2.core.block.machine.tileentity.TileEntityNuke
import boatcraft.compatibility.IC2
import com.sun.xml.internal.bind.v2.model.core.ID
import ic2.core.block.machine.gui.GuiNuke
import ic2.core.block.machine.ContainerNuke*/

object IC2GuiHandler// extends IGuiHandler
{
	/*override def getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
	{
		IC2.log info "Recieved client IC2 GUI request for ID " + id + ", x " + x + ", y " + y + " and z " + z
		return id match {
			case 0 => ??? //TODO Generator
			case 1 =>
				new GuiNuke(new ContainerNuke(player,
						world.getEntityByID(x).asInstanceOf[EntityCustomBoat].getBlockDataWithType[TileEntityNuke]))
			case _ => null
		}
	}

	override def getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
	{
		IC2.log info "Recieved server IC2 GUI request for ID " + id + ", x " + x + ", y " + y + " and z " + z
		return id match {
			case 0 => ??? //TODO Generator
			case 1 =>
				new ContainerNuke(player,
						world.getEntityByID(x).asInstanceOf[EntityCustomBoat].getBlockDataWithType[TileEntityNuke])
			case _ => null
		}
	}*/
}