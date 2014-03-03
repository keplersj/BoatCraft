package k2b6s9j.boatcraft.test.api.registry

import scala.collection.JavaConversions._
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.nbt.NBTTagCompound
import java.util
import k2b6s9j.boatcraft.api.Registry

@RunWith(classOf[JUnitRunner])
class MultipleMaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		Registry register util.Arrays.asList(ExampleMaterial, ExampleMaterial2)
	}
	
	"All Materials" should "be added to the registered Materials map." in
	{
		Registry.materials.toMap should contain(ExampleMaterial toString, ExampleMaterial)
		Registry.materials.toMap should contain(ExampleMaterial2 toString, ExampleMaterial2)
	}

	they should "be returned when searched by name." in
	{
		Registry find ExampleMaterial.toString shouldBe ExampleMaterial
		Registry find ExampleMaterial2.toString shouldBe ExampleMaterial2
	}

	they should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(Item.helmetChain)
		stack.stackTagCompound = new NBTTagCompound

		stack.stackTagCompound setString ("material", ExampleMaterial toString)
		Registry getMaterial stack shouldBe ExampleMaterial

		stack.stackTagCompound setString ("material", ExampleMaterial2 toString)
		Registry getMaterial stack shouldBe ExampleMaterial2
	}
	
	after
	{
		Registry unregister util.Arrays.asList(ExampleMaterial, ExampleMaterial2)
		
        Registry.materials.toMap should not contain(ExampleMaterial toString, ExampleMaterial)
        Registry.materials.toMap should not contain(ExampleMaterial2 toString, ExampleMaterial2)
	}

}
