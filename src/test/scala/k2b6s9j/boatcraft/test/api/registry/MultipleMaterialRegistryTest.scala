package k2b6s9j.boatcraft.test.api.registry

import java.util
import scala.collection.JavaConversions.mapAsScalaMap
import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, Finders, FlatSpec, Matchers}
import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultipleMaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		Registry register util.Arrays.asList(ExampleMaterial, ExampleMaterial2)
	}
	
	"All Materials" should "be added to the registered Materials map." in
	{
		Registry.materials.toMap should contain("test", ExampleMaterial)
		Registry.materials.toMap should contain("test2", ExampleMaterial2)
	}

	they should "be returned when searched by name." in
	{
		Registry find "test" shouldBe ExampleMaterial
		Registry find "test2" shouldBe ExampleMaterial2
	}

	they should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(net.minecraft.init.Items.chainmail_helmet)
		stack.stackTagCompound = new NBTTagCompound

		stack.stackTagCompound setString ("material", "test")
		Registry getMaterial stack shouldBe ExampleMaterial

		stack.stackTagCompound setString ("material", "test2")
		Registry getMaterial stack shouldBe ExampleMaterial2
	}

  after
  {
    Registry unregister util.Arrays.asList(ExampleMaterial, ExampleMaterial2)
  }

}
