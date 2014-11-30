package boatcraft.compatibility.vanilla

import boatcraft.compatibility.vanilla.modifiers.blocks.Workbench
import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraft.util.BlockPos

object VanillaGuiHandler extends IGuiHandler {
	override def getClientGuiElement(id: Int, player: EntityPlayer, world: World,
																	 x: Int, y: Int, z: Int): AnyRef =
		id match
		{
			case 0 => new Workbench.Gui(player.inventory, world, new BlockPos(x, y, z))
			case _ => null
		}

	override def getServerGuiElement(id: Int, player: EntityPlayer, world: World,
																	 x: Int, y: Int, z: Int): AnyRef =
		id match
		{
			case 0 => new Workbench.Container(player.inventory, world, new BlockPos(x, y, z))
			case _ => null
		}
}