package k2b6s9j.boatcraft.test.compatibility.vanilla.materials.metal

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import k2b6s9j.boatcraft.compatibility.vanilla.materials.metal.Iron

@RunWith(classOf[JUnitRunner])
class IronTest extends FlatSpec with Matchers with BeforeAndAfter {

  var material: Material = null

  before {
    material = Iron
  }

  "The Iron Material" should "be a material." in {
    material shouldBe a [Material]
  }

  it should "have a defined texture." in {
    material.getTexture shouldBe new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/metal/iron.png")
  }

  it should "be named \"Iron\"." in {
    material.getName shouldBe "Iron"
  }

  /* TODO: Find a way to test ItemStack
  it should "be made of Emerald." in {
    material.getItem shouldBe new ItemStack(Items.ironIngot)
  }
  */

}
