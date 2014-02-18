package k2b6s9j.boatcraft.api.registry.test

import java.util
import java.util.Arrays

import scala.collection.JavaConversions.mapAsScalaMap

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.test.api.traits.examples.{ExampleModifier, ExampleModifier2}
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

@RunWith(classOf[JUnitRunner])
class MultipleModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter
{
	before
	{
		ModifierRegistry addModifiers (Arrays.asList(ExampleModifier, ExampleModifier2))
	}
	
	"All Modifiers" should "be added to the registered Modifiers map." in
	{
		ModifierRegistry.modifiers.asInstanceOf[util.HashMap[AnyRef, AnyRef]].toMap should contain("test", ExampleModifier)
		ModifierRegistry.modifiers.asInstanceOf[util.HashMap[AnyRef, AnyRef]].toMap should contain("test2", ExampleModifier2)
	}
	
	they should "be returned when searched by name." in
	{
		ModifierRegistry getModifier "test" shouldBe ExampleModifier
		ModifierRegistry getModifier "test2" shouldBe ExampleModifier2
	}
	
	they should "be returned when searched by ItemStack." in
	{
		val stack = new ItemStack(net.minecraft.init.Blocks.bedrock)
		stack.stackTagCompound = new NBTTagCompound
		
		stack.stackTagCompound setString ("modifier", "test")
		ModifierRegistry getModifier stack shouldBe ExampleModifier

		stack.stackTagCompound setString ("modifier", "test2")
		ModifierRegistry getModifier stack shouldBe ExampleModifier2
	}
}
