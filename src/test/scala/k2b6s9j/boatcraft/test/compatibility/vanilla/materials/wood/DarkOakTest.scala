package k2b6s9j.boatcraft.test.compatibility.vanilla.materials.wood

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.traits.Material
import k2b6s9j.boatcraft.compatibility.vanilla.materials.wood.DarkOak
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class DarkOakTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var material: Material = null
	
	before {
		material = DarkOak
	}
	
	"The Dark Oak Wood Material" should "be a material." in {
		material shouldBe a[Material]
	}
	
	it should "have it's properties correct" in
	{
		material should have(
			'texture (new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/wood/dark_oak.png")),
			'name ("Dark Oak"))
	}
	
	/* TODO: Find way to test ItemStack
  	it should "be made of Dark Oak Wood Planks." in 
  	{
    	material.getItem shouldBe new ItemStack(Blocks.planks, 1, 5)
  	}

  	it should "drop Wooden Sticks when dead." in
  	{
    	material.getStick shouldBe new ItemStack(Items.stick)
  	}*/
}
