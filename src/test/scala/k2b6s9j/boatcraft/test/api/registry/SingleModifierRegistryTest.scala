package k2b6s9j.boatcraft.test.api.registry

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import scala.collection.JavaConversions._
import k2b6s9j.boatcraft.core.materials.Empty

@RunWith(classOf[JUnitRunner])
class SingleModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		ModifierRegistry addModifier ExampleModifier
	}

	"A Modifier" should "be added to the registered Modifiers map." in
	{
		ModifierRegistry.modifiers.toMap should contain("test", ExampleModifier)
	}

	it should "be returned when searched by name." in
	{
		ModifierRegistry getModifier "test" shouldBe ExampleModifier
	}

	it should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(Blocks.bedrock)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString ("modifier", "test")
		ModifierRegistry getModifier stack shouldBe ExampleModifier
	}

  it should "fallback to an Empty modifier if no modifier is defined." in {
    val stack = new ItemStack(Blocks.bedrock)
    ModifierRegistry getModifier stack shouldBe Empty
  }

}