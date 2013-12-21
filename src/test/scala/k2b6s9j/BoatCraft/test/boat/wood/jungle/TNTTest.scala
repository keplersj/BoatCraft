package k2b6s9j.BoatCraft.test.boat.wood.jungle

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.wood.jungle.TNT
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class TNTTest extends FlatSpec with Matchers {

  def renderer: TNT.Render = new TNT.Render

  "The TNT Jungle Wood Boat Renderer" should "return the Jungle Wood Boat Texture." in {
    renderer.getTexture() should be (new ResourceLocation("boatcraft:textures/boats/jungle.png"))
  }

}
