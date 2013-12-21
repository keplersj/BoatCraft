package k2b6s9j.BoatCraft.test.boat.wood.birch

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.wood.birch.Empty
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class EmptyTest extends FlatSpec with Matchers {

  def renderer: Empty.Render = new Empty.Render

  "The Empty Birch Wood Boat Renderer" should "return the Birch Wood Boat Texture." in {
    renderer.getTexture() should be (new ResourceLocation("boatcraft:textures/boats/birch.png"))
  }

}
