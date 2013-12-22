package k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.wood.silverwood

import k2b6s9j.BoatCraft.boat.Boat.{RenderBoat, ItemCustomBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.compatibility.thaumcraft.boat.Materials
import net.minecraft.world.World
import cpw.mods.fml.common.registry.GameRegistry

object Empty {

  class Entity(world: World) extends EntityCustomBoat(world) with Materials.Entity.Wood.Silverwood {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.shiftedID
    }

  }

  object Item {

    var ID: Int = _
    var shiftedID: Int = _
    var item: Item = new Item(ID)

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.compatibility.wood.silverwood.empty")
    GameRegistry.registerItem(this, "Empty Silverwood Boat")
    Item.shiftedID = this.itemID

  }

  class Render extends RenderBoat with Materials.Render.Wood.Silverwood {

  }

}