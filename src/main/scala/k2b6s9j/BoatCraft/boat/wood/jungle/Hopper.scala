package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Modifiers.{Entity, Render}

object Hopper {

  class Entity extends EntityBoatContainer with Entity.Hopper {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Render.Hopper {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}