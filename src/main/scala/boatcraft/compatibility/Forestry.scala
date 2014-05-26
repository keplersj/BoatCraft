package boatcraft.compatibility

import boatcraft.api.modifiers.Material
import boatcraft.compatibility.forestry.modifiers.materials.wood.MaterialForestryWood

object Forestry extends CompatModule {
	
	override def getMaterials = MaterialForestryWood.values
}