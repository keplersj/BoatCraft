package k2b6s9j.BoatCraft.core

import net.minecraft.item.ItemBoat
import net.minecraft.util._
import net.minecraft.inventory.IInventory
import java.lang.String
import net.minecraftforge.client.IItemRenderer
import net.minecraft.client.renderer.entity.RenderBoat
import net.minecraftforge.client.IItemRenderer.{ItemRendererHelper, ItemRenderType}
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Boat {

  class ItemCustomBoat extends ItemBoat

  case class EntityCustomBoat(world: World, x: Double, y: Double, z: Double) extends EntityBoat(world, x, y, z)

  class EntityBoatContainer(world2: World, x2: Double, y2: Double, z2: Double) extends EntityCustomBoat(world2, x2, y2, z2) with IInventory{

    def getSizeInventory: Int = 0

    def getStackInSlot(p1: Int): ItemStack = null

    def decrStackSize(p1: Int, p2: Int): ItemStack = null

    def getStackInSlotOnClosing(p1: Int): ItemStack = null

    def setInventorySlotContents(p1: Int, p2: ItemStack): Unit = {

    }

    def func_145825_b(): String = "something"

    def func_145818_k_(): Boolean = true

    def getInventoryStackLimit: Int = 0

    def onInventoryChanged(): Unit = {

    }

    def isUseableByPlayer(p1: EntityPlayer): Boolean = true

    def openChest(): Unit = {

    }

    def closeChest(): Unit = {

    }

    def isItemValidForSlot(p1: Int, p2: ItemStack): Boolean = true
  }

  class RenderCustomBoat extends RenderBoat with IItemRenderer {

    def doRender(p1: Entity, p2: Double, p3: Double, p4: Double, p5: Float, p6: Float): Unit = {

    }

    def getEntityTexture(p1: Entity): ResourceLocation = new ResourceLocation("textures/entity/boat.png")

    def handleRenderType(p1: ItemStack, p2: ItemRenderType): Boolean = true

    def shouldUseRenderHelper(p1: ItemRenderType, p2: ItemStack, p3: ItemRendererHelper): Boolean = true

    def renderItem(itemRenderType: ItemRenderType, itemStack: ItemStack, objects: AnyRef *): Unit = {

    }
  }

}