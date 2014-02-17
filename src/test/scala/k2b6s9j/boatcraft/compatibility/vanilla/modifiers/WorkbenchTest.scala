package k2b6s9j.boatcraft.compatibility.vanilla.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

@RunWith(classOf[JUnitRunner])
class WorkbenchTest extends FlatSpec with Matchers with BeforeAndAfter {

  var modifier: Modifier = null

  before {
    modifier = Workbench
  }

  "The Workbench Modifier" should "be a modifier." in {
    modifier shouldBe a [Modifier]
  }

  it should "not be rideable." in {
    modifier.isRideable shouldBe false
  }

  it should "contain the Workbench block." in {
    modifier.getBlock shouldBe Blocks.crafting_table
  }

  it should "be called \"Workbench\"." in {
    modifier.getName shouldBe "Workbench"
  }

  /*
  it should "contain a Workbench." in {
    modifier.getContent shouldBe new ItemStack(Blocks.crafting_table)
  }
  */

}
