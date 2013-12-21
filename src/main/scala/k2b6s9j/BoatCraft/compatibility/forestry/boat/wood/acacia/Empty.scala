package k2b6s9j.BoatCraft.compatibility.forestry.boat.wood.acacia

import k2b6s9j.BoatCraft.boat.Boat._
import k2b6s9j.BoatCraft.compatibility.forestry.boat.Materials
import net.minecraft.world.World

class Empty {

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Acacia {

  }

  object Item {
    var ID: Int = _
    var shiftedID: Int = _
    var item: Item = new Item(ID)
  }

  class Item(id: Int) extends ItemCustomBoat(id) {

  }

  class Render extends RenderBoat with Materials.Render.Wood.Acacia {

  }

}
