package boatcraft.compatibility

import cpw.mods.fml.common.Optional
import boatcraft.api.modifiers.Material
import boatcraft.compatibility.forestry.modifiers.materials.wood.MaterialForestryWood

object Forestry extends CompatModule("Forestry") {
	
	@Optional.Method(modid = "Forestry")
	override def getMaterials = MaterialForestryWood.values
}
