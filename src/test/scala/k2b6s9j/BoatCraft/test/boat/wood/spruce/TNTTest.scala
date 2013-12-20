package k2b6s9j.BoatCraft.test.boat.wood.spruce

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.wood.spruce.TNT
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class TNTTest extends FlatSpec with Matchers {

  def renderer: TNT.Render = new TNT.Render

  "The TNT Spruce Wood Boat Renderer" should "return the Spruce Wood Boat Texture." in {
    renderer.getTexture() should be (new ResourceLocation("boatcraft:textures/boats/spruce.png"))
  }

}
