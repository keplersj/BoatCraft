package k2b6s9j.BoatCraft.boat

import k2b6s9j.BoatCraft.boat.Boat.{EntityCustomBoat, RenderBoat}

object Materials {

  object Entity {

    object Wood {

      trait Oak extends EntityCustomBoat {

      }

      trait Spruce extends EntityCustomBoat {

      }

      trait Birch extends EntityCustomBoat {

      }

      trait Jungle extends EntityCustomBoat {

      }

    }

  }

  object Render {

    object Wood {

      trait Oak extends RenderBoat {

      }

      trait Spruce extends RenderBoat {

      }

      trait Birch extends RenderBoat {

      }

      trait Jungle extends RenderBoat {

      }

    }

  }

}