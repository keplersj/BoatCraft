package k2b6s9j.BoatCraft.boat

import k2b6s9j.BoatCraft.boat.Boat.{EntityCustomBoat, RenderBoat}
import net.minecraft.world.World
import net.minecraft.item.ItemStack
import net.minecraft.block.Block

object Materials {

  object Entity {

    trait Entity extends EntityCustomBoat {

      def Entity(world: World){
        super.world
      }

      def Entity(world: World, x: Double, y: Double, z: Double) {
        super.Entity(world, x, y, z)
      }

      override def isCustomBoat(): Boolean = {
        true
      }

    }

    object Wood {

      trait Oak extends EntityCustomBoat with Entity {

        override def customPlank(): ItemStack = {
          new ItemStack(Block.plank, 1, 0)
        }

      }

      trait Spruce extends EntityCustomBoat with Entity {

        override def customPlank(): ItemStack = {
          new ItemStack(Block.plank, 1, 1)
        }

      }

      trait Birch extends EntityCustomBoat with Entity {

        override def customPlank(): ItemStack = {
          new ItemStack(Block.plank, 1, 2)
        }

      }

      trait Jungle extends EntityCustomBoat with Entity {

        override def customPlank(): ItemStack = {
          new ItemStack(Block.plank, 1, 3)
        }

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