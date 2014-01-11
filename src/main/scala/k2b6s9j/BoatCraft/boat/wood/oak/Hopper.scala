package k2b6s9j.BoatCraft.boat.wood.oak

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderCustomBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraft.block.Block
import k2b6s9j.BoatCraft.registry.RecipeRegistration

object Hopper {

  class Entity(world: World) extends EntityBoatContainer(world) with Materials.Entity.Wood.Oak with Modifiers.Entity.Hopper {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.item.itemID
    }

  }

  object Item {
    var item: Item = _
    //RecipeRegistration.AddShapelessRecipe(new ItemStack(item), new ItemStack(Block.tnt), "boatOak")
  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.oak.hopper")
    GameRegistry.registerItem(this, "Hopper Oak Wood Boat")
    OreDictionary.registerOre("boatOakWoodHopper", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      new Entity(world)
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Oak with Modifiers.Render.Hopper {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}