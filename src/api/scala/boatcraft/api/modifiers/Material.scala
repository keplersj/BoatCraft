package boatcraft.api.modifiers

import java.lang.reflect.Type
import java.util.HashSet
import java.util.Set
import java.util.function.Consumer
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.oredict.OreDictionary

class Material extends Modifier {
	
	private var unlocalizedName: String = null
	
	override def getUnlocalizedName = unlocalizedName
	
	private var localizedName: String = null
	
	override def getLocalizedName = localizedName
	
	private var texture: ResourceLocation = null	
	/**
	 * The texture path for rendering the Boat in the world
	 *
	 * @return base texture of the Material
	 */
	def getTexture = new ResourceLocation(texture.getResourceDomain, texture.getResourcePath)
	
	private var item: ItemStack = null
	
	/**
	 * The item used in the crafting recipe.
	 * Also the primary drop when the boat crashes
	 *
	 * @return the ItemStack representing the Material
	 */
	def getItem = item.copy
	
	private var brokenMaterialStack: ItemStack = null
	
	/**
	 * The secondary drop when the boat crashes
	 * For Wood-based boats, this is the associated wood's sticks.
	 * For Metallic boats, this is the nugget form of the metal.
	 * For other Material types is either null or the small tier of the base material
	 * (ie: stone rods, diamond nuggets, flint, etc)
	 *
	 * @return the secondary drop of the boat
	 */
	def getBrokenMaterialStack = brokenMaterialStack.copy
	
	private var specialAbilities: Set[String] = new HashSet[String]()
	
	def hasAbility(ability: String) = specialAbilities contains ability
}

object Material {
	
	object Deserializer extends JsonDeserializer[Material] {
		
		def deserialize(json: JsonElement, typeOfSrc: Type, context: JsonDeserializationContext): Material = {

			val result = new Material()
			
			val obj = json.getAsJsonObject
			
			result.unlocalizedName = obj.getAsJsonPrimitive("unlocalizedName").getAsString
			result.localizedName = obj.getAsJsonPrimitive("localizedName").getAsString
			
			val texture = obj.getAsJsonObject("texture")
			result.texture = new ResourceLocation(texture.getAsJsonPrimitive("mod").getAsString,
													texture.getAsJsonPrimitive("location").getAsString)

			val wholeMaterial = obj.get("wholeMaterialStack")
			val stack = wholeMaterial.getAsJsonObject
			val modOrigin = stack.getAsJsonPrimitive("mod").getAsString
			val stackName = stack.getAsJsonPrimitive("name").getAsString
			val metadata =
				if (stack.getAsJsonPrimitive("metadata") != null && stack.getAsJsonPrimitive("metadata").isNumber)
					stack.getAsJsonPrimitive("metadata").getAsInt
				else 0
			result.item = GameRegistry.findItemStack(modOrigin, stackName, 1)
			result.item.setItemDamage(metadata)
			
			val brokenMaterialStack = obj.get("brokenMaterialStack")
			if (brokenMaterialStack != null && brokenMaterialStack.isJsonObject) {
				val stack = brokenMaterialStack.getAsJsonObject
				val oreDictName = stack.get("oreDictName")
				if (oreDictName != null && oreDictName.isJsonPrimitive)
					result.brokenMaterialStack = OreDictionary.getOres(oreDictName.getAsString).get(0)
				else {
					val modOrigin = stack.getAsJsonPrimitive("mod").getAsString
					val stackName = stack.getAsJsonPrimitive("name").getAsString
					val stackSize =
						if (stack.getAsJsonPrimitive("amount") != null && stack.getAsJsonPrimitive("amount").isNumber)
							stack.getAsJsonPrimitive("amount").getAsInt
						else 1
					result.brokenMaterialStack = GameRegistry.findItemStack(modOrigin, stackName, stackSize)
				}
			}
			
			val specialAbilities = obj.get("specialAbilities")
			if (specialAbilities != null && specialAbilities.isJsonArray)
				specialAbilities.getAsJsonArray.forEach(new Consumer[JsonElement]()
					{
						def accept(elem: JsonElement) {
							result.specialAbilities add elem.getAsString
						}
					})
				
			
			return result
		}
	}
}