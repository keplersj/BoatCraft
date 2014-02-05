package k2b6s9j.boatcraft.compatibility.thaumcraft.boat

import k2b6s9j.boatcraft.core.Boat.{RenderCustomBoat, EntityCustomBoat}

object Materials {

  object Entity {

    trait Entity extends EntityCustomBoat {


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

      trait Greatwood extends RenderCustomBoat {

      }

      trait Silverwood extends RenderCustomBoat {

      }

    }

  }

}
