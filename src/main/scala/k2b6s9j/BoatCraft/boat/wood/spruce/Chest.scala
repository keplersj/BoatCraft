package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Modifiers.{Entity, Render}

object Chest {

  class Entity extends EntityBoatContainer with Entity.Chest {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Render.Chest {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}