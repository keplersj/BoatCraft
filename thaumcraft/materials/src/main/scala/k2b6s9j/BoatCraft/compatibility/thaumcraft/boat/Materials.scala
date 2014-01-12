package k2b6s9j.BoatCraft.compatibility.thaumcraft.boat

import k2b6s9j.BoatCraft.boat.Boat.{RenderBoat, EntityCustomBoat}

object Materials {

  object Entity {

    trait Entity extends EntityCustomBoat {

      override def isCustomBoat(): Boolean = {
        true
      }

    }

    object Wood {

      trait Greatwood extends Entity {

      }

      trait Silverwood extends Entity {

      }

    }

  }

  object Render {

    object Wood {

      trait Greatwood extends RenderBoat {

      }

      trait Silverwood extends RenderBoat {

      }

    }

  }

}
