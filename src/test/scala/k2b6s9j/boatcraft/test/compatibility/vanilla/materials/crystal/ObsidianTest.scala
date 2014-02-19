package k2b6s9j.boatcraft.test.compatibility.vanilla.materials.crystal

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfter, Matchers, FlatSpec}
import k2b6s9j.boatcraft.api.traits.Material
import k2b6s9j.boatcraft.compatibility.vanilla.materials.crystal.Obsidian
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class ObsidianTest extends FlatSpec with Matchers with BeforeAndAfter {

  var material: Material = null

  before {
    material = Obsidian
  }

  "The Obsidian Material" should "be a material." in {
    material shouldBe a [Material]
  }

  it should "have a defined texture." in {
    material.getTexture shouldBe new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/crystal/obsidian.png")
  }

  it should "be named \"Obsidian\"." in {
    material.getName shouldBe "Obsidian"
  }

  /* TODO: Find a way to test ItemStack
  it should "be made of Obsidian." in {
    material.getItem shouldBe new ItemStack(Block.obsidian)
  }
  */

}
