package k2b6s9j.boatcraft.test.api

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import k2b6s9j.boatcraft.api
import k2b6s9j.boatcraft.api.boat.ItemCustomBoat

@RunWith(classOf[JUnitRunner])
class apiTest extends FlatSpec with Matchers with BeforeAndAfter
{

	"The Custom Boat item" should "be returned." in
	{
		api.getItemCustomBoat shouldBe new ItemCustomBoat(333)
	}

}
