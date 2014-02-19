package k2b6s9j.boatcraft.test.compatibility.ironchest.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api.traits.Modifier
import cpw.mods.ironchest.{ IronChestType, IronChest }
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers.Obsidian_Chest

@RunWith(classOf[JUnitRunner])
class Obsidian_ChestTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var modifier: Modifier = null

	before {
		modifier = Obsidian_Chest
	}

	"The Obsidian Chest Modifier" should "be a modifier." in
	{
		modifier shouldBe a [Modifier]
	}

	it should "not be rideable." in
	{
		modifier should not be 'rideable
	}

	it should "have it's properties correct" in
	{
		modifier should have(
			'block (IronChest.ironChestBlock),
			'meta (IronChestType.OBSIDIAN.ordinal),
			'name (IronChestType.OBSIDIAN.friendlyName))
	}

	/*it should "contain a Chest." in
  	{
    	modifier.getContent shouldBe new ItemStack(Blocks.chest)
  	}*/

}
