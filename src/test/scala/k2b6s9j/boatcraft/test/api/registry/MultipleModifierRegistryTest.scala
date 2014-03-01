package k2b6s9j.boatcraft.test.api.registry

import java.util
import scala.collection.JavaConversions._
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import k2b6s9j.boatcraft.api.Registry

@RunWith(classOf[JUnitRunner])
class MultipleModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		Registry register util.Arrays.asList(ExampleModifier, ExampleModifier2)
	}
	
	"All Modifiers" should "be added to the registered Modifiers map." in
	{
		Registry.modifiers.toMap should contain("test", ExampleModifier)
		Registry.modifiers.toMap should contain("test2", ExampleModifier2)
	}
	
	they should "be returned when searched by name." in
	{
		Registry getModifier "test" shouldBe ExampleModifier
		Registry getModifier "test2" shouldBe ExampleModifier2
	}
	
	they should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(net.minecraft.init.Blocks.bedrock)
		stack.stackTagCompound = new NBTTagCompound
		
		stack.stackTagCompound setString ("modifier", "test")
		Registry getModifier stack shouldBe ExampleModifier

		stack.stackTagCompound setString ("modifier", "test2")
		Registry getModifier stack shouldBe ExampleModifier2
	}
}
