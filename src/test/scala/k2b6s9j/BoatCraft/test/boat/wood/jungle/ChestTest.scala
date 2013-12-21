package k2b6s9j.BoatCraft.test.boat.wood.jungle

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.wood.jungle.Chest
import net.minecraft.util.ResourceLocation

@RunWith(classOf[JUnitRunner])
class ChestTest extends FlatSpec with Matchers {

  def renderer: Chest.Render = new Chest.Render

  "The Chest Jungle Wood Boat Renderer" should "return the Jungle Wood Boat Texture." in {
    renderer.getTexture() should be (new ResourceLocation("boatcraft:textures/boats/jungle.png"))
  }

}
