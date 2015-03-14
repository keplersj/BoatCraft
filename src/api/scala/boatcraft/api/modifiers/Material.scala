package boatcraft.api.modifiers

import java.lang.reflect.Type

import com.google.gson.{JsonDeserializationContext, JsonDeserializer, JsonElement}
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.ResourceLocation

class Material extends Modifier {
	
	private var displayName: String = null
	
	override def getLocalizedName = displayName
	
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
	
	override def getUnlocalizedName = item.getUnlocalizedName
	
	private var stick: ItemStack = null
	
	/**
	 * The secondary drop when the boat crashes
	 * For Wood-based boats, this is the associated wood's sticks.
	 * For Metallic boats, this is the nugget form of the metal.
	 * For other Material types is either null or the small tier of the base material
	 * (ie: stone rods, diamond nuggets, flint, etc)
	 *
	 * @return the secondary drop of the boat
	 */
	def getStick = stick.copy
	
	private var fireResist = false
	
	def isFireResist = fireResist
}

object Material {
	
	class Deserializer extends JsonDeserializer[Material] {
		
		def deserialize(json: JsonElement, typeOfSrc: Type, context: JsonDeserializationContext): Material = {

			val result = new Material()
			
			val obj = json.getAsJsonObject
			
			result.displayName = obj.getAsJsonPrimitive("displayname").getAsString
			
			val item = obj.getAsJsonObject("item")
			val name = item.getAsJsonPrimitive("mod").getAsString +
					":" + item.getAsJsonPrimitive("name").getAsString
			result.item = new ItemStack(Item.itemRegistry.getObject(name).asInstanceOf[Item])
			result.item.setItemDamage(item.getAsJsonPrimitive("metadata").getAsInt)
			
			val texture = obj.getAsJsonObject("texture")
			result.texture = new ResourceLocation(texture.getAsJsonPrimitive("mod").getAsString,
													texture.getAsJsonPrimitive("location").getAsString)

			val wholeMaterial = obj.get("wholeMaterialStack")
			if (wholeMaterial.isJsonObject) {
				val stack = wholeMaterial.getAsJsonObject
				val modOrigin = stack.getAsJsonPrimitive("mod").getAsString
				val stackName = stack.getAsJsonPrimitive("name").getAsString
				result.item = GameRegistry.findItemStack(modOrigin, stackName, 1)
			}
			
			val stick = obj.get("brokenMaterialStack")
			if (stick isJsonObject) {
				val stack = stick.getAsJsonObject
				val modOrigin = stack.getAsJsonPrimitive("mod").getAsString
				val stackName = stack.getAsJsonPrimitive("name").getAsString
				val stackSize = stack.getAsJsonPrimitive("amount").getAsInt
				result.stick = GameRegistry.findItemStack(modOrigin, stackName, stackSize)
			}
			
			val fireResist = obj.get("fireResist")
			if (fireResist isJsonPrimitive)
				result.fireResist = fireResist.getAsBoolean
			
			result
		}
	}
}