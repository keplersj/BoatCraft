package k2b6s9j.boatcraft.api.registry.test

import org.scalatest._
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith

import k2b6s9j.boatcraft.api.registry.ModifierRegistry
import k2b6s9j.boatcraft.api.traits.examples._

@RunWith(classOf[JUnitRunner])
class ModifierRegistryTest extends FlatSpec with Matchers with BeforeAndAfter {

  "A Modifier" should "be added to the registered Modifiers map." in {
    ModifierRegistry addModifier ExampleModifier
    ModifierRegistry getModifier "test" shouldBe ExampleModifier
  }

}
