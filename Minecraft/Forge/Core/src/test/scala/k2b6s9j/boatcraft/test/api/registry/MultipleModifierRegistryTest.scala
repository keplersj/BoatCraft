package k2b6s9j.boatcraft.test.api.registry

import java.util
import scala.collection.JavaConversions._
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
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
		Registry.modifiers.toMap should contain(ExampleModifier toString, ExampleModifier)
		Registry.modifiers.toMap should contain(ExampleModifier2 toString, ExampleModifier2)
	}
	
	they should "be returned when searched by name." in
	{
		Registry find ExampleModifier.toString shouldBe ExampleModifier
		Registry find ExampleModifier2.toString shouldBe ExampleModifier2
	}
	
	they should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(net.minecraft.init.Blocks.bedrock)
		stack.stackTagCompound = new NBTTagCompound
		
		stack.stackTagCompound setString ("modifier", ExampleModifier toString)
		Registry getModifier stack shouldBe ExampleModifier

		stack.stackTagCompound setString ("modifier", ExampleModifier2 toString)
		Registry getModifier stack shouldBe ExampleModifier2
	}
	
	after
	{
		Registry unregister util.Arrays.asList(ExampleModifier, ExampleModifier2)
		
        Registry.modifiers.toMap should not contain(ExampleModifier toString, ExampleModifier)
        Registry.modifiers.toMap should not contain(ExampleModifier2 toString, ExampleModifier2)
	}
}
