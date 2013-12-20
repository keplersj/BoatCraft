package k2b6s9j.BoatCraft.test.boat

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.Boat
import net.minecraft.util.ResourceLocation
import net.minecraft.item.ItemStack
import k2b6s9j.BoatCraft.boat.wood.oak.Empty
import net.minecraftforge.client.IItemRenderer.ItemRenderType

@RunWith(classOf[JUnitRunner])
class BoatTest extends FlatSpec with Matchers {

  def boat = Boat
  def render = new Boat.RenderBoat
  def item: ItemStack = new ItemStack(Empty.Item)

  "The Default Boat Texture" should "be the Vanilla Oak Wood Boat Texture." in {
    render.getTexture() should be (new ResourceLocation("textures/entity/boat.png"))
  }

  "The Boat Renderer" should "handle entity rendering." in {
    render.handleRenderType(item, ItemRenderType.EQUIPPED) should be (true)
  }

  it should "handle equipped rendering." in {
    render.handleRenderType(item, ItemRenderType.EQUIPPED) should be (true)
  }

  it should "handle first person equipped rendering." in {
    render.handleRenderType(item, ItemRenderType.EQUIPPED_FIRST_PERSON) should be (true)
  }

  it should "not handle inventory rendering." in {
    render.handleRenderType(item, ItemRenderType.INVENTORY) should be (false)
  }

  it should "not handle first person map rendering." in {
    render.handleRenderType(item, ItemRenderType.FIRST_PERSON_MAP) should be (false)
  }

  
}
