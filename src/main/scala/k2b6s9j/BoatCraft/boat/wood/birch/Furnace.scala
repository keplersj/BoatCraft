package k2b6s9j.BoatCraft.boat.wood.birch

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

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Birch with Modifiers.Entity.Furnace {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.item.itemID
    }

  }

  object Item {
    var item: Item = _
    RecipeRegistration.AddShapelessRecipe(new ItemStack(item), new ItemStack(Block.furnaceIdle), "boatBirch")

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.birch.furnace")
    GameRegistry.registerItem(this, "Furnace Birch Wood Boat")
    OreDictionary.registerOre("boatBirchWoodFurnace", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      new Entity(world)
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Birch with Modifiers.Render.Furnace {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}