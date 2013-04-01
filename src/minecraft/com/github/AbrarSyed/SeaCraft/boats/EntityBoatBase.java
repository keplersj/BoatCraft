package com.github.AbrarSyed.SeaCraft.boats;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.SeaCraft;
import com.github.AbrarSyed.SeaCraft.api.SeaCraftAPI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class EntityBoatBase extends Entity
{
	// client stuff that makes no sense.
	private double			boatX;
	private double			boatY;
	private double			boatZ;
	private double			boatYaw;
	private double			boatPitch;

	@SideOnly(Side.CLIENT)
	private double			velocityX;
	@SideOnly(Side.CLIENT)
	private double			velocityY;
	@SideOnly(Side.CLIENT)
	private double			velocityZ;

	public EntityBoatBase	hitched;

	public EntityBoatBase(World par1World)
	{
		super(par1World);
		preventEntitySpawning = true;
		setSize(1.5F, 0.6F);
		yOffset = height / 2.0F;
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void entityInit()
	{
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Float(0));
		dataWatcher.addObject(19, new Integer(0));
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
	 * pushable on contact, like boats or minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return boundingBox;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		return true;
	}

	public EntityBoatBase(World par1World, double par2, double par4, double par6)
	{
		this(par1World);
		setPosition(par2, par4 + yOffset, par6);
		motionX = 0.0D;
		motionY = 0.0D;
		motionZ = 0.0D;
		prevPosX = par2;
		prevPosY = par4;
		prevPosZ = par6;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	@Override
	public double getMountedYOffset()
	{
		return height * 0.0D - 0.30000001192092896D;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
	{
		if (isEntityInvulnerable())
			return false;
		else if (!worldObj.isRemote && !isDead)
		{
			setTimeSinceHit(10);
			setDamageTaken(getDamageTaken() + par2 * 10);
			setBeenAttacked();
			boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;

			if (flag || getDamageTaken() > getMaxDamage())
			{
				if (riddenByEntity != null)
				{
					riddenByEntity.mountEntity(this);
				}

				if (!flag)
				{
					dropItemsOnBreak();
				}

				setDead();
			}

			return true;
		}
		else
			return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	public void performHurtAnimation()
	{
		setTimeSinceHit(10);
		setDamageTaken(getDamageTaken() * 11);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		return !isDead;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int par9)
	{
		if (riddenByEntity != null)
		{
			double d3 = x - posX;
			double d4 = y - posY;
			double d5 = z - posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D)
				return;
		}

		boatX = x;
		boatY = y;
		boatZ = z;
		boatYaw = yaw;
		boatPitch = pitch;
		motionX = velocityX;
		motionY = velocityY;
		motionZ = velocityZ;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5)
	{
		velocityX = motionX = par1;
		velocityY = motionY = par3;
		velocityZ = motionZ = par5;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (getTimeSinceHit() > 0)
		{
			setTimeSinceHit(getTimeSinceHit() - 1);
		}

		if (getDamageTaken() > 0)
		{
			setDamageTaken(getDamageTaken() - 1);
		}

		// ste previous
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;

		// check on water part.
		// over water ammount
		double waterFloor = 0.0D;
		{
			for (int i = 0; i < 5; ++i)
			{
				double d1 = boundingBox.minY + (boundingBox.maxY - boundingBox.minY) * (i + 0) / 5 - 0.125D;
				double d2 = boundingBox.minY + (boundingBox.maxY - boundingBox.minY) * (i + 1) / 5 - 0.125D;
				AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(boundingBox.minX, d1, boundingBox.minZ, boundingBox.maxX, d2, boundingBox.maxZ);

				if (worldObj.isAABBInMaterial(axisalignedbb, Material.water))
				{
					waterFloor += 1.0 / 5;
				}
			}
		}

		// pythag
		double headingVelocity = Math.sqrt(motionX * motionX + motionZ * motionZ);

		double headingX = Math.cos(rotationYaw * Math.PI / 180.0D); // in radians
		double headingZ = Math.sin(rotationYaw * Math.PI / 180.0D); // in radians

		// Make Splashes
		if (headingVelocity > getMinSplashSpeed())
		{

			for (int j = 0; j < 1.0D + headingVelocity * 60.0D; ++j)
			{
				double d6 = rand.nextFloat() * 2.0F - 1.0F;
				double d7 = (rand.nextInt(2) * 2 - 1) * 0.7D;
				double particleX;
				double particleZ;

				// random? wut?
				if (rand.nextBoolean())
				{
					particleX = posX - headingX * d6 * 0.8D + headingZ * d7;
					particleZ = posZ - headingZ * d6 * 0.8D - headingX * d7;
					worldObj.spawnParticle("splash", particleX, posY - 0.125D, particleZ, motionX, motionY, motionZ);
				}
				else
				{
					particleX = posX + headingX + headingZ * d6 * 0.7D;
					particleZ = posZ + headingZ - headingX * d6 * 0.7D;
					worldObj.spawnParticle("splash", particleX, posY - 0.125D, particleZ, motionX, motionY, motionZ);
				}
			}
		}

		calcMotion(waterFloor);

		double groundMotion = motionX * motionX + motionZ + motionZ;

		if (isCollidedHorizontally && groundMotion > getCrashSpeed())
		{
			if (!worldObj.isRemote)
			{
				setDead();
				dropItemsOnCrash();
			}
		}
		else
		{
			setVelocityForDrag();
		}

		if (!worldObj.isRemote)
		{
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
			int l;

			if (list != null && !list.isEmpty())
			{
				for (l = 0; l < list.size(); ++l)
				{
					Entity entity = (Entity) list.get(l);

					if (entity != riddenByEntity && entity.canBePushed() && entity instanceof EntityBoatBase)
					{
						entity.applyEntityCollision(this);
					}
				}
			}

			// check Block Collisions.
			for (l = 0; l < 4; ++l)
			{
				int hitx = MathHelper.floor_double(posX + (l % 2 - 0.5D) * 0.8D);
				int hitz = MathHelper.floor_double(posZ + (l / 2 - 0.5D) * 0.8D);

				for (int k1 = 0; k1 < 2; ++k1)
				{
					int hity = MathHelper.floor_double(posY) + k1;
					int blockID = worldObj.getBlockId(hitx, hity, hitz);

					if (SeaCraftAPI.isBlockDestroyable(blockID))
					{
						ItemStack stack = SeaCraftAPI.getDestroyableDrop(blockID);
						if (stack != null)
						{
							worldObj.setBlockToAir(hitx, hity, hitz);
							entityDropItem(stack, 1);
						}
					}
				}
			}

			if (riddenByEntity != null && riddenByEntity.isDead)
			{
				riddenByEntity = null;
			}
		}
	}

	private void calcMotion(double waterFloor)
	{
		// set ridding controls.
		if (this.calcPowerredMotion())
		{
		}
		else if (hitched != null)
		{
			double speed = Math.sqrt(hitched.motionX * hitched.motionX + hitched.motionZ * hitched.motionZ);

			Vec3 vector = Vec3.createVectorHelper(hitched.posX - this.posX, 0d, hitched.posZ - this.posZ).normalize();
			double headingX = vector.xCoord;
			double headingZ = vector.zCoord;
			
			{
				// new value
				float lookingAngle = (float) (270f - Math.atan2(headingX, headingZ) * 180.0D / Math.PI);
				float changed = (float) MathHelper.wrapAngleTo180_double(lookingAngle - rotationYaw);

				// get value changed
				//float changed = lookingAngle - rotationYaw;

				if (changed > getMaxRotationChange())
					changed = getMaxRotationChange();
				else if (changed < -getMaxRotationChange())
					changed = -getMaxRotationChange();

				lookingAngle = this.rotationYaw + changed;

				rotationYaw = lookingAngle;
				this.setRotation(lookingAngle, rotationPitch);
				this.setRotation(lookingAngle);

				double rotation = Math.toRadians(lookingAngle);

				headingX = -Math.cos(rotation);
				headingZ = -Math.sin(rotation);
			}
			
			motionX = speed * headingX;
			motionZ = speed * headingZ;
		}

		// verify gravity.
		if (waterFloor < 1.0D)
		{
			double num = waterFloor * 2.0D - 1.0D;
			motionY += 0.03999999910593033D * num;
		}
		else
		{
			if (motionY < 0.0D)
			{
				motionY /= 2.0D;
			}

			motionY += 0.007000000216066837D;
		}

		if (onGround)
		{
			setGroundDrag();
		}

		moveEntity(motionX, motionY, motionZ);
	}
	
	/**
	 * @param rider MAY BE NULL!
	 */
	protected boolean calcPowerredMotion()
	{
		// set ridding controls.
		if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
		{
			//this.rotationYaw = this.riddenByEntity.rotationYaw;
			EntityPlayer rider = (EntityPlayer) riddenByEntity;

			double headingX = rider.getLookVec().xCoord; // in radians
			double headingZ = rider.getLookVec().zCoord; // in radians

			{
				// new value
				float lookingAngle = (float) (270f - Math.atan2(headingX, headingZ) * 180.0D / Math.PI);
				float changed = (float) MathHelper.wrapAngleTo180_double(lookingAngle - rotationYaw);

				// get value changed
				//float changed = lookingAngle - rotationYaw;

				if (changed > getMaxRotationChange())
					changed = getMaxRotationChange();
				else if (changed < -getMaxRotationChange())
					changed = -getMaxRotationChange();

				lookingAngle = this.rotationYaw + changed;

				rotationYaw = lookingAngle;
				this.riddenByEntity.prevRotationYaw = this.riddenByEntity.rotationYaw -= changed;
				this.setRotation(lookingAngle, rotationPitch);
				this.setRotation(lookingAngle);

				double rotation = Math.toRadians(lookingAngle);

				headingX = -Math.cos(rotation);
				headingZ = -Math.sin(rotation);
			}

			motionX = getPoweredSpeed() * headingX;
			motionZ = getPoweredSpeed() * headingZ;
			return true;
		}
		
		return false;
	}

	private final double calcDragForce()
	{
		return 0;
	}

	protected void setVelocityForDrag()
	{
		motionX *= 0.9900000095367432D;
		motionY *= 0.949999988079071D;
		motionZ *= 0.9900000095367432D;
	}

	protected final void setGroundDrag()
	{
		motionX *= 0.5D;
		motionY *= 0.5D;
		motionZ *= 0.5D;
	}

	@Override
	public void updateRiderPosition()
	{
		if (riddenByEntity != null)
		{
			double d0 = Math.cos(rotationYaw * Math.PI / 180.0D) * 0.4D;
			double d1 = Math.sin(rotationYaw * Math.PI / 180.0D) * 0.4D;
			riddenByEntity.setPosition(posX + d0, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ + d1);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected abstract void writeEntityToNBT(NBTTagCompound par1NBTTagCompound);

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected abstract void readEntityFromNBT(NBTTagCompound par1NBTTagCompound);

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public final boolean interact(EntityPlayer player)
	{
		// do check for hitch
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == SeaCraft.hitch.itemID)
		{
			ItemStack stack = player.getCurrentEquippedItem();
			NBTTagCompound nbt = stack.stackTagCompound;
			if (nbt == null)
			{
				nbt = new NBTTagCompound();
				nbt.setInteger("entity", this.entityId);
			}
			else
			{
				int id = nbt.getInteger("entity");
				if (id == this.entityId)
				{
					// rule it out.
				}
				else if (id == 0)
				{
					nbt.setInteger("entity", this.entityId);
				}
				else
				{
					EntityBoatBase other = (EntityBoatBase) this.worldObj.getEntityByID(id);
					other.hitched = this;
					
					if (player.capabilities.isCreativeMode)
						stack.stackSize--;
					
					nbt = null;
				}
			}
			stack.setTagCompound(nbt);
			return true;
		}

		if (playerInteract(player))
			return true;

		// already has something riding? DENIED
		if (riddenByEntity != null &&
				// ridden by player.
				riddenByEntity instanceof EntityPlayer &&
				// ridden by player thats not this player
				riddenByEntity != player)
			return true;
		else if (isMountableByPlayer())
		{
			if (!worldObj.isRemote)
			{
				player.mountEntity(this);
			}

			return true;
		}

		return true;
	}

	public boolean playerInteract(EntityPlayer player)
	{
		return false;
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public final void setDamageTaken(int par1)
	{
		dataWatcher.updateObject(19, Integer.valueOf(par1));
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public final int getDamageTaken()
	{
		return dataWatcher.getWatchableObjectInt(19);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public final void setTimeSinceHit(int par1)
	{
		dataWatcher.updateObject(17, Integer.valueOf(par1));
	}

	/**
	 * Gets the time since the last hit.
	 */
	public final int getTimeSinceHit()
	{
		return dataWatcher.getWatchableObjectInt(17);
	}

	/**
	 * Sets the forward direction of the entity.
	 */
	public final void setRotation(float rot)
	{
		dataWatcher.updateObject(18, Float.valueOf(rot));
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public final float getRotation()
	{
		return dataWatcher.getWatchableObjectInt(18);
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
	public abstract double getPoweredSpeed();

	/**
	 * get crash speed. vanilla is .2
	 * @return
	 */
	public abstract double getCrashSpeed();

	public abstract float getMaxRotationChange();
}
