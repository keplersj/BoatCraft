package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials

object Empty {

  class Entity extends EntityCustomBoat with Materials.Entity.Wood.Spruce {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Materials.Render.Wood.Spruce {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}