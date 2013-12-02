package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers

object Chest {

  class Entity extends EntityBoatContainer with Materials.Entity.Wood.Spruce with Modifiers.Entity.Chest {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Materials.Render.Wood.Spruce with Modifiers.Render.Chest {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}