package k2b6s9j.BoatCraft.boat.wood.oak

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers

object Hopper {

  class Entity extends EntityBoatContainer with Materials.Entity.Wood.Oak with Modifiers.Entity.Hopper {

  }

  class Item extends ItemCustomBoat {

    val ID: Int
    val shiftedID: Int

  }

  class Render extends RenderBoat with Materials.Render.Wood.Oak with Modifiers.Render.Hopper {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}