package com.github.AbrarSyed.SeaCraft;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBoatKayak extends Entity
{

	public EntityBoatKayak(World par1World)
	{
		super(par1World);
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 0.6F);
		this.yOffset = 0.3f;
	}

	public EntityBoatKayak(World par1World, double x, double y, double z)
	{
		this(par1World);
		this.setPosition(x, y + (double) this.yOffset, z);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Integer(0));
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	@Override
	public boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public double getMountedYOffset()
	{
		return (double) this.height * 0.0D - 0.30000001192092896D;
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		// already riding.
		// ridden by a player
		// ridden by not this player
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

	@Override
	public boolean attackEntityFrom(DamageSource source, int ammount)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}

		if (this.isDead)
		{
			return false;
		}
		
		// TODO: make it have health.

		if (!worldObj.isRemote)
		{
			this.dropItemWithOffset(SeaCraft.kayak.itemID, 1, 0.0F);
			this.setDead();
		}

		return true;
	}

	@Override
	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double d0 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        if (onGround)
        {
        	motionX = motionZ = 0;
        	motionY = 5;
        }
        else
        {
        	motionY = -.2;
        }
        
        
        // using the motion.
        posX += motionX;
        posY += motionY;
        posZ += motionZ;
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}

}
