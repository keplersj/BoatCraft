package k2b6s9j.BoatCraft.render;

import k2b6s9j.BoatCraft.entity.item.EntityBoatHopper;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderHopperBoat extends Render implements IItemRenderer {

	private static final ResourceLocation field_110804_g = new ResourceLocation("textures/entity/boat.png");

    /** instance of ModelBoat for rendering */
    protected ModelBase modelBoat = new ModelBoat();
    protected final RenderBlocks field_94145_f;

    public RenderHopperBoat()
    {
        this.shadowSize = 0.5F;
        this.field_94145_f = new RenderBlocks();
    }

    /**
     * Renders the Boat.
     */
    public void renderTheBoat(EntityBoatHopper boat, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        this.func_110777_b(boat);
        long i = (long)boat.entityId * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f2 = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f3 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f4 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GL11.glTranslatef(f2, f3, f4);
        double d3 = boat.lastTickPosX + (boat.posX - boat.lastTickPosX) * (double)par9;
        double d4 = boat.lastTickPosY + (boat.posY - boat.lastTickPosY) * (double)par9;
        double d5 = boat.lastTickPosZ + (boat.posZ - boat.lastTickPosZ) * (double)par9;
        double d6 = 0.30000001192092896D;
        float f5 = boat.prevRotationPitch + (boat.rotationPitch - boat.prevRotationPitch) * par9;

        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);

        int j = boat.getDisplayTileOffset();
        Block block = boat.getDisplayTile();
        int k = boat.getDisplayTileData();

        if (block != null)
        {
            GL11.glPushMatrix();
            this.func_110776_a(TextureMap.field_110575_b);
            float f8 = 0.75F;
            GL11.glScalef(f8, f8, f8);
            GL11.glTranslatef(0.0F, (float)j / 16.0F, 0.0F);
            this.renderBlockInBoat(boat, par9, block, k);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_110777_b(boat);
        }

        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelBoat.render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110803_a(EntityBoatHopper boat)
    {
        return field_110804_g;
    }

    /**
     * Renders the block that is inside the boat.
     */
    protected void renderBlockInBoat(EntityBoatHopper boat, float par2, Block par3Block, int par4)
    {
        float f1 = boat.getBrightness(par2);
        GL11.glPushMatrix();
        this.field_94145_f.renderBlockAsItem(par3Block, par4, f1);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110803_a((EntityBoatHopper)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderTheBoat((EntityBoatHopper)par1Entity, par2, par4, par6, par8, par9);
    }

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		// TODO Auto-generated method stub
		
	}
}
