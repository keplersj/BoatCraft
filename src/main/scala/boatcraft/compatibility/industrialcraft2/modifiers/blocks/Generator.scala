package boatcraft.compatibility.industrialcraft2.modifiers.blocks

import boatcraft.api.boat.EntityCustomBoat
import boatcraft.api.modifiers.Block
import ic2.api.item.IC2Items
import ic2.core.block.generator.tileentity.TileEntityGenerator
import net.minecraft.item.ItemBlock
import net.minecraft.nbt.NBTTagCompound
import cpw.mods.fml.common.registry.GameRegistry

object Generator extends Block {
	GameRegistry.registerTileEntity(classOf[Inventory], "boatGenerator")
	
	override def getUnlocalizedName = "Generator"
	
	override def getLocalizedName = "industrialcraft2.blocks.generator.name"
	
	override def getContent = IC2Items.getItem("generator")
	
	override def getBlock = getContent.getItem.asInstanceOf[ItemBlock].field_150939_a
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new Inventory(boat)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getBlockDataWithType[Inventory] readFromNBT tag
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getBlockDataWithType[Inventory] writeToNBT tag

	private[industrialcraft2] class Inventory(boat: EntityCustomBoat) extends TileEntityGenerator {
		worldObj = boat.worldObj
	}

}