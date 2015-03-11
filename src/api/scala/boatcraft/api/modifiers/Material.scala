package boatcraft.api.modifiers

import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import com.google.gson.JsonSerializer
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonElement
import java.lang.reflect.Type
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import cpw.mods.fml.common.registry.GameRegistry
import com.google.gson.JsonDeserializationContext
import net.minecraft.item.Item

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
			
			var result = new Material()
			
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
			
			val stick = obj.get("stick")
			if (stick isJsonObject) {
				
				val item = stick.getAsJsonObject
				val name = item.getAsJsonPrimitive("mod").getAsString +
						":" + item.getAsJsonPrimitive("name").getAsString
				result.stick = new ItemStack(Item.itemRegistry.getObject(name).asInstanceOf[Item])
				result.stick.setItemDamage(item.getAsJsonPrimitive("metadata").getAsInt)
			}
			
			val fireResist = obj.get("fireResist")
			if (fireResist isJsonPrimitive)
				result.fireResist = fireResist.getAsBoolean
			
			return result;
		}
	}
}