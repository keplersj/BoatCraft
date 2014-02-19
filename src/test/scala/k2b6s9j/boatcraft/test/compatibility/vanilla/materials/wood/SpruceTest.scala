package k2b6s9j.boatcraft.test.compatibility.vanilla.materials.wood

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.traits.Material
import k2b6s9j.boatcraft.compatibility.vanilla.materials.wood.Spruce
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class SpruceTest extends FlatSpec with Matchers with BeforeAndAfter
{

	var material: Material = null

	before
	{
		material = Spruce
	}

	"The Spruce Wood Material" should "be a material." in
	{
		material shouldBe a [Material]
	}
	
	it should "have it's properties correct" in
	{
		material should have(
			'texture (new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/wood/spruce.png")),
			'name ("Spruce"))
	}

	/* TODO: Find a way to test ItemStack
  	it should "be made of Spruce Wood Planks." in
  	{
    	material.getItem shouldBe new ItemStack(Blocks.planks, 1, 1)
  	}

  	it should "drop Wooden Sticks when dead." in
  	{
    	material.getStick shouldBe new ItemStack(Items.stick)
  	}*/

}
