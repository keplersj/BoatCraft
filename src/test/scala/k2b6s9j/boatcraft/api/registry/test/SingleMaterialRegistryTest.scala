package k2b6s9j.boatcraft.api.registry.test

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import k2b6s9j.boatcraft.api.registry.MaterialRegistry
import k2b6s9j.boatcraft.api.traits.examples._

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

import scala.collection.JavaConversions._
import java.util

@RunWith(classOf[JUnitRunner])
class SingleMaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    MaterialRegistry addMaterial ExampleMaterial
  }

  "A Material" should "be added to the registered Materials map." in {
    MaterialRegistry.materials.asInstanceOf[util.HashMap[AnyRef, AnyRef]].toMap should contain ("test", ExampleMaterial)
  }

  it should "be returned when searched by name." in {
    MaterialRegistry getMaterial "test" shouldBe ExampleMaterial
  }

  it should "be returned when searched by ItemStack." in {
    val stack = new ItemStack(net.minecraft.init.Items.chainmail_helmet)
    stack.stackTagCompound = new NBTTagCompound
    stack.stackTagCompound setString("material", "test")
    MaterialRegistry getMaterial stack shouldBe ExampleMaterial
  }

}
