package k2b6s9j.BoatCraft.entity.item;

import java.util.List;

import k2b6s9j.BoatCraft.item.boat.BoatOak;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityCustomBoat extends EntityBoat
{
    private boolean field_70279_a;
    public String material;
    
    public EntityCustomBoat(World par1World)
    {
        super(par1World);
    }

	public EntityCustomBoat(World par1World, double par2, double par4,double par6) {
		super(par1World, par2, par4, par6);
	}
    
    public EntityCustomBoat(World par1World, double par2, double par4, double par6, String material) {
    	super(par1World, par2, par4, par6);
    	this.material = material;
	}

	/**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0F);
            this.setBeenAttacked();
            boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.getEntity()).capabilities.isCreativeMode;

            if (flag || this.getDamageTaken() > 40.0F)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                if (!flag)
                {
                	if (isCustomBoat())
                	{
                        this.dropItemWithOffset(customBoatItem(), 1, 0.0F);
                	}
                	else
                	{
                		this.dropItemWithOffset(BoatOak.shiftedID, 1, 0.0F);
                	}
                }

                this.setDead();
            }

            return true;
        }
        else
        {
            return true;
        }
    }

    public boolean isCustomBoat()
    {
    	return false;
    }
    
    public int customBoatItem()
    {
    	return BoatOak.shiftedID;
    }
    
    public boolean func_130002_c(EntityPlayer player)
    {
    	//Do not mount if the player is shift clicking
    	if (player.isSneaking())
    	{
    		return false;
    	}
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
        {
            return true;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                player.mountEntity(this);
            }

            return true;
        }
    }

    public Block getDisplayTile()
    {
        if (!this.hasDisplayTile())
        {
            return this.getDefaultDisplayTile();
        }
        else
        {
            int i = this.getDataWatcher().getWatchableObjectInt(20) & 65535;
            return i > 0 && i < Block.blocksList.length ? Block.blocksList[i] : null;
        }
    }

    public Block getDefaultDisplayTile()
    {
        return null;
    }

    public int getDisplayTileData()
    {
        return !this.hasDisplayTile() ? this.getDefaultDisplayTileData() : this.getDataWatcher().getWatchableObjectInt(20) >> 16;
    }

    public int getDefaultDisplayTileData()
    {
        return 0;
    }

    public int getDisplayTileOffset()
    {
        return !this.hasDisplayTile() ? this.getDefaultDisplayTileOffset() : this.getDataWatcher().getWatchableObjectInt(21);
    }

    public int getDefaultDisplayTileOffset()
    {
        return 6;
    }
    
    public void setDisplayTile(int par1)
    {
        this.getDataWatcher().updateObject(20, Integer.valueOf(par1 & 65535 | this.getDisplayTileData() << 16));
        this.setHasDisplayTile(true);
    }

    public void setDisplayTileData(int par1)
    {
        Block block = this.getDisplayTile();
        int j = block == null ? 0 : block.blockID;
        this.getDataWatcher().updateObject(20, Integer.valueOf(j & 65535 | par1 << 16));
        this.setHasDisplayTile(true);
    }

    public void setDisplayTileOffset(int par1)
    {
        this.getDataWatcher().updateObject(21, Integer.valueOf(par1));
        this.setHasDisplayTile(true);
    }

    public boolean hasDisplayTile()
    {
        return false;
    }

    public void setHasDisplayTile(boolean par1)
    {
        this.getDataWatcher().updateObject(22, Byte.valueOf((byte)(par1 ? 1 : 0)));
    }
    
    @SideOnly(Side.CLIENT)
    public void func_70270_d(boolean par1)
    {
        this.field_70279_a = par1;
    }
}
