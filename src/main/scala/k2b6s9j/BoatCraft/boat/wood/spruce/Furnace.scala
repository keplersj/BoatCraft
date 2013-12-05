package k2b6s9j.BoatCraft.boat.wood.spruce

import k2b6s9j.BoatCraft.boat.Boat.{ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object Furnace {

  class Entity extends EntityCustomBoat(par1World = World) with Materials.Entity.Wood.Spruce with Modifiers.Entity.Furnace {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.shiftedID
    }

  }

  object Item {

    var ID: Int = 0
    var shiftedID: Int = 0

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.spruce.furnace")
    func_111206_d("boatcraft:boat.wood.spruce.furnace")
    GameRegistry.registerItem(this, "Furnace Spruce Wood Boat")
    Item.shiftedID = this.itemID
    OreDictionary.registerOre("boatSpruceWoodFurnace", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      val entity: Entity = new Entity(world, x + 0.5F, y + 1.0F, z + 0.5F)
      return entity
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Spruce with Modifiers.Render.Furnace {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}