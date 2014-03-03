package k2b6s9j.boatcraft.test.compatibility.vanilla.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ BeforeAndAfter, Matchers, FlatSpec }
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Chest
import net.minecraft.block.Block

@RunWith(classOf[JUnitRunner])
class ChestTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var modifier: Modifier = null
	
	before 
	{
		modifier = Chest
	}
	
	"The Chest Modifier" should "be a modifier." in
	{
		modifier shouldBe a [Modifier]
	}

	it should "not be rideable." in
	{
		modifier should not be 'rideable
	}
	
	it should "contain the Chest block." in
	{
		modifier.getBlock shouldBe Block.chest
	}
	
	it should "be called \"Chest\"." in
	{
		modifier.getName shouldBe "Chest"
	}

	/* TODO: Find way to test ItemStack
  it should "contain a Chest block." in
	{
		modifier.getContent shouldBe new ItemStack(Blocks.chest)
	}
	*/

}
