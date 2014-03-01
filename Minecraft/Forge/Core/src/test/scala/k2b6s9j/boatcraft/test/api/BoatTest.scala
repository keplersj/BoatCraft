package k2b6s9j.boatcraft.test.api

import java.util.ArrayList

import scala.collection.JavaConversions._

import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import k2b6s9j.boatcraft.api.Registry
import k2b6s9j.boatcraft.api.boat.ItemCustomBoat
import k2b6s9j.boatcraft.test.api.traits.examples._
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound

@RunWith(classOf[JUnitRunner])
class BoatTest extends FlatSpec with Matchers with BeforeAndAfter
{
	//ItemCustomBoatTest
	var item: ItemCustomBoat = null
	
	before 
	{
		item = new ItemCustomBoat
		Registry register ExampleMaterial
		Registry register ExampleModifier
	}
	
	"A Boat Item" should "have an unlocalized name based on it's Material and Modifier." in
	{
		val stack = new ItemStack(item)
		stack.stackTagCompound = new NBTTagCompound
		stack.stackTagCompound setString ("material", "test")
		stack.stackTagCompound setString ("modifier", "test")
		item getUnlocalizedName stack shouldBe "boat.test.test"
	}
	
	it should "register all it's subitems" in
	{
		val list = new ArrayList[ItemStack]
		item getSubItems(item, CreativeTabs.tabTransport, list)
		/* At least Code coverage
		var stack = new ItemStack(item)
		stack.stackTagCompound = new NBTTagCompound
		for ((nameMat, material) <- MaterialRegistry.materials)
		{
			stack.stackTagCompound setString("material", nameMat)
			for ((nameMod, modifier) <- ModifierRegistry.modifiers)
			{
				stack.stackTagCompound setString("modifier", nameMod)
				list should contain (stack)
			}
		}*/
	}
	
	//EntityCustomBoatTest
	
	//EntityBoatContainerTest
	
	//RenderCustomBoatTest

}
