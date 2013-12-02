package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}

object Chest {

  class Entity extends EntityBoatContainer {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}