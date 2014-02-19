package k2b6s9j.boatcraft.test.compatibility.ironchest.modifiers

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

import cpw.mods.ironchest.{IronChest, IronChestType}
import k2b6s9j.boatcraft.api.traits.Modifier
import k2b6s9j.boatcraft.compatibility.ironchest.modifiers.Copper_Chest
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

@RunWith(classOf[JUnitRunner])
class Copper_ChestTest extends FlatSpec with Matchers with BeforeAndAfter {

	var modifier: Modifier = null

	before
	{
		modifier = Copper_Chest
	}

	"The Copper Chest Modifier" should "be a modifier." in
	{
		modifier shouldBe a [Modifier]
	}

	it should "not be rideable." in
	{
		modifier should not be 'rideable
	}

	it should "contain the Iron Chest block." in 
	{
		modifier.getBlock shouldBe IronChest.ironChestBlock
	}

	it should "have the metadata of a Copper Chest" in 
	{
		modifier.getMeta shouldBe IronChestType.COPPER.ordinal
	}

	it should "be called \"Copper Chest\"." in
	{
		modifier.getName shouldBe "Copper Chest"
	}

  /* TODO: Find way to test ItemStack
	it should "contain an Iron Chest block." in
	{
		modifier.getContent shouldBe new ItemStack(IronChest.ironChestBlock)
	}
	*/

}
