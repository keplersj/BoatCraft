package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers

object TNT {

  class Entity extends EntityCustomBoat with Materials.Entity.Wood.Spruce with Modifiers.Entity.TNT {

  }

  class Item extends ItemCustomBoat {

    val ID: Int
    val shiftedID: Int

  }

  class Render extends RenderBoat with Materials.Render.Wood.Spruce with Modifiers.Render.TNT {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}