package k2b6s9j.BoatCraft.test.boat.wood.spruce

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.wood.spruce.Chest
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class ChestTest extends FlatSpec with Matchers {

  def renderer: Chest.Render = new Chest.Render

  "The Chest Spruce Wood Boat Renderer" should "return the Spruce Wood Boat Texture." in {
    renderer.getTexture() should be (new ResourceLocation("boatcraft:textures/boats/spruce.png"))
  }

}
