package k2b6s9j.BoatCraft.boat

import k2b6s9j.BoatCraft.boat.Boat.{EntityBoatContainer, EntityCustomBoat}
import k2b6s9j.BoatCraft.utilities.log.ModLogger
import net.minecraft.item.ItemStack
import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraft.tileentity.TileEntityHopper
import net.minecraft.entity.item.EntityItem
import net.minecraft.command.IEntitySelector
import net.minecraft.nbt.NBTTagCompound

object Modifiers {

  object Entity {

    trait Block extends EntityCustomBoat {

      override def doesBoatContainBlock(): Boolean = {
        true
      }

      override def func_130002_c(player: EntityPlayer): Boolean = {
        //Do not mount this boat! It contains a block!
        false
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

      override def blockInBoat(): ItemStack = {
        new ItemStack(Block.furnaceIdle, 1, 0)
      }

      override def getDefaultDisplayTile(): net.minecraft.block.Block = {
        Block.furnaceBurning
      }

      override def getDefaultDisplayTileOffset(): Int = {
        2
      }

    }

    trait TNT extends EntityCustomBoat with Block {

      override def blockInBoat(): ItemStack = {
        new ItemStack(Block.tnt, 1, 0)
      }

      override def getDefaultDisplayTile(): net.minecraft.block.Block = {
        Block.tnt
      }

    }

    trait Hopper extends EntityBoatContainer with Block with net.minecraft.tileentity.Hopper {

      var transferTicker: Int = -1

      override def blockInBoat(): ItemStack = {
        new ItemStack(Block.hopperBlock, 1, 0)
      }

      override def getDefaultDisplayTile(): net.minecraft.block.Block = {
        Block.hopperBlock
      }

      override def getDefaultDisplayTileOffset(): Int = {
        1
      }

      /**
       * Returns the number of slots in the inventory.
       */
      override def getSizeInventory(): Int = {
        5
      }

      override def func_130002_c(par1EntityPlayer: EntityPlayer): Boolean = {
        if (!this.worldObj.isRemote) {
          //par1EntityPlayer.displayGUIHopper(this);
          ModLogger.ifo("A Hopper GUI should really be showing by now, I'll fix it later.")
        }

        true
      }

      /**
       * Returns the worldObj for this tileEntity.
       */
      override def getWorldObj(): World = {

        this.worldObj

      }

      /**
       * Gets the world X position for this hopper entity.
       */
      override def getXPos(): Double = {

        this.posX

      }

      /**
       * Gets the world Y position for this hopper entity.
       */
      override def getYPos(): Double = {

        this.posY

      }

      /**
       * Gets the world Z position for this hopper entity.
       */
      override def getZPos(): Double = {

        this.posZ

      }

      /**
       * Called to update the entity's position/logic.
       */
      override def onUpdate() {
        super.onUpdate()

        if (!this.worldObj.isRemote && this.isEntityAlive)
        {
          this.transferTicker

          if (!this.canTransfer())
          {
            this.setTransferTicker(0)

            if (this.func_96112_aD())
            {
              this.setTransferTicker(4)
              this.onInventoryChanged()
            }
          }
        }
      }

      override def func_96112_aD(): Boolean = {
        if (TileEntityHopper.suckItemsIntoHopper(this))
        {
          true
        }
        else
        {
          var list: List = this.worldObj.selectEntitiesWithinAABB(EntityItem, this.boundingBox.expand(0.25D, 0.0D, 0.25D), IEntitySelector.selectAnything);

          if (list.size() > 0)
          {
            TileEntityHopper.func_96114_a(this, (EntityItem)list.get(0))
          }

          false
        }
      }

      /**
       * (abstract) Protected helper method to write subclass entity data to NBT.
       */
      override def writeEntityToNBT(par1NBTTagCompound: NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound)
        par1NBTTagCompound.setInteger("TransferCooldown", this.transferTicker)
      }

      /**
       * (abstract) Protected helper method to read subclass entity data from NBT.
       */
      override def readEntityFromNBT(par1NBTTagCompound: NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound)
        this.transferTicker = par1NBTTagCompound.getInteger("TransferCooldown")
      }

      /**
       * Sets the transfer ticker, used to determine the delay between transfers.
       */
      override def setTransferTicker(par1: Int) {
        this.transferTicker = par1
      }

      /**
       * Returns whether the hopper cart can currently transfer an item.
       */
      override def canTransfer(): Boolean = {
        this.transferTicker > 0
      }

      override def isInvNameLocalized(): Boolean = {
        false
      }

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