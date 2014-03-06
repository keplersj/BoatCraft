package k2b6s9j.boatcraft.compatibility.ironchest

import cpw.mods.fml.common.network.IGuiHandler
import cpw.mods.ironchest.{ ContainerIronChest, IronChestType, TileEntityIronChest }
import cpw.mods.ironchest.client.GUIChest
import k2b6s9j.boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.AxisAlignedBB
import net.minecraft.world.World

//TODO: Fill Documentation
/**
  *
  */
object IronChestsGuiHandler extends IGuiHandler
{
	//TODO: Fill Documentation
	/**
	  *
	  * @param ID
	  * @param player
	  * @param world
	  * @param x
	  * @param y
	  * @param z
	  * @return
	  */
	override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
		try
	    {
			return GUIChest.GUI.buildGUI(IronChestType.values()(ID), player.inventory,
				getBoat(world, x, y, z).getInventory.asInstanceOf[TileEntityIronChest])
		}
		catch
		{
			case _: IndexOutOfBoundsException => return null
		}

	//TODO: Fill Documentation
	/**
	  *
	  * @param ID
	  * @param player
	  * @param world
	  * @param x
	  * @param y
	  * @param z
	  * @return
	  */
	override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef =
		try
	    {
	        return new ContainerIronChest(player.inventory, getBoat(world, x, y, z), IronChestType.values()(ID), 0, 0)
        }
        catch
        {
            case _: IndexOutOfBoundsException => return null
        }

	//TODO: Fill Documentation
	/**
	  *
	  * @param world
	  * @param x
	  * @param y
	  * @param z
	  * @return
	  */
	@throws(classOf[IndexOutOfBoundsException])
	private def getBoat(world: World, x: Int, y: Int, z: Int) =
		world.getEntitiesWithinAABB(classOf[EntityCustomBoat],
			AxisAlignedBB getBoundingBox (x - 0.1, y - 0.1, z - 0.1, x + 1.1, y + 1.1, z + 1.1))
			.get(0)
			.asInstanceOf[EntityCustomBoat]
}