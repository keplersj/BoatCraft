package k2b6s9j.BoatCraft.boat.wood.birch

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials

object Empty {

  class Entity extends EntityCustomBoat with Materials.Entity.Wood.Birch {

  }

  class Item extends ItemCustomBoat {

    val ID: Int
    val shiftedID: Int

  }

  class Render extends RenderBoat with Materials.Render.Wood.Birch {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}