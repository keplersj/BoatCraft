package k2b6s9j.BoatCraft.render;

import k2b6s9j.BoatCraft.entity.item.EntityOakWoodBoat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

public class RenderOakWoodBoat extends Render implements IItemRenderer {

	private static final ResourceLocation texture = new ResourceLocation("textures/entity/boat.png");

    /** instance of ModelBoat for rendering */
    protected ModelBase modelBoat;
	
	public RenderOakWoodBoat () {
		this.shadowSize = 0.5F;
        this.modelBoat = new ModelBoat();
	}
	
	public void renderBoat(EntityOakWoodBoat par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        float f2 = (float)par1EntityBoat.getTimeSinceHit() - par9;
        float f3 = par1EntityBoat.getDamageTaken() - par9;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f2 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float)par1EntityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        float f4 = 0.75F;
        GL11.glScalef(f4, f4, f4);
        GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
        this.func_110777_b(par1EntityBoat);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelBoat.render(par1EntityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
    
    @Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
    	this.renderBoat((EntityOakWoodBoat)entity, d0, d1, d2, f, f1);
		
	}

    protected ResourceLocation func_110781_a(EntityOakWoodBoat par1Entity)
    {
        return texture;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110781_a((EntityOakWoodBoat)par1Entity);
    }

    @Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		renderBoat(null, 0.5F, 0.5F, 0.5F, 1, 1);
	}

}