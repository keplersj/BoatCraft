package k2b6s9j.boatcraft.test.api.registry

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import scala.collection.JavaConversions._
import k2b6s9j.boatcraft.core.materials.Empty
import k2b6s9j.boatcraft.api.Registry
import net.minecraft.block.Block

@RunWith(classOf[JUnitRunner])
class SingleModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		Registry register ExampleModifier
	}

	"A Modifier" should "be added to the registered Modifiers map." in
	{
		Registry.modifiers.toMap should contain(ExampleModifier toString, ExampleModifier)
	}

	it should "be returned when searched by name." in
	{
		Registry find ExampleModifier.toString shouldBe ExampleModifier
	}

	it should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(Block.bedrock)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString ("modifier", ExampleModifier toString)
		Registry getModifier stack shouldBe ExampleModifier
	}

	it should "fallback to an Empty modifier if no modifier is defined." in
	{
		val stack = new ItemStack(Block.bedrock)
		Registry getModifier stack shouldBe Empty
	}

	after
	{
		Registry unregister ExampleModifier
		
        Registry.modifiers.toMap should not contain(ExampleModifier toString, ExampleModifier)
	}
}
