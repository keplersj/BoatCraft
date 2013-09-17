package k2b6s9j.BoatCraft.entity.boat.wood.spruce;

import java.util.List;
import java.util.logging.Level;

import k2b6s9j.BoatCraft.entity.boat.EntityBoatContainer;
import k2b6s9j.BoatCraft.item.boat.wood.spruce.BoatSpruceHopper;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.Hopper;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;

public class EntityBoatSpruceHopper extends EntityBoatContainer implements Hopper {
	
	private int transferTicker = -1;
	public BoatSpruceHopper item;
	
	public EntityBoatSpruceHopper(World par1World)
    {
        super(par1World);
    }

    public EntityBoatSpruceHopper(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    @Override
    public boolean doesBoatContainBlock()
    {
    	return true;
    }
    
    @Override
    public ItemStack blockInBoat()
    {
    	return new ItemStack(Block.hopperBlock, 1, 0);
    }

    public int getBoatType()
    {
        return 5;
    }

    public Block getDefaultDisplayTile()
    {
        return Block.hopperBlock;
    }

    public int getDefaultDisplayTileOffset()
    {
        return 1;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return 5;
    }

    public boolean func_130002_c(EntityPlayer par1EntityPlayer)
    {
        if (!this.worldObj.isRemote)
        {
            //par1EntityPlayer.displayGUIHopper(this);
        	FMLLog.log(Level.INFO, "A Hopper GUI should really be showing by now, I'll fix it later.");
        }

        return true;
    }
    
    /**
     * Returns the worldObj for this tileEntity.
     */
    public World getWorldObj()
    {
        return this.worldObj;
    }

    /**
     * Gets the world X position for this hopper entity.
     */
    public double getXPos()
    {
        return this.posX;
    }

    /**
     * Gets the world Y position for this hopper entity.
     */
    public double getYPos()
    {
        return this.posY;
    }

    /**
     * Gets the world Z position for this hopper entity.
     */
    public double getZPos()
    {
        return this.posZ;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote && this.isEntityAlive() )
        {
            --this.transferTicker;

            if (!this.canTransfer())
            {
                this.setTransferTicker(0);

                if (this.func_96112_aD())
                {
                    this.setTransferTicker(4);
                    this.onInventoryChanged();
                }
            }
        }
    }

    public boolean func_96112_aD()
    {
        if (TileEntityHopper.suckItemsIntoHopper(this))
        {
            return true;
        }
        else
        {
            List list = this.worldObj.selectEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(0.25D, 0.0D, 0.25D), IEntitySelector.selectAnything);

            if (list.size() > 0)
            {
                TileEntityHopper.func_96114_a(this, (EntityItem)list.get(0));
            }

            return false;
        }
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("TransferCooldown", this.transferTicker);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.transferTicker = par1NBTTagCompound.getInteger("TransferCooldown");
    }

    /**
     * Sets the transfer ticker, used to determine the delay between transfers.
     */
    public void setTransferTicker(int par1)
    {
        this.transferTicker = par1;
    }

    /**
     * Returns whether the hopper cart can currently transfer an item.
     */
    public boolean canTransfer()
    {
        return this.transferTicker > 0;
    }

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isCustomBoat()
    {
    	return true;
    }
    
	@Override
	public boolean useItemID()
	{
		return true;
	}
    
	@Override
    public int customBoatItemID()
    {
    	return item.shiftedID;
    }
}
