package k2b6s9j.BoatCraft.boat.wood.jungle

object TNT {

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