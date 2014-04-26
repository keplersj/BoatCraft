package k2b6s9j.boatcraft.test.api.registry

import scala.collection.JavaConversions._

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.test.api.traits.examples.ExampleMaterial
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class SingleMaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		Registry register ExampleMaterial
	}
	
	"A Material" should "be added to the registered Materials map." in
	{
		Registry.materials.toMap should contain(
				ExampleMaterial toString,
				ExampleMaterial)
	}
	
	it should "be returned when searched by name." in
	{
		Registry find ExampleMaterial.toString shouldBe ExampleMaterial
	}
	
	it should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(net.minecraft.init.Items.chainmail_helmet)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString ("material", ExampleMaterial toString)
		Registry getMaterial stack shouldBe ExampleMaterial
	}
	
	it should "fallback to a Vanilla Boat texture if no Material is defined." in
	{
		val stack = new ItemStack(net.minecraft.init.Items.chainmail_helmet)
		(Registry getMaterial stack) should have (
				'texture (new ResourceLocation("minecraft", "textures/entity/boat.png")))
	}

  after
  {
    Registry unregister ExampleMaterial
  }

}