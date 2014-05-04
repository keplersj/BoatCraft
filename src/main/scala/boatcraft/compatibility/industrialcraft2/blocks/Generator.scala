package boatcraft.compatibility.industrialcraft2.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.traits.Block
import ic2.api.item.IC2Items
import ic2.core.block.generator.tileentity.TileEntityGenerator
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemBlock
import net.minecraft.nbt.NBTTagCompound

object Generator extends Block {
	override def getName = "Generator"

	override def getContent = IC2Items.getItem("generator")

	override def getBlock = getContent.getItem.asInstanceOf[ItemBlock].field_150939_a

	override def getInventory(boat: EntityCustomBoat): IInventory = new Inventory(boat)

	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getInventory.asInstanceOf[Inventory] readFromNBT tag

	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getInventory.asInstanceOf[Inventory] writeToNBT tag

	private[industrialcraft2] class Inventory(boat: EntityCustomBoat) extends TileEntityGenerator {
		worldObj = boat.worldObj
	}

}