package boatcraft.compatibility.vanilla.modifiers.blocks

import boatcraft.api.modifiers.Block
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.init.Items
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumParticleTypes

object TNT extends Block
{
	override def getBlock = Blocks.tnt.getDefaultState

	override def getUnlocalizedName: String = "TNT"

	override def getLocalizedName = "vanilla.blocks.tnt.name"

	override def getContent: ItemStack = new ItemStack(Blocks.tnt)
	
	override def getBlockData(boat: EntityCustomBoat): AnyRef = new TNTFuse

	override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
		if (player.getCurrentEquippedItem != null
			&& player.getCurrentEquippedItem.getItem == Items.flint_and_steel) {
			boat.getBlockDataWithType[TNTFuse].fuse = 80
			player.getCurrentEquippedItem.damageItem(1, player)
			
			true
		}
		else false

	override def update(boat: EntityCustomBoat)
	{
		var data = boat.getBlockData.asInstanceOf[TNTFuse]
		
		if (data.fuse == -1)
		{
			//Needs to be less, because the boat is about to be destroyed
			if (boat isBurning) data.fuse = 20
			else return
		}
		
		if (data.fuse == 0)
		{
			boat.setDead()
			boat.worldObj.createExplosion(boat, boat.posX, boat.posY, boat.posZ, 4, true)
		}
		else
		{
			data.fuse -= 1
			boat.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, boat.posX, boat.posY + 0.5, boat.posZ, 0, 0, 0)
		}
	}
	
	override def writeStateToNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		tag.setByte("Fuse", boat.getBlockDataWithType[TNTFuse].fuse.toByte)
	
	override def readStateFromNBT(boat: EntityCustomBoat, tag: NBTTagCompound) =
		boat.getBlockDataWithType[TNTFuse].fuse = tag.getByte("Fuse")
	
	private class TNTFuse {
		private[TNT] var fuse = -1
	}
}