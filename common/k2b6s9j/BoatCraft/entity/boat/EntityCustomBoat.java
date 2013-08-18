package k2b6s9j.BoatCraft.entity.boat;

import java.util.List;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class EntityCustomBoat extends EntityBoat implements IInventory
{
    public boolean field_70279_a;
	private double speedMultiplier;
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	
	//Container Boat Fields
	private ItemStack[] boatContainerItems = new ItemStack[36];
	private boolean dropContentsWhenDead = true;

	public EntityCustomBoat(World par1World)
    {
        super(par1World);
    }

    public EntityCustomBoat(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
    
    public EntityCustomBoat(World par2World, double d, double e, double f, String type) {
		super(par2World, d, e, f);
		FMLLog.log(Level.INFO, "A " + type + " boat was placed");
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
                		if (useItemID())
                		{
                			this.dropItemWithOffset(customBoatItemID(), 1, 0.0F);
                		}
                		else
                		{
                			this.entityDropItem(customBoatItem(), 0.0F);
                		}
                	}
                	else
                	{
                		//this.dropItemWithOffset(BoatOak.shiftedID, 1, 0.0F);
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

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i)
        {
            double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 0) / (double)b0 - 0.125D;
            double d2 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 1) / (double)b0 - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d2, this.boundingBox.maxZ);

            if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                d0 += 1.0D / (double)b0;
            }
        }

        double d3 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double d4;
        double d5;

        if (d3 > 0.26249999999999996D)
        {
            d4 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
            d5 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);

            for (int j = 0; (double)j < 1.0D + d3 * 60.0D; ++j)
            {
                double d6 = (double)(this.rand.nextFloat() * 2.0F - 1.0F);
                double d7 = (double)(this.rand.nextInt(2) * 2 - 1) * 0.7D;
                double d8;
                double d9;

                if (this.rand.nextBoolean())
                {
                    d8 = this.posX - d4 * d6 * 0.8D + d5 * d7;
                    d9 = this.posZ - d5 * d6 * 0.8D - d4 * d7;
                    this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
                }
                else
                {
                    d8 = this.posX + d4 + d5 * d6 * 0.7D;
                    d9 = this.posZ + d5 - d4 * d6 * 0.7D;
                    this.worldObj.spawnParticle("splash", d8, this.posY - 0.125D, d9, this.motionX, this.motionY, this.motionZ);
                }
            }
        }

        double d10;
        double d11;

        if (this.worldObj.isRemote && this.field_70279_a)
        {
            if (this.boatPosRotationIncrements > 0)
            {
                d4 = this.posX + (this.boatX - this.posX) / (double)this.boatPosRotationIncrements;
                d5 = this.posY + (this.boatY - this.posY) / (double)this.boatPosRotationIncrements;
                d11 = this.posZ + (this.boatZ - this.posZ) / (double)this.boatPosRotationIncrements;
                d10 = MathHelper.wrapAngleTo180_double(this.boatYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + d10 / (double)this.boatPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.boatPitch - (double)this.rotationPitch) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.setPosition(d4, d5, d11);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
            else
            {
                d4 = this.posX + this.motionX;
                d5 = this.posY + this.motionY;
                d11 = this.posZ + this.motionZ;
                this.setPosition(d4, d5, d11);

                if (this.onGround)
                {
                    this.motionX *= 0.5D;
                    this.motionY *= 0.5D;
                    this.motionZ *= 0.5D;
                }

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        }
        else
        {
            if (d0 < 1.0D)
            {
                d4 = d0 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d4;
            }
            else
            {
                if (this.motionY < 0.0D)
                {
                    this.motionY /= 2.0D;
                }

                this.motionY += 0.007000000216066837D;
            }

            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)
            {
                d4 = (double)((EntityLivingBase)this.riddenByEntity).moveForward;

                if (d4 > 0.0D)
                {
                    d5 = -Math.sin((double)(this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0F));
                    d11 = Math.cos((double)(this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0F));
                    this.motionX += d5 * this.speedMultiplier * 0.05000000074505806D;
                    this.motionZ += d11 * this.speedMultiplier * 0.05000000074505806D;
                }
            }

            d4 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

            if (d4 > 0.35D)
            {
                d5 = 0.35D / d4;
                this.motionX *= d5;
                this.motionZ *= d5;
                d4 = 0.35D;
            }

            if (d4 > d3 && this.speedMultiplier < 0.35D)
            {
                this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;

                if (this.speedMultiplier > 0.35D)
                {
                    this.speedMultiplier = 0.35D;
                }
            }
            else
            {
                this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

                if (this.speedMultiplier < 0.07D)
                {
                    this.speedMultiplier = 0.07D;
                }
            }

            if (this.onGround)
            {
                this.motionX *= 0.5D;
                this.motionY *= 0.5D;
                this.motionZ *= 0.5D;
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);

            if (this.isCollidedHorizontally && d3 > 0.2D)
            {
                if (!this.worldObj.isRemote && !this.isDead)
                {
                	this.setDead();
                    this.crashedDrops();
                }
            }
            else
            {
                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }

            this.rotationPitch = 0.0F;
            d5 = (double)this.rotationYaw;
            d11 = this.prevPosX - this.posX;
            d10 = this.prevPosZ - this.posZ;

            if (d11 * d11 + d10 * d10 > 0.001D)
            {
                d5 = (double)((float)(Math.atan2(d10, d11) * 180.0D / Math.PI));
            }

            double d12 = MathHelper.wrapAngleTo180_double(d5 - (double)this.rotationYaw);

            if (d12 > 20.0D)
            {
                d12 = 20.0D;
            }

            if (d12 < -20.0D)
            {
                d12 = -20.0D;
            }

            this.rotationYaw = (float)((double)this.rotationYaw + d12);
            this.setRotation(this.rotationYaw, this.rotationPitch);

            if (!this.worldObj.isRemote)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
                int l;

                if (list != null && !list.isEmpty())
                {
                    for (l = 0; l < list.size(); ++l)
                    {
                        Entity entity = (Entity)list.get(l);

                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat)
                        {
                            entity.applyEntityCollision(this);
                        }
                    }
                }

                for (l = 0; l < 4; ++l)
                {
                    int i1 = MathHelper.floor_double(this.posX + ((double)(l % 2) - 0.5D) * 0.8D);
                    int j1 = MathHelper.floor_double(this.posZ + ((double)(l / 2) - 0.5D) * 0.8D);

                    for (int k1 = 0; k1 < 2; ++k1)
                    {
                        int l1 = MathHelper.floor_double(this.posY) + k1;
                        int i2 = this.worldObj.getBlockId(i1, l1, j1);

                        if (i2 == Block.snow.blockID)
                        {
                            this.worldObj.setBlockToAir(i1, l1, j1);
                        }
                        else if (i2 == Block.waterlily.blockID)
                        {
                            this.worldObj.destroyBlock(i1, l1, j1, true);
                        }
                    }
                }

                if (this.riddenByEntity != null && this.riddenByEntity.isDead)
                {
                    this.riddenByEntity = null;
                }
            }
        }
    }

    public boolean isCustomBoat()
    {
    	return false;
    }
    
    public ItemStack customBoatItem()
    {
    	return new ItemStack(Item.boat, 1, 0);
    }
    
    public boolean useItemID()
    {
    	return false;
    }
    
    public int customBoatItemID()
    {
    	return Item.boat.itemID;
    }
    
    public ItemStack customPlank()
    {
    	return new ItemStack(Block.planks, 1, 0);
    }
    
    public ItemStack customStick()
    {
    	return new ItemStack(Item.stick, 1, 0);
    }
    
    public boolean doesBoatContainBlock()
    {
    	return false;
    }
    
    public ItemStack blockInBoat()
    {
    	return null;
    }
    
    public void crashedDrops()
    {
    	dropContents();
        int k;

        for (k = 0; k < 3; ++k)
        {
        	if (isCustomBoat())
        	{
        		this.entityDropItem(customPlank(), 0.0F);
        	}
        	else
        	{
        		this.dropItemWithOffset(Block.planks.blockID, 1, 0.0F);
        	}
        }

        for (k = 0; k < 2; ++k)
        {
        	if (isCustomBoat())
        	{
                this.entityDropItem(customStick(), 0.0F);
        	}
        	else
        	{
        		this.dropItemWithOffset(Item.stick.itemID, 1, 0.0F);
        	}
        }
        
        if (doesBoatContainBlock())
        {
        	this.entityDropItem(blockInBoat(), 0.0F);
        }
    }

    /*
     * This function is called with the Player action clicks on the Boat entity
     */
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
            	if (!hasDisplayTile()) {
            		player.mountEntity(this);
            	}
            	else {
            		
            	}
            }

            return true;
        }
    }
    
    /*
     * These classes below supply the information needed for Block in Boats
     */
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
    
    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int par1)
    {
        return this.boatContainerItems[par1];
    }
    
    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.boatContainerItems[par1] != null)
        {
            ItemStack itemstack;

            if (this.boatContainerItems[par1].stackSize <= par2)
            {
                itemstack = this.boatContainerItems[par1];
                this.boatContainerItems[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.boatContainerItems[par1].splitStack(par2);

                if (this.boatContainerItems[par1].stackSize == 0)
                {
                    this.boatContainerItems[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.boatContainerItems[par1] != null)
        {
            ItemStack itemstack = this.boatContainerItems[par1];
            this.boatContainerItems[par1] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.boatContainerItems[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    public void onInventoryChanged() {}

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.isDead ? false : par1EntityPlayer.getDistanceSqToEntity(this) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
    {
        return true;
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.boat";
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    public void travelToDimension(int par1)
    {
        this.dropContentsWhenDead = false;
        super.travelToDimension(par1);
    }
    
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.boatContainerItems.length; ++i)
        {
            if (this.boatContainerItems[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.boatContainerItems[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.boatContainerItems = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.boatContainerItems.length)
            {
                this.boatContainerItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }
    
    public void dropContents()
    {
    	for (int i = 0; i < this.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.getStackInSlot(i);

            if (itemstack != null)
            {
                float f = this.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0)
                {
                    int j = this.rand.nextInt(21) + 10;

                    if (j > itemstack.stackSize)
                    {
                        j = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j;
                    EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, new ItemStack(itemstack.itemID, j, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
                    entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
                    this.worldObj.spawnEntityInWorld(entityitem);
                }
            }
        }
    }
}
