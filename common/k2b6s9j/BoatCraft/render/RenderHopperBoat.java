package k2b6s9j.BoatCraft.render;

import k2b6s9j.BoatCraft.entity.boat.wood.oak.EntityBoatHopper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

public class RenderHopperBoat extends Render implements IItemRenderer {

	private static final ResourceLocation texture = new ResourceLocation("textures/entity/boat.png");
	private EntityBoatHopper entity;

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
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        float f2 = (float)boat.getTimeSinceHit() - par9;
        float f3 = boat.getDamageTaken() - par9;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f2 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)boat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }
        
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

        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.func_110777_b(boat);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelBoat.render(boat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110803_a(EntityBoatHopper boat)
    {
        return texture;
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
		switch (type) {
			case EQUIPPED_FIRST_PERSON:
				return true;
			case EQUIPPED:
				return true;
			case INVENTORY:
				return false; //For now... Until I find a way.
			case ENTITY:
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object ... var3)
    {
		switch (type) {
			default:
				int j = 8;
		        Block block = Block.hopperBlock;
		        int k = 2;

		        if (block != null)
		        {
		        	GL11.glPushMatrix();
		        	Minecraft.getMinecraft().renderEngine.func_110577_a(TextureMap.field_110575_b);
		            float f8 = 0.75F;
		            GL11.glScalef(f8, f8, f8);
					GL11.glRotatef(90, 0, -1, 0);
		            GL11.glTranslatef(0.0F, (float)j / 8.0F, 0.0F);
		            GL11.glPushMatrix();;
		            this.field_94145_f.renderBlockAsItem(block, 0, k);
		            GL11.glPopMatrix();
		            GL11.glPopMatrix();
		            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		        }
		        GL11.glPushMatrix();
		        Minecraft.getMinecraft().renderEngine.func_110577_a(texture);
		        
				float defaultScale = 1F;
				GL11.glScalef(defaultScale, defaultScale, defaultScale);
				GL11.glRotatef(90, -1, 0, 0);
				GL11.glRotatef(90, 0, 0, 1);
				GL11.glRotatef(180, 0, 1, 0);
				GL11.glRotatef(90, 1, 0, 0);
				
				GL11.glTranslatef(-0.1F, -0.5F, 0F); // Left-Right
				// Forward-Backwards Up-Down
				modelBoat.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);

				GL11.glPopMatrix();
		}
    }
}
