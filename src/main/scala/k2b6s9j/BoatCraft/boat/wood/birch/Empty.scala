package k2b6s9j.BoatCraft.boat.wood.birch

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}

object Empty {

  class Entity extends EntityCustomBoat {

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