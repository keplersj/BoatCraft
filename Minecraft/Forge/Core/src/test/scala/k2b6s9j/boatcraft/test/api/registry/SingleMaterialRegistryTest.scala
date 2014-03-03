package k2b6s9j.boatcraft.test.api.registry

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import scala.collection.JavaConversions._
import net.minecraft.util.ResourceLocation
import k2b6s9j.boatcraft.api.Registry

@RunWith(classOf[JUnitRunner])
class SingleMaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		Registry register ExampleMaterial
	}
	
	"A Material" should "be added to the registered Materials map." in
	{
		Registry.materials.toMap should contain(ExampleMaterial toString, ExampleMaterial)
	}
	
	it should "be returned when searched by name." in
	{
		Registry find ExampleMaterial.toString shouldBe ExampleMaterial
	}
	
	it should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(Item.helmetChain)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString ("material", ExampleMaterial toString)
		Registry getMaterial stack shouldBe ExampleMaterial
	}
	
	it should "fallback to a Vanilla Boat texture if no Material is defined." in
	{
		val stack = new ItemStack(Item.helmetChain)
		(Registry getMaterial stack) should have (
				'texture (new ResourceLocation("minecraft", "textures/entity/boat.png")))
	}
	
	after
	{
		Registry unregister ExampleMaterial
		
        Registry.materials.toMap should not contain(ExampleMaterial toString, ExampleMaterial)
	}

}
