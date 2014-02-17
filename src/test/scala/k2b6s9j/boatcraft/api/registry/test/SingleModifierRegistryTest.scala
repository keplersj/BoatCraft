package k2b6s9j.boatcraft.api.registry.test

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.api.traits.examples._

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

import scala.collection.JavaConversions._
import java.util

@RunWith(classOf[JUnitRunner])
class SingleModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    ModifierRegistry addModifier ExampleModifier
  }

  "A Modifier" should "be added to the registered Modifiers map." in {
    ModifierRegistry.modifiers.asInstanceOf[util.HashMap[AnyRef, AnyRef]].toMap should contain ("test", ExampleModifier)
  }

  it should "be returned when searched by name." in {
    ModifierRegistry getModifier "test" shouldBe ExampleModifier
  }

  it should "be returned when searched by ItemStack." in {
    val stack = new ItemStack(net.minecraft.init.Blocks.bedrock)
    stack.stackTagCompound = new NBTTagCompound
    stack.stackTagCompound setString("modifier", "test")
    ModifierRegistry getModifier stack shouldBe ExampleModifier
  }

}
