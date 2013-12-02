package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Chest {

  class Entity extends EntityBoatContainer with Materials.Entity.Wood.Spruce with Modifiers.Entity.Chest {

    var item: Item

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      item.shiftedID
    }

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    val ID: Int
    var shiftedID: Int
    setUnlocalizedName("boat.wood.spruce.chest")
    func_111206_d("boatcraft:boat.wood.spruce.chest")
    GameRegistry.registerItem(this, "Chest Spruce Wood Boat")
    shiftedID = this.itemID
    OreDictionary.registerOre("boatSpruceWoodChest", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: Entity = new Entity(world, x + 0.5F, y + 1.0F, z + 0.5F)
      return entity
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Spruce with Modifiers.Render.Chest {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}