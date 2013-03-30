package com.github.AbrarSyed.SeaCraft.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.github.AbrarSyed.SeaCraft.SeaCraft;
import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatBase;
import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatFurnace;

public abstract class ItemBoatBase extends Item
{
	public ItemBoatBase(int par1)
	{
		super(par1);
		setMaxStackSize(2);
		setHasSubtypes(false);
		canRepair = false;
		setUnlocalizedName(SeaCraft.MODID + ":" + getNameForTexture());
		setCreativeTab(SeaCraft.tab);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{

		// get looking spot.
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
		double d1 = player.prevPosY + (player.posY - player.prevPosY) * f + 1.62D - player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
		Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3 vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
		MovingObjectPosition trace = world.rayTraceBlocks_do(vec3, vec31, true);

		// looking at nothing? RETURN!
		if (trace == null)
			return stack;

		// check spawning area for other entities
		Vec3 vec32 = player.getLook(1f);
		boolean shouldPlace = true;
		List list = world.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand(1d, 1d, 1d));
		int i;

		for (i = 0; i < list.size(); ++i)
		{
			Entity entity = (Entity) list.get(i);

			if (entity.canBeCollidedWith())
			{
				float f10 = entity.getCollisionBorderSize();
				AxisAlignedBB axisalignedbb = entity.boundingBox.expand(f10, f10, f10);

				if (axisalignedbb.isVecInside(vec3))
				{
					shouldPlace = false;
				}
			}
		}

		if (!shouldPlace)
			return stack;

		// actually get spawn points.
		if (trace.typeOfHit == EnumMovingObjectType.TILE)
		{
			i = trace.blockX;
			int j = trace.blockY;
			int k = trace.blockZ;

			if (world.getBlockId(i, j, k) == Block.snow.blockID)
			{
				--j;
			}

			// create boat.
			EntityBoatBase boat = this.getBoat(world, i + 0.5F, j + 1.0F, k + 0.5F);
			boat.rotationYaw = ((MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3) - 1) * 90;

			// ensure no 2 boats in same place
			if (!world.getCollidingBoundingBoxes(boat, boat.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
				return stack;

			// no spawn on client
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(boat);
			}

			// no remove in creative
			if (!player.capabilities.isCreativeMode)
			{
				--stack.stackSize;
			}
		}

		return stack;
	}

	protected abstract String getNameForTexture();
	
	protected abstract EntityBoatBase getBoat(World world, float x, float y, float z);
}
