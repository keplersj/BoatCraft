package k2b6s9j.BoatCraft.boat.wood.oak

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, ItemCustomBoat, RenderBoat, EntityCustomBoat}
import k2b6s9j.BoatCraft.boat.Materials
import k2b6s9j.BoatCraft.boat.Modifiers
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraftforge.oredict.OreDictionary
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import net.minecraft.block.Block
import k2b6s9j.BoatCraft.registry.RecipeRegistration

object Chest {

  class Entity(world: World) extends EntityBoatContainer(world) with Materials.Entity.Wood.Oak with Modifiers.Entity.Chest {

    override def useItemID(): Boolean = {
      true
    }

    override def customBoatItemID(): Int = {
      Item.item.itemID
    }

  }

  object Item {

    var ID: Int = _
    var item: Item = new Item(ID)
    RecipeRegistration.AddShapelessRecipe(new ItemStack(item), new ItemStack(Block.chest), "boatOak")

  }

  class Item(id: Int) extends ItemCustomBoat(id) {

    setUnlocalizedName("boat.wood.oak.chest")
    //func_111206_d("boatcraft:boat.wood.oak.chest")
    GameRegistry.registerItem(this, "Chest Oak Wood Boat")
    OreDictionary.registerOre("boatOakWoodChest", new ItemStack(this))

    override def getEntity(world: World, x: Int, y: Int, z: Int): EntityCustomBoat = {
      new Entity(world)
    }

  }

  class Render extends RenderBoat with Materials.Render.Wood.Oak with Modifiers.Render.Chest {

    override def getEntity(): EntityCustomBoat = {
      val entity: Entity =  null
      return entity
    }

  }

}