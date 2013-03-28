package com.github.AbrarSyed.SeaCraft;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityBoatKayak extends Entity
{

	private String	rider;

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
        this.setPosition(x, y + (double)this.yOffset, z);
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
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		rider = nbt.getString("rider");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setString("rider", rider);
	}

}
