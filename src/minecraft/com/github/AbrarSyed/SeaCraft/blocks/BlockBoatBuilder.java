package com.github.AbrarSyed.SeaCraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBoatBuilder extends BlockContainer
{
	@SideOnly(Side.CLIENT)
	private Icon	outIcon;

	public BlockBoatBuilder(int id, Material material)
	{
		super(id, material);
	}

	/**
	 * set orientation
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack stack)
	{
		// gets side facing player
		int meta = BlockPistonBase.determineOrientation(world, x, y, z, living);
		
		// reverse direction
		meta = ForgeDirection.getOrientation(meta).getOpposite().ordinal();
		
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register)
	{
		// registers normal with unlocalized name
		super.registerIcons(register);
		// registers the out one.
		this.outIcon = register.registerIcon(this.getUnlocalizedName2() + "_out");
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
        return side == meta ? outIcon : this.blockIcon;
    }

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBoatBuilder();
	}
}
