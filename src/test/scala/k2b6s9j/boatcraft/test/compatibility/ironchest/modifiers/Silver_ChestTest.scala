package k2b6s9j.boatcraft.test.compatibility.ironchest.modifiers

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import cpw.mods.ironchest.{IronChest, IronChestType}
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers.Silver_Chest
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class Silver_ChestTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var modifier: Modifier = null
	
	before 
	{
		modifier = Silver_Chest
	}
	
	"The Silver Chest Modifier" should "be a modifier." in {
		modifier shouldBe a[Modifier]
	}
	
	it should "not be rideable." in {
		modifier should not be 'rideable
	}

	it should "have it's properties correct" in
	{
		modifier should have(
			'block (IronChest.ironChestBlock),
			'meta (IronChestType.SILVER.ordinal),
			'name (IronChestType.SILVER.friendlyName))
	}
	
	/*it should "contain a Chest." in
	{
    	modifier.getContent shouldBe new ItemStack(Blocks.chest)
  	}*/
}
