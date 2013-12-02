package k2b6s9j.BoatCraft.boat.wood.birch

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import net.minecraft.world.World
import net.minecraft.item.ItemStack
import net.minecraftforge.oredict.OreDictionary
import cpw.mods.fml.common.registry.GameRegistry

object Empty {

  class Entity extends EntityCustomBoat with Materials.Entity.Wood.Birch {

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
      setUnlocalizedName("boat.wood.birch.empty")
      func_111206_d("boatcraft:boat.wood.birch.empty")
      GameRegistry.registerItem(this, "Birch Wood Boat")
      shiftedID = this.itemID
      OreDictionary.registerOre("boatBirchWoodEmpty", new ItemStack(this))
    }

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: Entity = new Entity(world, x + 0.5F, y + 1.0F, z + 0.5F)
      return entity
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Birch {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}