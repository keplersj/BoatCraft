package boatcraft.compatibility.vanilla.blocks

import boatcraft.api.traits.Block
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Items

object TNT extends Block {
    
    override def getBlock = Blocks.tnt

    override def getName: String = "TNT"

    override def getContent: ItemStack = new ItemStack(Blocks.tnt)
    
    override def getBlockData(boat: EntityCustomBoat): AnyRef = new TNTFuse

    override def interact(player: EntityPlayer, boat: EntityCustomBoat) {
        if (player.getCurrentEquippedItem().getItem() == Items.flint_and_steel
        	|| player.getCurrentEquippedItem().getItem() == Items.fire_charge) {
            boat.getBlockData.asInstanceOf[TNTFuse].fuse = 80
        }
    }

    override def update(boat: EntityCustomBoat) {
        var data = boat.getBlockData.asInstanceOf[TNTFuse]
        if (data.fuse > 0) boat.getBlockData.asInstanceOf[TNTFuse].fuse -= 1
        else {
        	boat.setDead()
        	boat.worldObj.createExplosion(boat, boat.posX, boat.posY, boat.posZ, 4, true)
        }
    }
    
    private class TNTFuse {
    	private[TNT] var fuse = -1
    }
}