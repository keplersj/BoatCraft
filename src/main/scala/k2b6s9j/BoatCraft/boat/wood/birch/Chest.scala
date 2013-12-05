package k2b6s9j.BoatCraft.boat.wood.birch

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Chest {

  class Entity extends EntityBoatContainer(par1World = World) with Materials.Entity.Wood.Birch with Modifiers.Entity.Chest {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.shiftedID
    }

  }

  object Item {

    var ID: Int = 0
    var shiftedID: Int = 0

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.birch.chest")
    func_111206_d("boatcraft:boat.wood.birch.chest")
    GameRegistry.registerItem(this, "Chest Birch Wood Boat")
    Item.shiftedID = this.itemID
    OreDictionary.registerOre("boatBirchWoodChest", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: Entity = new Entity(world, x + 0.5F, y + 1.0F, z + 0.5F)
      return entity
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Birch with Modifiers.Render.Chest {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}