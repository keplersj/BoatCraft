package k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.wood.greatwood

import net.minecraft.world.World
import k2b6s9j.BoatCraft.boat.Boat.{RenderBoat, ItemCustomBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.Materials
import cpw.mods.fml.common.registry.GameRegistry

object Empty {

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Greatwood {

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

    setUnlocalizedName("boat.compatibility.wood.greatwood.empty")
    GameRegistry.registerItem(this, "Empty Greatwood Boat")
    Item.shiftedID = this.itemID

  }

  class Render extends RenderBoat with Materials.Render.Wood.Greatwood {

  }

}
