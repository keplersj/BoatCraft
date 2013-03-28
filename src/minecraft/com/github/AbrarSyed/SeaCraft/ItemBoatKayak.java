package com.github.AbrarSyed.SeaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemBoatKayak extends Item
{
	public ItemBoatKayak(int par1)
	{
		super(par1);
		this.setMaxStackSize(2);
		this.setHasSubtypes(false);
		this.canRepair = false;
		this.setUnlocalizedName(SeaCraft.MODID+":kayak");
		this.setCreativeTab(SeaCraft.tab);
	}
	
    /**
     * This is called when the item is used, before the block is activated.
     * @param stack The Item Stack
     * @param player The Player that used the item
     * @param world The Current World
     * @param x Target X Position
     * @param y Target Y Position
     * @param z Target Z Position
     * @param side The side of the target hit
     * @return Return true to prevent any further processing.
     */
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {	
    	MovingObjectPosition trace = SeaCraft.getPlayerLookingSpot(player, true);
    	
    	if (trace == null)
    		return false;
    	
    	double spawnX, spawnY, spawnZ;
    	
    	if(trace.typeOfHit.equals(EnumMovingObjectType.ENTITY))
    	{
    		if (trace.entityHit instanceof EntityBoatKayak)
    			return false;
    		
    		spawnX = trace.entityHit.posX;
    		spawnY = trace.entityHit.posY+trace.entityHit.height+1;
    		spawnZ = trace.entityHit.posZ;
    	}
    	else
    	{
    		spawnX = trace.blockX;
    		spawnY = trace.blockY+1;
    		spawnZ = trace.blockZ;
    	}
    	
    	EntityBoatKayak entity = new EntityBoatKayak(world, spawnX, spawnY, spawnZ);
    	world.spawnEntityInWorld(entity);
    	
    	if (!player.capabilities.isCreativeMode)
    		stack.stackSize--;
    	
        return true;
    }
}
