package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api.traits.Modifier
import cpw.mods.ironchest.{IronChestType, IronChest}

@RunWith(classOf[JUnitRunner])
class DirtChest9000Test extends FlatSpec with Matchers with BeforeAndAfter {

  var modifier: Modifier = null

  before {
    modifier = DirtChest9000
  }

  "The Chest Modifier" should "be a modifier." in {
    modifier shouldBe a [Modifier]
  }

  it should "not be rideable." in {
    modifier.isRideable shouldBe false
  }

  it should "contain the Iron Chest block." in {
    modifier.getBlock shouldBe IronChest.ironChestBlock
  }

  it should "be called \"DirtChest9000\"." in {
    modifier.getName shouldBe "DirtChest9000"
  }

  it should "have the metadata of a DirtChest9000" in {
    modifier.getMeta shouldBe IronChestType.DIRTCHEST9000.ordinal
  }

  /*
  it should "contain a Chest." in {
    modifier.getContent shouldBe new ItemStack(Blocks.chest)
  }
  */

}
