package boatcraft.compatibility.industrialcraft.modifiers

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.traits.Modifier
import ic2.api.item.IC2Items
import ic2.core.block.generator.tileentity.TileEntityGenerator
import net.minecraft.inventory.IInventory
import net.minecraft.item.{ItemBlock, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.ForgeDirection

object Generator extends Modifier
{
	override def getName = "Generator"
	
	override def getContent = IC2Items.getItem("generator")
	
	override def getBlock = getContent.getItem.asInstanceOf[ItemBlock].field_150939_a
	
	override def getInventory(boat: EntityCustomBoat): IInventory = new Inventory(boat)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getInventory.asInstanceOf[Inventory] readFromNBT tag
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getInventory.asInstanceOf[Inventory] writeToNBT tag
	
	private[industrialcraft] class Inventory(boat: EntityCustomBoat) extends TileEntityGenerator
	{
		worldObj = boat.worldObj
	}
}