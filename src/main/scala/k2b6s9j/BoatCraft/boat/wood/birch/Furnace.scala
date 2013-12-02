package k2b6s9j.BoatCraft.boat.wood.birch

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Modifiers.{Entity, Render}

object Furnace {

  class Entity extends EntityCustomBoat with Entity.Furnace {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Render.Furnace {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}