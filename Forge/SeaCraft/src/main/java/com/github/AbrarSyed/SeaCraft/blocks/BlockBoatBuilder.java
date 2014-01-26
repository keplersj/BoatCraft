package k2b6s9j.BoatCraft.blocks;

import k2b6s9j.BoatCraft.SeaCraft;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBoatBuilder extends BlockContainer
{
	
	public static int ID;
	public static int shiftedID;
	
	@SideOnly(Side.CLIENT)
	private Icon	outIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon	topIcon;
	
	@SideOnly(Side.CLIENT)
	private Icon	sideIcon;

	public BlockBoatBuilder(int id, Material material)
	{
		super(id, material);
		this.setCreativeTab(SeaCraft.tab);
		this.setUnlocalizedName(SeaCraft.MODID + ":boatBuilder");
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register)
	{
		// registers the out one.
		this.outIcon = register.registerIcon(this.getUnlocalizedName2() + "_out");
		
		// registers the out one.
		this.topIcon = register.registerIcon(this.getUnlocalizedName2() + "_top");
		
		// registers the out one.
		this.sideIcon = register.registerIcon(this.getUnlocalizedName2() + "_side");
	}
	
    @SideOnly(Side.CLIENT)
    public Icon getBlockTextureFromSideAndMetadata(int side, int meta)
    {
    	if (side == 1)
    		return topIcon;
    	else if (side == meta)
    		return outIcon;
    	else
    		return sideIcon;
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
		
		if (meta == 1)
			meta = 0;
		
		world.setBlockMetadataWithNotify(x, y, z, meta, 3);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int wut, float clickX, float clickY, float clockZ)
	{
		player.openGui(SeaCraft.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityBoatBuilder();
	}
}
