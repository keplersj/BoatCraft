package k2b6s9j.boatcraft.test.compatibility.vanilla.modifiers

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ BeforeAndAfter, Matchers, FlatSpec }
import k2b6s9j.boatcraft.api.traits.Modifier
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack
import k2b6s9j.boatcraft.compatibility.vanilla.modifiers.Furnace

@RunWith(classOf[JUnitRunner])
class FurnaceTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var modifier: Modifier = null

	before
	{
		modifier = Furnace
	}

	"The Furnace Modifier" should "be a modifier." in
	{
		modifier shouldBe a [Modifier]
	}

	it should "not be rideable." in
	{
		modifier should not be 'rideable
	}

	it should "contain the Furnace block." in
		modifier.getBlock shouldBe Blocks.furnace

	it should "be called \"Furnace\"." in
		modifier.getName shouldBe "Furnace"

	it should "contain a Furnace." in
		modifier.getContent shouldBe new ItemStack(Blocks.furnace)

}
