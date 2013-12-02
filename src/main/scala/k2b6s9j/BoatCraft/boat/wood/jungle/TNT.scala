package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Modifiers.{Entity, Render}

object TNT {

  class Entity extends EntityCustomBoat with Entity.TNT {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Render.TNT {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}