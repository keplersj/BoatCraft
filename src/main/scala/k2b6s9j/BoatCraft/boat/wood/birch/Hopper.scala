package k2b6s9j.BoatCraft.boat.wood.birch

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers

object Hopper {

  class Entity extends EntityBoatContainer with Materials.Entity.Wood.Birch with Modifiers.Entity.Hopper {

  }

  class Item extends ItemCustomBoat {

  }

  class Render extends RenderBoat with Materials.Render.Wood.Birch with Modifiers.Render.Hopper {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}