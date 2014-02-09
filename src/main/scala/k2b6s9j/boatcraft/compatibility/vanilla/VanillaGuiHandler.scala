package k2b6s9j.boatcraft.compatibility.vanilla

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraft.client.gui.inventory.GuiChest

class VanillaGuiHandler extends IGuiHandler
{
	def getClientGuiElement(id: Int, player: EntityPlayer, world: World,
		x: Int, y: Int, z: Int): AnyRef =
	{
		id match
		{
			//case 0 => new GuiChest(player.inventory, )
			case _ => null
		}
	}
	
	def getServerGuiElement(id: Int, player: EntityPlayer, world: World,
		x: Int, y: Int, z: Int): AnyRef =
	{
		id match
		{
			case _ => null
		}
	}
}