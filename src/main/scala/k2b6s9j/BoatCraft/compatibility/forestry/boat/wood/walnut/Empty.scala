package k2b6s9j.BoatCraft.compatibility.forestry.boat.wood.walnut

import net.minecraft.world.World
import k2b6s9j.BoatCraft.boat.Boat.{RenderBoat, ItemCustomBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.compatibility.forestry.boat.Materials

class Empty {

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Walnut {

  }

  object Item {
    var ID: Int = _
    var shiftedID: Int = _
    var item: Item = new Item(ID)
  }

  class Item(id: Int) extends ItemCustomBoat(id) {

  }

  class Render extends RenderBoat with Materials.Render.Wood.Walnut {

  }

}
