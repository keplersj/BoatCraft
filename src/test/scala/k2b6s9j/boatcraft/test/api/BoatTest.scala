package k2b6s9j.boatcraft.test.api

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import k2b6s9j.boatcraft.api.Boat
import k2b6s9j.boatcraft.api.registry._
import k2b6s9j.boatcraft.test.api.traits.examples._

object BoatTest
{

  @RunWith(classOf[JUnitRunner])
  class ItemCustomBoatTest extends FlatSpec with Matchers with BeforeAndAfter
  {
    var item: Boat.ItemCustomBoat = null

    before
    {
      item = new Boat.ItemCustomBoat
      MaterialRegistry addMaterial ExampleMaterial
      ModifierRegistry addModifier ExampleModifier
    }

    "A Boat Item" should "have an unlocalized name based on it's Material and Modifier." in
    {
      val stack = new ItemStack(Items.chainmail_helmet)
      stack.stackTagCompound = new NBTTagCompound
      stack.stackTagCompound setString ("material", "test")
      stack.stackTagCompound setString ("modifier", "test")
      item.getUnlocalizedName(stack) shouldBe "boat.test.test"
    }
  }

  @RunWith(classOf[JUnitRunner])
  class EntityCustomBoatTest extends FlatSpec with Matchers with BeforeAndAfter
  {

  }

  @RunWith(classOf[JUnitRunner])
  class EntityBoatContainerTest extends FlatSpec with Matchers with BeforeAndAfter
  {

  }

  @RunWith(classOf[JUnitRunner])
  class RenderCustomBoatTest extends FlatSpec with Matchers with BeforeAndAfter
  {

  }

}
