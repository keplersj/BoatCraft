package k2b6s9j.boatcraft.test.compatibility.vanilla.materials.crystal

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.boatcraft.api.traits.Material
import k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal.Emerald
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class EmeraldTest extends FlatSpec with Matchers with BeforeAndAfter {

  var material: Material = null

  before {
    material = Emerald
  }

  "The Emerald Material" should "be a material." in {
    material shouldBe a [Material]
  }

  it should "have a defined texture." in {
    material.getTexture shouldBe new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/crystal/emerald.png")
  }

  it should "be named \"Emerald\"." in {
    material.getName shouldBe "Emerald"
  }

  /* TODO: Find a way to test ItemStack
  it should "be made of Emerald." in {
    material.getItem shouldBe new ItemStack(Items.emerald)
  }
  */

}
