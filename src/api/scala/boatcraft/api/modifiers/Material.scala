package boatcraft.api.modifiers

import java.lang.reflect.Type
import java.util.{HashSet, Set}

import scala.collection.JavaConverters.iterableAsScalaIterableConverter

import com.google.gson.{JsonDeserializationContext, JsonDeserializer, JsonElement}

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraftforge.oredict.OreDictionary

class Material extends Modifier {
	
	private var parentMod = "FML"
	
	def getParentMod = parentMod
	
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
	def getItem =
		if (item != null)
			item.copy
		else
			null
	
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
	def getBrokenMaterialStack =
		if (brokenMaterialStack != null)
			brokenMaterialStack.copy
		else null
	
	private var specialAbilities: Set[String] = new HashSet[String]()
	
	def hasAbility(ability: String) = specialAbilities contains ability
}

object Material {
	
	object Deserializer extends JsonDeserializer[Material] {
		
		def deserialize(json: JsonElement, typeOfSrc: Type, context: JsonDeserializationContext): Material = {

			val result = new Material()
			
			val obj = json.getAsJsonObject
			
			if (obj.getAsJsonPrimitive("parentMod") != null) {
				result.parentMod = obj.getAsJsonPrimitive("parentMod").getAsString
			}

			if (obj.getAsJsonPrimitive("unlocalizedName") != null) {
				result.unlocalizedName = obj.getAsJsonPrimitive("unlocalizedName").getAsString
			}
			if (obj.getAsJsonPrimitive("localizedName") != null) {
				result.localizedName = obj.getAsJsonPrimitive("localizedName").getAsString
			}
			
			if (obj.getAsJsonObject("texture") != null) {
				val texture = obj.getAsJsonObject("texture")
				result.texture = new ResourceLocation(texture.getAsJsonPrimitive("mod").getAsString,
					texture.getAsJsonPrimitive("location").getAsString)
			}

			if (obj.getAsJsonObject("wholeMaterialStack") != null) {
				val wholeMaterialStack = obj.getAsJsonObject("wholeMaterialStack")
				val modOrigin = wholeMaterialStack.getAsJsonPrimitive("mod").getAsString
				val stackName = wholeMaterialStack.getAsJsonPrimitive("name").getAsString
				val metadata =
					if (wholeMaterialStack.getAsJsonPrimitive("metadata") != null && wholeMaterialStack.getAsJsonPrimitive("metadata").isNumber)
						wholeMaterialStack.getAsJsonPrimitive("metadata").getAsInt
					else 0

				result.item = GameRegistry.findItemStack(modOrigin, stackName, 1)
				if (result.item != null) result.item.setItemDamage(metadata)
			}
			
			if (obj.getAsJsonObject("brokenMaterialStack") != null) {
				val brokenMaterialStack = obj.getAsJsonObject("brokenMaterialStack")
				if (brokenMaterialStack != null) {
					val oreDictName = brokenMaterialStack.getAsJsonPrimitive("oreDictName")

					if (oreDictName != null) {
						if (OreDictionary.getOres(oreDictName.getAsString) isEmpty)
							result.brokenMaterialStack = null
						else
							result.brokenMaterialStack = OreDictionary.getOres(oreDictName.getAsString).get(0)
					}
					else if (brokenMaterialStack.getAsJsonPrimitive("mod") != null
							&& brokenMaterialStack.getAsJsonPrimitive("name") != null) {
						val modOrigin = brokenMaterialStack.getAsJsonPrimitive("mod").getAsString
						val stackName = brokenMaterialStack.getAsJsonPrimitive("name").getAsString
						val stackSize =
							if (brokenMaterialStack.getAsJsonPrimitive("amount") != null)
								brokenMaterialStack.getAsJsonPrimitive("amount").getAsInt
							else 1

						result.brokenMaterialStack = GameRegistry.findItemStack(modOrigin, stackName, stackSize)
					}
					else {
						result.brokenMaterialStack = null
					}
				}
			}
			
			if (obj.getAsJsonArray("specialAbilities") != null) {
				obj.getAsJsonArray("specialAbilities").asScala.foreach(elem => result.specialAbilities.add(elem.getAsString))
			}
			
			return result
		}
	}
}
