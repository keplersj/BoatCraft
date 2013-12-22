package k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.wood.greatwood

import net.minecraft.world.World
import k2b6s9j.BoatCraft.boat.Boat.{RenderBoat, ItemCustomBoat, EntityBoatContainer}
import k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry

object Hopper {

  class Entity(world: World) extends EntityBoatContainer(world) with Materials.Entity.Wood.Greatwood with Modifiers.Entity.Hopper {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.shiftedID
    }

  }

  object Item {

    var ID: Int = _
    var shiftedID: Int = _
    var item: Item = new Item(ID)

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.compatibility.wood.greatwood.hopper")
    GameRegistry.registerItem(this, "Hopper Greatwood Boat")
    Item.shiftedID = this.itemID

  }

  class Render extends RenderBoat with Materials.Render.Wood.Greatwood with Modifiers.Render.Hopper {

  }

}