package boatcraft.compatibility.vanilla.modifiers.materials.wood

import boatcraft.api.modifiers.Material
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import boatcraft.api.boat.EntityCustomBoat
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.DamageSource

class MaterialWood(meta: Int, name: String, localizedName: String) extends Material {
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/vanilla/wood/" +
				name.toLowerCase.replace(' ', '_').replace("_wood", "") + ".png")

	override def getUnlocalizedName = name

	override def getLocalizedName = localizedName

	override def getItem = new ItemStack(Blocks.planks, 1, meta)

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