package k2b6s9j.boatcraft.test.api.registry

import scala.collection.JavaConversions._
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import k2b6s9j.boatcraft.api.registry.MaterialRegistry
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import java.util

@RunWith(classOf[JUnitRunner])
class MultipleMaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		MaterialRegistry addMaterials util.Arrays.asList(ExampleMaterial, ExampleMaterial2)
	}
	
	"All Materials" should "be added to the registered Materials map." in
	{
		MaterialRegistry.materials.toMap should contain("test", ExampleMaterial)
		MaterialRegistry.materials.toMap should contain("test2", ExampleMaterial2)
	}

	they should "be returned when searched by name." in
	{
		MaterialRegistry getMaterial "test" shouldBe ExampleMaterial
		MaterialRegistry getMaterial "test2" shouldBe ExampleMaterial2
	}

	they should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(net.minecraft.init.Items.chainmail_helmet)
		stack.stackTagCompound = new NBTTagCompound

		stack.stackTagCompound setString ("material", "test")
		MaterialRegistry getMaterial stack shouldBe ExampleMaterial

		stack.stackTagCompound setString ("material", "test2")
		MaterialRegistry getMaterial stack shouldBe ExampleMaterial2
	}

}
