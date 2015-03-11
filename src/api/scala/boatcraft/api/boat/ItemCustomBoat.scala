package boatcraft.api.boat

import java.util

import scala.collection.JavaConversions.mapAsScalaMap

import boatcraft.api.Registry
import boatcraft.api.modifiers.Block
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemBoat
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.AxisAlignedBB
import net.minecraft.util.MathHelper
import net.minecraft.util.MovingObjectPosition
import net.minecraft.util.StatCollector
import net.minecraft.util.Vec3
import net.minecraft.world.World

/**
 * The Item Class used for all items that can be deployed like a Boat.
 * Extends ItemBoat from Vanilla Minecraft.
 */
class ItemCustomBoat extends ItemBoat {
	import ItemCustomBoat._
	
	hasSubtypes = true

	@SideOnly(Side.CLIENT)
	override def getSubItems(item: Item, tab: CreativeTabs, list: util.List[_]) {
		val stack = new ItemStack(item)
		stack.stackTagCompound = new NBTTagCompound
		for ((nameMat, material) <- Registry.materials) {
			stack.stackTagCompound setString("material", nameMat)
			for ((nameBlock, block) <- Registry.blocks) {
				stack.stackTagCompound setString("block", nameBlock)
				list.asInstanceOf[util.List[ItemStack]] add stack.copy
			}
		}
	}
	
	override def getUnlocalizedName(stack: ItemStack) =
		"boat." +
			Registry.getMaterial(stack) +
			"." +
			Registry.getBlock(stack)
	
	override def getItemStackDisplayName(stack: ItemStack): String = stack match {
		case x if Registry.getBlock(stack) == Block.Empty =>
			//StatCollector.translateToLocal(Registry.getMaterial(stack).getLocalizedName) + " " + StatCollector.translateToLocal("core.forms.dinghy.name")
			StatCollector.translateToLocalFormatted(
					transform(StatCollector.translateToLocal("core.forms.dinghy.empty.format")),
					StatCollector.translateToLocal(Registry.getMaterial(stack).getLocalizedName),
					StatCollector.translateToLocal("core.forms.dinghy.name"))
		case x if Registry.getBlock(stack) != null =>
			//StatCollector.translateToLocal(Registry.getMaterial(stack).getLocalizedName) + " " + StatCollector.translateToLocal("core.forms.dinghy.name") +	" " + StatCollector.translateToLocal("core.module.linkingword") + " " + StatCollector.translateToLocal(Registry.getBlock(stack).getLocalizedName)
			StatCollector.translateToLocalFormatted(
					transform(StatCollector.translateToLocal("core.forms.dinghy.format")),
					StatCollector.translateToLocal(Registry.getMaterial(stack).getLocalizedName),
					StatCollector.translateToLocal("core.forms.dinghy.name"),
					StatCollector.translateToLocal("core.module.linkingword"),
					StatCollector.translateToLocal(Registry.getBlock(stack).getLocalizedName))
		case _ =>
			StatCollector.translateToLocal("core.forms.dinghy.name")
	}
	
	override def onItemRightClick(stack: ItemStack, world: World, player: EntityPlayer): ItemStack = {
		val f = 1.0F
		val f1 = player.prevRotationPitch +
			(player.rotationPitch - player.prevRotationPitch) * f
		val f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f
		val d0 = player.prevPosX + (player.posX - player.prevPosX) * f.asInstanceOf[Double]
		val d1 = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset
		val d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f.asInstanceOf[Double]
		val vec3 = Vec3.createVectorHelper(d0, d1, d2)
		val f3 = MathHelper.cos(-f2 * 0.017453292F - Math.PI.asInstanceOf[Float])
		val f4 = MathHelper.sin(-f2 * 0.017453292F - Math.PI.asInstanceOf[Float])
		val f5 = -MathHelper.cos(-f1 * 0.017453292F)
		val f6 = MathHelper.sin(-f1 * 0.017453292F)
		val f7 = f4 * f5
		val f8 = f3 * f5
		val d3 = 5.0
		val vec31: Vec3 = vec3.addVector(f7.toDouble * d3,
			f6 * d3,
			f8 * d3)
		val movingobjectposition: MovingObjectPosition =
			world rayTraceBlocks(vec3, vec31, true)
		if (movingobjectposition == null)
			return stack
		else {
			val vec32: Vec3 = player getLook f
			var flag: Boolean = false
			val f9: Float = 1.0F
			val list: util.List[_] = world.getEntitiesWithinAABBExcludingEntity(player,
				player.boundingBox addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3)
					expand(f9.asInstanceOf[Double], f9.asInstanceOf[Double], f9.asInstanceOf[Double]))
			var i: Int = 0
			for (i <- 0 until list.size()) {
				val entity: Entity = list.get(i).asInstanceOf[Entity]

				if (entity.canBeCollidedWith) {
					val f10: Float = entity.getCollisionBorderSize
					val axisalignedbb: AxisAlignedBB = entity.boundingBox.
						expand(f10 toDouble, f10 toDouble, f10 toDouble)

					if (axisalignedbb isVecInside vec3)
						flag = true
				}
			}

			if (flag) return stack
			else {
				if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					i = movingobjectposition.blockX
					var j = movingobjectposition.blockY
					val k = movingobjectposition.blockZ

					if (world.getBlock(i, j, k) == Blocks.snow_layer)
						j = j - 1

					var boat: EntityCustomBoat = null
					val material = Registry getMaterial stack
					val block = Registry getBlock stack

					boat = EntityCustomBoat(world, i + 0.5, j + 1.0, k + 0.5)
					boat setMaterial (material toString)

					boat setBlock (block toString)
					boat.rotationYaw =
						((MathHelper.floor_double(player.rotationYaw * 4.0 / 360.0 + 0.5) & 3) - 1) * 90

					if (!world.getCollidingBoundingBoxes(boat,
						boat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty)
						return stack

					if (!world.isRemote)
						world spawnEntityInWorld boat

					if (!player.capabilities.isCreativeMode)
						stack.stackSize = stack.stackSize - 1
				}
				return stack
			}
		}
	}
}

object ItemCustomBoat extends ItemCustomBoat
{
	private def transform(str: String): String =
			str.replace("_mat_", "%1$s")
				.replace("_form_", "%2$s")
				.replace("_link_", "%3$s")
				.replace("_block_", "%4$s")
}
