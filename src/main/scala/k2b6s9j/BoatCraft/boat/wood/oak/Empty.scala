package k2b6s9j.BoatCraft.boat.wood.oak

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Empty {

  class Entity extends EntityCustomBoat with Materials.Entity.Wood.Oak {

    var item: Item

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      item.shiftedID
    }

  }

  class Item extends ItemCustomBoat {

    val ID: Int
    var shiftedID: Int

    def Item(id: Int) {
      super.id
      setUnlocalizedName("boat.wood.oak.empty")
      func_111206_d("boatcraft:boat.wood.oak.empty")
      GameRegistry.registerItem(this, "Oak Wood Boat")
      shiftedID = this.itemID
      OreDictionary.registerOre("boatOakWoodEmpty", new ItemStack(this))
    }

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: Entity = new Entity(world, x + 0.5F, y + 1.0F, z + 0.5F)
      return entity
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Oak {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}