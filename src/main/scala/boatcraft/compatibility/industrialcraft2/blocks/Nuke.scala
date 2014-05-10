package boatcraft.compatibility.industrialcraft2.blocks

import boatcraft.api.modifiers.Modifier
import ic2.api.item.IC2Items
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Items
import net.minecraft.init.Blocks
import boatcraft.api.modifiers.Block
import ic2.core.block.machine.tileentity.TileEntityNuke
import net.minecraft.nbt.NBTTagCompound
import boatcraft.compatibility.IC2
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.tileentity.TileEntity
import cpw.mods.fml.common.network.internal.FMLNetworkHandler

object Nuke extends Block {
	TileEntity.addMapping(classOf[NukeFuse], "boatNuke")
	
	override def getBlock = IC2Items.getItem("nuke").getItem.asInstanceOf[ItemBlock].field_150939_a

	override def getUnlocalizedName = "Nuke"

	override def getLocalizedName = "industrialcraft2.blocks.nuke.name"

	override def getContent: ItemStack = IC2Items.getItem("nuke")
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new NukeFuse(boat)

	override def interact(player: EntityPlayer, boat: EntityCustomBoat) = {
		if (player.getCurrentEquippedItem != null
			&& (player.getCurrentEquippedItem.getItem == Items.flint_and_steel
				|| player.getCurrentEquippedItem.getItem == Items.fire_charge)) {
			boat.getBlockData.asInstanceOf[NukeFuse].fuse = 300
		}
		else if (player.isInstanceOf[EntityPlayerMP]) {
			IC2.log info "Sent GUI request for GUI " + ((IC2.code << 6) | 1) + " and entity " + boat.getEntityId
			player.openGui("boatcraft", (IC2.code << 6) | 1, boat.worldObj, boat.getEntityId, -1, 0)
		}
		
		true
	}

	override def update(boat: EntityCustomBoat) {
		var data = boat.getBlockData.asInstanceOf[NukeFuse]
		
		if (data.fuse == -1) return
		
		if (data.fuse == 0) {
			boat.setDead
			boat.worldObj.createExplosion(boat, boat.posX, boat.posY, boat.posZ, data.getNukeExplosivePower, true)
		}
		else {
			data.fuse -= 1
			boat.worldObj.spawnParticle("smoke", boat.posX, boat.posY + 0.5, boat.posZ, 0, 0, 0)
		}
	}
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getBlockData.asInstanceOf[NukeFuse].writeToNBT(tag)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getBlockData.asInstanceOf[NukeFuse].readFromNBT(tag)
	
	private class NukeFuse(boat: EntityCustomBoat) extends TileEntityNuke {
		worldObj = boat.worldObj
		var fuse = -1
		
		override def getInventoryName() = "Nuked Boat"
	}
}