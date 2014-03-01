package k2b6s9j.boatcraft.test.core.materials

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.core.materials.Empty
import net.minecraft.init.Blocks

@RunWith(classOf[JUnitRunner])
class EmptyTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var modifier: Modifier = null

	before
	{
		modifier = Empty
	}

	"The Empty Modifier" should "be a modifier." in
	{
		modifier shouldBe a [Modifier]
	}
	
	it should "be rideable." in
	{
		modifier shouldBe 'rideable
	}

	it should "contain the Air block." in
	{
		modifier.getBlock shouldBe Blocks.air
	}

	it should "be called \"Empty\"." in
	{
		modifier.getName shouldBe "Empty"
	}

	it should "not have any content." in
	{
		modifier.getContent shouldBe null
	}

}