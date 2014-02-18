package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import net.minecraft.init.{Items, Blocks}

@RunWith(classOf[JUnitRunner])
class BirchTest extends FlatSpec with Matchers with BeforeAndAfter {

  var material: Material = null

  before {
    material = Birch
  }

  "The Birch Wood Material" should "be a material." in {
    material shouldBe a [Material]
  }

  it should "have a defined texture." in {
    material.getTexture shouldBe new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/wood/birch.png")
  }

  it should "be named \"Birch\"." in {
    material.getName shouldBe "Birch"
  }

  /* TODO: Find way to test ItemStack
  it should "be made of Birch Wood Planks." in {
    material.getItem shouldBe new ItemStack(Blocks.planks, 1, 2)
  }

  it should "drop Wooden Sticks when dead." in {
    material.getStick shouldBe new ItemStack(Items.stick)
  }
  */

}
