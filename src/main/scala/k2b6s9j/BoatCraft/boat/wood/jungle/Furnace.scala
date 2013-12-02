package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers

object Furnace {

  class Entity extends EntityCustomBoat with Materials.Entity.Wood.Jungle with Modifiers.Entity.Furnace {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Materials.Render.Wood.Jungle with Modifiers.Render.Furnace {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}