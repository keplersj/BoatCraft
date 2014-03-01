package k2b6s9j.boatcraft.test.compatibility.vanilla.materials.metal

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ BeforeAndAfter, Matchers, FlatSpec }
import k2b6s9j.boatcraft.api.traits.Material
import net.minecraft.util.ResourceLocation
import k2b6s9j.boatcraft.compatibility.vanilla.materials.metal.Gold

@RunWith(classOf[JUnitRunner])
class GoldTest extends FlatSpec with Matchers with BeforeAndAfter
{
	var material: Material = null

	before
	{
		material = Gold
	}

	"The Gold Material" should "be a material." in 
	{
		material shouldBe a [Material]
	}
	
	it should "have it's properties correct" in
	{
		material should have(
			'texture (new ResourceLocation("boatcraft", "textures/entity/boat/vanilla/metal/gold.png")),
			'name ("Gold"))
	}

	/* TODO: Find a way to test ItemStack
  	it should "be made of Gold." in
  	{
    	material.getItem shouldBe new ItemStack(Items.goldIngot)
  	}*/
}
