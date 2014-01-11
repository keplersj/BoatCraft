package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderCustomBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraft.block.Block

object Empty {

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Spruce {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.item.itemID
    }

  }

  object Item {
    var item: Item = _
    //GameRegistry.addRecipe(new ItemStack(item), "W W", "WWW", Character.valueOf('W'), new ItemStack(Block.planks, 1, 1))
  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.spruce.empty")
    GameRegistry.registerItem(this, "Spruce Wood Boat")
    OreDictionary.registerOre("boatSpruceWoodEmpty", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      new Entity(world)
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Spruce {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}