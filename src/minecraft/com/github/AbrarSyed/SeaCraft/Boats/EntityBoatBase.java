package com.github.AbrarSyed.SeaCraft.Boats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import com.github.AbrarSyed.SeaCraft.api.SeaCraftAPI;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumEntitySize;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityBoatBase extends Entity
{
	private double	speedMultiplier;
	private int		boatPosRotationIncrements;
	
	// client stuff that makes no sense.
	private double	boatX;
	private double	boatY;
	private double	boatZ;
	private double	boatYaw;
	private double	boatPitch;
	
	@SideOnly(Side.CLIENT)
	private double	velocityX;
	@SideOnly(Side.CLIENT)
	private double	velocityY;
	@SideOnly(Side.CLIENT)
	private double	velocityZ;

	public EntityBoatBase(World par1World)
	{
		super(par1World);
		this.speedMultiplier = 0.07D;
		this.preventEntitySpawning = true;
		this.setSize(1.5F, 0.6F);
		this.yOffset = this.height / 2.0F;
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return false;
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Float(0));
		this.dataWatcher.addObject(19, new Integer(0));
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
	 * pushable on contact, like boats or minecarts.
	 */
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return true;
	}

	public EntityBoatBase(World par1World, double par2, double par4, double par6)
	{
		this(par1World);
		this.setPosition(par2, par4 + (double) this.yOffset, par6);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	public double getMountedYOffset()
	{
		return (double) this.height * 0.0D - 0.30000001192092896D;
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + par2 * 10);
			this.setBeenAttacked();
			boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;

			if (flag || this.getDamageTaken() > getMaxDamage())
			{
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				if (!flag)
				{
					this.dropItemsOnBreak();
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

	@SideOnly(Side.CLIENT)
	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	public void performHurtAnimation()
	{
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
	{
		if (this.riddenByEntity == null)
		{
			this.boatPosRotationIncrements = par9 + 5;
		}
		else
		{
			double d3 = x - this.posX;
			double d4 = y - this.posY;
			double d5 = z - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D)
			{
				return;
			}

			this.boatPosRotationIncrements = 3;
		}

		this.boatX = x;
		this.boatY = y;
		this.boatZ = z;
		this.boatYaw = (double) yaw;
		this.boatPitch = (double) pitch;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5)
	{
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		
		if (this.getTimeSinceHit() > 0)
		{
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0)
		{
			this.setDamageTaken(this.getDamageTaken() - 1);
		}
		
		// ste previous
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		// check on water part.
		// over water ammount
		double waterFloor = 0.0D;
		{	
			for (int i = 0; i < 5; ++i)
			{
				double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (i + 0) / 5 - 0.125D;
				double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double) (i + 1) / 5 - 0.125D;
				AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);

				if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
				{
					waterFloor += 1.0 / 5;
				}
			}
		}
		
		// pythag
		double headingVelocity = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		
		double headingX = Math.cos((double) this.rotationYaw * Math.PI / 180.0D); // in radians
		double headingZ = Math.sin((double) this.rotationYaw * Math.PI / 180.0D); // in radians
		
		// Make Splashes
		if (headingVelocity > getMinSplashSpeed())
		{

			for (int j = 0; (double) j < 1.0D + headingVelocity * 60.0D; ++j)
			{
				double d6 = (double) (this.rand.nextFloat() * 2.0F - 1.0F);
				double d7 = (double) (this.rand.nextInt(2) * 2 - 1) * 0.7D;
				double particleX;
				double particleZ;

				// random? wut?
				if (this.rand.nextBoolean())
				{
					particleX = this.posX - headingX * d6 * 0.8D + headingZ * d7;
					particleZ = this.posZ - headingZ * d6 * 0.8D - headingX * d7;
					this.worldObj.spawnParticle("splash", particleX, this.posY - 0.125D, particleZ, this.motionX, this.motionY, this.motionZ);
				}
				else
				{
					particleX = this.posX + headingX + headingZ * d6 * 0.7D;
					particleZ = this.posZ + headingZ - headingX * d6 * 0.7D;
					this.worldObj.spawnParticle("splash", particleX, this.posY - 0.125D, particleZ, this.motionX, this.motionY, this.motionZ);
				}
			}
		}
		
		// set ridding controls.
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			//this.rotationYaw = this.riddenByEntity.rotationYaw;
			EntityPlayer rider = (EntityPlayer) riddenByEntity;
			
			headingX = rider.getLookVec().normalize().xCoord; // in radians
			headingZ = rider.getLookVec().normalize().zCoord; // in radians
			
			{
				float val = (float) (270f - ((Math.atan2(headingX, headingZ)) * 180.0D / Math.PI));
				val = (float) MathHelper.wrapAngleTo180_double(val);
				//val += this.rotationYaw;
				
				rotationYaw = val;
				this.setRotation(val, this.rotationPitch);
				this.setRotation(val);
			}
			
			motionX = getCurrentSpeed()*headingX;
			motionZ = getCurrentSpeed()*headingZ;
		}
		
		// verify gravity.
		if (waterFloor < 1.0D)
		{
			double num = waterFloor * 2.0D - 1.0D;
			this.motionY += 0.03999999910593033D * num;
		}
		else
		{
			if (this.motionY < 0.0D)
			{
				this.motionY /= 2.0D;
			}

			this.motionY += 0.007000000216066837D;
		}
		
		if (this.onGround)
		{
			setGroundDrag();
		}
		
		double groundMotion = motionX * motionX + motionZ + motionZ;
		
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		
		if (this.isCollidedHorizontally && groundMotion > getCrashSpeed())
		{
			if (!this.worldObj.isRemote)
			{
				this.setDead();
				this.dropItemsOnCrash();
			}
		}
		else
		{
			setVelocityForDrag();
		}
		
		if (!this.worldObj.isRemote)
		{
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
			int l;

			if (list != null && !list.isEmpty())
			{
				for (l = 0; l < list.size(); ++l)
				{
					Entity entity = (Entity) list.get(l);

					if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoatBase)
					{
						entity.applyEntityCollision(this);
					}
				}
			}

			// check Block Collisions.
			for (l = 0; l < 4; ++l)
			{
				int hitx = MathHelper.floor_double(this.posX + ((double) (l % 2) - 0.5D) * 0.8D);
				int hitz = MathHelper.floor_double(this.posZ + ((double) (l / 2) - 0.5D) * 0.8D);

				for (int k1 = 0; k1 < 2; ++k1)
				{
					int hity = MathHelper.floor_double(this.posY) + k1;
					int blockID = this.worldObj.getBlockId(hitx, hity, hitz);

					if (SeaCraftAPI.isBlockDestroyable(blockID))
					{
						ItemStack stack  = SeaCraftAPI.getDestroyableDrop(blockID);
						if (stack != null)
						{
							this.worldObj.setBlockToAir(hitx, hity, hitz);
							this.entityDropItem(stack, 1);
						}
					}
				}
			}

			if (this.riddenByEntity != null && this.riddenByEntity.isDead)
			{
				this.riddenByEntity = null;
			}
		}
	}
	
	private final double calcDragForce()
	{
		return 0;
	}
	
	protected void setVelocityForDrag()
	{
        this.motionX *= 0.9900000095367432D;
        this.motionY *= 0.949999988079071D;
        this.motionZ *= 0.9900000095367432D;
	}
	
	private final void setGroundDrag()
	{
		this.motionX *= 0.5D;
		this.motionY *= 0.5D;
		this.motionZ *= 0.5D;
	}

	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double d0 = Math.cos((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin((double) this.rotationYaw * Math.PI / 180.0D) * 0.4D;
			this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected abstract void writeEntityToNBT(NBTTagCompound par1NBTTagCompound);

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected abstract void readEntityFromNBT(NBTTagCompound par1NBTTagCompound);

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		// already has something riding? DENIED
		if (this.riddenByEntity != null &&
				// ridden by player.
				this.riddenByEntity instanceof EntityPlayer &&
				// ridden by player thats not this player
				this.riddenByEntity != par1EntityPlayer)
		{
			return true;
		}
		else if (this.isMountableByPlayer())
		{
			if (!this.worldObj.isRemote)
			{
				par1EntityPlayer.mountEntity(this);
			}

			return true;
		}
		
		return true;
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public final void setDamageTaken(int par1)
	{
		this.dataWatcher.updateObject(19, Integer.valueOf(par1));
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public final int getDamageTaken()
	{
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public final void setTimeSinceHit(int par1)
	{
		this.dataWatcher.updateObject(17, Integer.valueOf(par1));
	}

	/**
	 * Gets the time since the last hit.
	 */
	public final int getTimeSinceHit()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}
	
	/**
	 * Sets the forward direction of the entity.
	 */
	public final void setRotation(float rot)
	{
		this.dataWatcher.updateObject(18, Float.valueOf(rot));
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public final float getRotation()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}
	
	
	// start abstracts
	// - ----------------------------------------------------------------------------------
	
	/**
	 * Called when the boat is broken.
	 * Spawn drops here.
	 */
	public abstract void dropItemsOnBreak();
	
	/**
	 * Called when the boat crashes horizontally.
	 * Spawn drops here.
	 */
	public abstract void dropItemsOnCrash();
	
	/**
	 * If the Player can mount this boat.
	 * @return
	 */
	public abstract boolean isMountableByPlayer();
	
	/**
	 * all things on boat + the boats weight.
	 * @return
	 */
	public abstract double getCurrentWeight();
	
	/**
	 * The weight of the boat empty.
	 * Vanilla is 1
	 * @return
	 */
	public abstract int getBaseWeight();
	
	/**
	 * Vanilla boats are 40
	 * @return
	 */
	public abstract int getMaxDamage();
	
	/**
	 * Vanilla boats are 1.
	 * The ammount of damage to remove each tick.
	 * Players do 10 per hit.
	 * @return
	 */
	public abstract int getRegenRate();
	
	/**
	 * Vanilla is .333
	 * @return
	 */
	public abstract double getMinSplashSpeed();
	
	/**
	 * vanilla is .35
	 * @return
	 */
	public abstract double getMaxSpeed();
	
	/**
	 * get speed.
	 * @return
	 */
	public abstract double getCurrentSpeed();
	
	/**
	 * get crash speed. vanilla is .2
	 * @return
	 */
	public abstract double getCrashSpeed();
}
