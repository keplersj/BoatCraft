package k2b6s9j.boatcraft.core.materials.test

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.core.materials.Empty
import net.minecraft.init.Blocks

@RunWith(classOf[JUnitRunner])
class EmptyTest extends FlatSpec with Matchers
{
	private val rideable = 'rideable
	
	it should "be rideable." in
	{
		Empty shouldBe rideable
	}

	it should "contain the Air block." in
	{
		Empty.getBlock shouldBe Blocks.air
	}

	it should "be called \"Empty\"." in
	{
		Empty.getName shouldBe "Empty"
	}

	it should "not have any content." in
	{
		Empty.getContent shouldBe null
	}

}