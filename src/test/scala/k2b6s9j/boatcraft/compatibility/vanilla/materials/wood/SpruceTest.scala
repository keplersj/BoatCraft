package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.init.{Items, Blocks}
import net.minecraft.item.ItemStack

@RunWith(classOf[JUnitRunner])
class SpruceTest extends FlatSpec with Matchers with BeforeAndAfter {

  var material: Material = null

  before {
    material = Spruce
  }

  "The Spruce Wood Material" should "be a material." in {
    material shouldBe a [Material]
  }

  it should "have a defined texture." in {
    material.getTexture shouldBe new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/wood/spruce.png")
  }

  it should "be named \"Spruce\"." in {
    material.getName shouldBe "Spruce"
  }

  /* TODO: Find a way to test ItemStack
  it should "be made of Spruce Wood Planks." in {
    material.getItem shouldBe new ItemStack(Blocks.planks, 1, 1)
  }

  it should "drop Wooden Sticks when dead." in {
    material.getStick shouldBe new ItemStack(Items.stick)
  }
  */

}
