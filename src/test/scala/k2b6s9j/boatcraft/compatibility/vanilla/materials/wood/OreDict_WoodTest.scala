package k2b6s9j.boatcraft.compatibility.vanilla.materials.wood

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class OreDict_WoodTest extends FlatSpec with Matchers with BeforeAndAfter {

  var material: Material = null

  before {
    material = OreDict_Wood
  }

  "The OreDictionary Wood Material" should "be a material." in {
    material shouldBe a [Material]
  }

  it should "have a defined texture." in {
    material.getTexture shouldBe new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/wood/oak.png")
  }

  it should "be named \"Wood\"." in {
    material.getName shouldBe "Wood"
  }

  /* TODO: Find a way to test ItemStack
  it should "be made of Oak Wood Planks." in {
    material.getItem shouldBe new ItemStack(Blocks.planks)
  }

  it should "drop Wooden Sticks when dead." in {
    material.getStick shouldBe new ItemStack(Items.stick)
  }
  */

}
