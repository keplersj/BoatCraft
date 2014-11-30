package boatcraft.compatibility

import net.minecraftforge.fml.common.Optional
import boatcraft.api.modifiers.Material
import boatcraft.compatibility.forestry.modifiers.materials.wood.MaterialForestryWood

object Forestry extends CompatModule {
	
	/*@Optional.Method(modid = "Forestry")
	override def getMaterials = MaterialForestryWood.values*/
}
