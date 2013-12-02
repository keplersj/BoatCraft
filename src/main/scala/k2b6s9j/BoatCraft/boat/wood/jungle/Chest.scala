package k2b6s9j.BoatCraft.boat.wood.jungle

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers

object Chest {

  class Entity extends EntityBoatContainer with Materials.Entity.Wood.Jungle with Modifiers.Entity.Chest {

  }

  class Item extends ItemCustomBoat {

    val ID: Int
    val shiftedID: Int

  }

  class Render extends RenderBoat with Materials.Render.Wood.Jungle with Modifiers.Render.Chest {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}