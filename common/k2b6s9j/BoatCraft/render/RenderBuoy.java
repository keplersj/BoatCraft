package k2b6s9j.BoatCraft.render;

import k2b6s9j.BoatCraft.entity.item.EntityBirchWoodBoat;
import k2b6s9j.BoatCraft.entity.item.EntityBuoy;
import k2b6s9j.BoatCraft.model.ModelBuoy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class RenderBuoy extends Render implements IItemRenderer {
	
	private static final ResourceLocation texture = new ResourceLocation("boatcraft:textures/model/buoy.png");
	private EntityBuoy entity;

    /** instance of ModelBoat for rendering */
    protected ModelBuoy modelBuoy;
	
	public RenderBuoy () {
		this.shadowSize = 0.5F;
        this.modelBuoy = new ModelBuoy();
	}
	
	public void renderBoat(EntityBirchWoodBoat par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
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
        this.modelBuoy.render(par1EntityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }
    
    @Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
    	this.renderBoat((EntityBirchWoodBoat)entity, d0, d1, d2, f, f1);
		
	}

    protected ResourceLocation func_110781_a(EntityBirchWoodBoat par1Entity)
    {
        return texture;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity)
    {
        return this.func_110781_a((EntityBirchWoodBoat)par1Entity);
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
				modelBuoy.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.05F);

				GL11.glPopMatrix();
		}
    }
}
