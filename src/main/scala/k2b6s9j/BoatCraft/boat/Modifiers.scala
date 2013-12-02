package k2b6s9j.BoatCraft.boat

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, EntityCustomBoat}
import net.minecraft.item.ItemStack
import net.minecraft.block.Block

object Modifiers {

  object Entity {

    trait Block extends EntityCustomBoat {

      override def doesBoatContainBlock(): Boolean = {
        true
      }

    }

    trait Chest extends EntityBoatContainer with Block {

      override def blockInBoat(): ItemStack = {
        new ItemStack(Block.chest, 1, 0)
      }

      override def getSizeInventory(): Int = {
        27
      }

      override def getDefaultDisplayTile(): net.minecraft.block.Block = {
        Block.chest
      }

      override def getDefaultDisplayTileOffset(): Int = {
        8
      }

      override def isInvNameLocalized(): Int = {
        false
      }

      override def getInvName(): String = {
        "Boat"
      }

    }

    trait Furnace extends EntityCustomBoat with Block {

    }

    trait TNT extends EntityCustomBoat with Block {

    }

    trait Hopper extends EntityBoatContainer with Block {

    }

  }

  object Render {

    trait Chest {

    }

    trait Furnace {

    }

    trait TNT {

    }

    trait Hopper {

    }

  }

}