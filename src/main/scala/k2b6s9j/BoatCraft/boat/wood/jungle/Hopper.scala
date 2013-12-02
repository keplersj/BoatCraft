package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Hopper {

  class Entity extends EntityBoatContainer with Materials.Entity.Wood.Jungle with Modifiers.Entity.Hopper {

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
      setUnlocalizedName("boat.wood.jungle.Hopper")
      func_111206_d("boatcraft:boat.wood.jungle.Hopper")
      GameRegistry.registerItem(this, "Hopper Jungle Wood Boat")
      shiftedID = this.itemID
      OreDictionary.registerOre("boatJungleWoodHopper", new ItemStack(this))
    }

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: Entity = new Entity(world, x + 0.5F, y + 1.0F, z + 0.5F)
      return entity
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Jungle with Modifiers.Render.Hopper {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}