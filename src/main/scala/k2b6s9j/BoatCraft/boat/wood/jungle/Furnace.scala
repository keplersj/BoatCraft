package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraft.block.Block
import k2b6s9j.BoatCraft.registry.RecipeRegistration

object Furnace {

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Jungle with Modifiers.Entity.Furnace {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.item.itemID
    }

  }

  object Item {

    var ID: Int = _
    var item: Item = new Item(ID)
    RecipeRegistration.AddShapelessRecipe(new ItemStack(item), new ItemStack(Block.furnaceIdle), "boatJungle")

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.jungle.furnace")
    //func_111206_d("boatcraft:boat.wood.jungle.furnace")
    GameRegistry.registerItem(this, "Furnace Jungle Wood Boat")
    OreDictionary.registerOre("boatJungleWoodFurnace", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      new Entity(world)
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Jungle with Modifiers.Render.Furnace {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}