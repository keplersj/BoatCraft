package k2b6s9j.BoatCraft.entity.item;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

public abstract class EntityBoatContainer extends EntityCustomBoat implements IInventory {
	
	private ItemStack[] boatContainerItems = new ItemStack[36];

    /**
     * When set to true, the boat will drop all items when setDead() is called. When false (such as when travelling
     * dimensions) it preserves its contents.
     */
    private boolean dropContentsWhenDead = true;
	
	public EntityBoatContainer(World par1World)
    {
        super(par1World);
    }

    public EntityBoatContainer(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
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
    
    public boolean func_130002_c(EntityPlayer player)
    {
    	//Do not mount if the player is shift clicking
    	if (player.isSneaking())
    	{
    		return false;
    	}
        if (!this.worldObj.isRemote)
        {
            player.displayGUIChest(this);
        }
        return true;
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
    
    @Override
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
}
