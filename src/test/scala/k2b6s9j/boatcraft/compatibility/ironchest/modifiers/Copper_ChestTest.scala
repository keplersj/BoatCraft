package k2b6s9j.boatcraft.compatibility.ironchest.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api.traits.Modifier
import cpw.mods.ironchest.{IronChestType, IronChest}

@RunWith(classOf[JUnitRunner])
class Copper_ChestTest extends FlatSpec with Matchers with BeforeAndAfter {

  var modifier: Modifier = null

  before {
    modifier = Copper_Chest
  }

  "The Copper Chest Modifier" should "be a modifier." in {
    modifier shouldBe a [Modifier]
  }

  it should "not be rideable." in {
    modifier.isRideable shouldBe false
  }

  it should "contain the Iron Chest block." in {
    modifier.getBlock shouldBe IronChest.ironChestBlock
  }

  it should "have the metadata of a Copper Chest" in {
    modifier.getMeta shouldBe IronChestType.COPPER.ordinal
  }

  it should "be called \"Copper Chest\"." in {
    modifier.getName shouldBe "Copper Chest"
  }

  /*
  it should "contain a Chest." in {
    modifier.getContent shouldBe new ItemStack(Blocks.chest)
  }
  */

}
