package k2b6s9j.boatcraft.compatibility.ironchest

import cpw.mods.fml.common.network.IGuiHandler
import cpw.mods.ironchest.ContainerIronChest
import cpw.mods.ironchest.IronChestType
import cpw.mods.ironchest.TileEntityIronChest
import cpw.mods.ironchest.client.GUIChest
import k2b6s9j.boatcraft.api.boat.EntityBoatContainer
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
  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
		GUIChest.GUI.buildGUI(IronChestType.values()(ID), player.inventory,
				getBoat(world, x, y, z).getInventory.asInstanceOf[TileEntityIronChest])

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
  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int) =
		new ContainerIronChest(player.inventory, getBoat(world, x, y, z), IronChestType.values()(ID), 0, 0)

  //TODO: Fill Documentation
  /**
   *
   * @param world
   * @param x
   * @param y
   * @param z
   * @return
   */
  private def getBoat(world: World, x: Int, y: Int, z: Int) =
		world.getEntitiesWithinAABB(classOf[EntityBoatContainer],
				AxisAlignedBB getBoundingBox(x, y, z, x + 1, y + 1, z + 1))
				.get(0)
				.asInstanceOf[EntityBoatContainer]
}