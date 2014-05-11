package boatcraft.compatibility.vanilla.modifiers.materials.wood

import boatcraft.api.modifiers.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.Blocks
import net.minecraft.init.Items
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.DamageSource

object OreDict_Wood extends Material {
	override def getTexture =
		new ResourceLocation("minecraft", "textures/entity/boat.png")

	override def getUnlocalizedName = "Wood"

	override def getLocalizedName = "vanilla.materials.wood.oredict.name"

	override def getItem = new ItemStack(Blocks.planks)

	override def getStick = new ItemStack(Items.stick)
    
    override def interact(player: EntityPlayer, boat: EntityCustomBoat) =
        if (player.getCurrentEquippedItem != null &&
            player.getCurrentEquippedItem.getItem == Items.flint_and_steel)
        {
            boat.setFire(8)
            
            true
        }
        else false
    
    override def update(boat: EntityCustomBoat)
    {
        if (boat isBurning) boat.attackEntityFrom(DamageSource.onFire, 0.2f)
    }
}