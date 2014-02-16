package k2b6s9j.boatcraft.api.registry.test

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import k2b6s9j.boatcraft.api.registry.MaterialRegistry
import k2b6s9j.boatcraft.api.traits.examples._

@RunWith(classOf[JUnitRunner])
class MaterialRegistryTest extends FlatSpec with Matchers with BeforeAndAfter {

  "A Material" should "be added to the registered Materials map." in {
    MaterialRegistry addMaterial ExampleMaterial
    MaterialRegistry getMaterial "test" shouldBe ExampleMaterial
  }

}
