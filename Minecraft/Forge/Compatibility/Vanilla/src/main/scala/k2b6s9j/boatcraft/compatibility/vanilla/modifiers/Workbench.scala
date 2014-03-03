package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.inventory.ContainerWorkbench
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.world.World
import k2b6s9j.boatcraft.compatibility.Vanilla
import net.minecraft.client.gui.inventory.GuiCrafting
import net.minecraft.client.resources.I18n
import k2b6s9j.boatcraft.api.boat.EntityCustomBoat

//TODO: Fill Documentation
/**
 *
 */
object Workbench extends Modifier
{
  //TODO: Fill Documentation
  /**
   *
   * @return block used for rendering
   */
  override def getBlock: Block = Block.workbench

  //TODO: Fill Documentation
  /**
   *
   * @return the metadata of the block being rendering in the boat
   */
  override def getMeta: Int = 0

  //TODO: Fill Documentation
  /**
   *
   * @return the name of the Modifier
   */
  override def getName: String = "Workbench"

  //TODO: Fill Documentation
  /**
   *
   * @return the ItemStack used when crafting
   */
  override def getContent: ItemStack = new ItemStack(Block.workbench)

  //TODO: Fill Documentation
  /**
   *
   * @param player the player interacting with the Boat
   * @param boat the Boat being interacted with
   */
  override def interact(player: EntityPlayer, boat: EntityCustomBoat)
	{
		//player displayGUIWorkbench(boat.x toInt, boat.y toInt, boat.z toInt)
		player openGui(Vanilla, 0,
				boat.worldObj, boat.posX toInt, boat.posY toInt, boat.posZ toInt)
	}

  //TODO: Fill Documentation
  /**
   *
   * @param inventoryPlayer
   * @param world
   * @param x
   * @param y
   * @param z
   */
  private[vanilla] class Container(inventoryPlayer: InventoryPlayer, world: World,
			x: Int, y: Int, z: Int)
		extends ContainerWorkbench(inventoryPlayer, world, x, y, z)
	{
    //TODO: Fill Documentation
    /**
     *
     * @param player
     * @return
     */
    override def canInteractWith(player: EntityPlayer) = true
	}

  //TODO: Fill Documentation
  /**
   *
   * @param inventoryPlayer
   * @param world
   * @param x
   * @param y
   * @param z
   */
  private[vanilla] class Gui(inventoryPlayer: InventoryPlayer, world: World,
			x: Int, y: Int, z: Int)
		extends GuiCrafting(inventoryPlayer, world, x, y, z)
	{
    //TODO: Fill Documentation
    /**
     *
     */
    inventorySlots = new Container(inventoryPlayer, world, x, y, z)

    //TODO: Fill Documentation
    /**
     *
     * @param arg1
     * @param arg2
     */
    override protected def drawGuiContainerForegroundLayer(arg1: Int, arg2: Int)
		{
			fontRendererObj.drawString(I18n.format("boatcraft.container.crafting", Array()), 28, 6, 4210752)
			fontRendererObj.drawString(I18n.format("container.inventory", Array()), 8, ySize - 96 + 2, 4210752)
		}
	}
}
