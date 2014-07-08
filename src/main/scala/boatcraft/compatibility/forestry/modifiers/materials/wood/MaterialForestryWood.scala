package boatcraft.compatibility.forestry.modifiers.materials.wood

import boatcraft.compatibility.vanilla.modifiers.materials.wood.MaterialWood
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
/*import forestry.core.config.ForestryBlock
import forestry.arboriculture.WoodType*/
import java.util.ArrayList
import boatcraft.api.modifiers.Material

class MaterialForestryWood(meta: Int, name: String, localizedName: String)
//	extends MaterialWood(meta, name, localizedName)
{
/*	
	override def getTexture =
		new ResourceLocation("boatcraft",
			"textures/entity/boat/forestry/wood/" +
				name.toLowerCase.replace(' ', '_').replace("_wood", "") + ".png")
	
	override def getItem =
	  	if (meta < 16) new ItemStack(ForestryBlock.planks1, 1, meta)
	  	else new ItemStack(ForestryBlock.planks2, 1, meta & 15)*/
}

object MaterialForestryWood {
	
	final val values = null//transform(WoodType.values)
	
	/*private def transform(types: Array[WoodType]): Array[Material] = {
		var values = new ArrayList[MaterialForestryWood]
		
		for (value <- types if value hasPlank)
			values.add(new MaterialForestryWood(
					value.ordinal,
					value.name,
					"forestry.materials.wood." + value.name.toLowerCase + ".name"))
		
		return values.toArray(Array[Material]())
	}*/
}