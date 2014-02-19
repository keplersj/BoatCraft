package k2b6s9j.boatcraft.test.compatibility.ironchest.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api.traits.Modifier
import cpw.mods.ironchest.{ IronChestType, IronChest }
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers.Diamond_Chest

@RunWith(classOf[JUnitRunner])
class Diamond_ChestTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var modifier: Modifier = null
	
	before 
	{
		modifier = Diamond_Chest
	}
	
	"The Diamond Chest Modifier" should "be a modifier." in
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
			'meta (IronChestType.DIAMOND.ordinal),
			'name (IronChestType.DIAMOND.friendlyName))
	}
	
	/*it should "contain a Chest." in
  	{
    	modifier.getContent shouldBe new ItemStack(Blocks.chest)
  	}*/
}
