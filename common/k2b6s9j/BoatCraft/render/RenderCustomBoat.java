package k2b6s9j.BoatCraft.render;

import java.util.logging.Level;

import k2b6s9j.BoatCraft.entity.item.EntityCustomBoat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.VillagerRegistry;

public class RenderCustomBoat extends Render implements IItemRenderer {

	private final static ResourceLocation oak = new ResourceLocation("textures/entity/boat.png");
	private final static String modBase = "boatcraft:textures/boats/";
	private final static ResourceLocation spruce = new ResourceLocation(modBase + "spruce.png");
	private final static ResourceLocation birch = new ResourceLocation(modBase + "birch.png");
	private final static ResourceLocation jungle = new ResourceLocation(modBase + "jungle.png");

    /** instance of ModelBoat for rendering */
    protected ModelBase modelBoat;
	
	public RenderCustomBoat() {
		this.shadowSize = 0.5F;
        this.modelBoat = new ModelBoat();
	}
	
	public void renderBoat(EntityCustomBoat par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
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
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
    	this.renderBoat((EntityCustomBoat)entity, d0, d1, d2, f, f1);	
	}

    protected ResourceLocation func_110781_a(EntityCustomBoat entity)
    {
    	switch (entity.getType())
        {
            case 0:
                return oak;
            case 1:
                return spruce;
            case 2:
                return birch;
            case 3:
                return jungle;
            default:
                return oak;
        }
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110781_a((EntityCustomBoat)par1Entity);
    }

    @Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
	}

}
