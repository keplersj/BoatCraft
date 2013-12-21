package k2b6s9j.BoatCraft.test.boat

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Materials.Entity.Entity

@RunWith(classOf[JUnitRunner])
class MaterialsTest extends FlatSpec with Matchers {

  def entity: Materials.Entity.Entity = new Entity {}

  "Any boat interacting with this class" should "be a custom boat." in {
    entity.isCustomBoat() should be (true)
  }

}