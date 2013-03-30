package com.github.AbrarSyed.SeaCraft.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import com.github.AbrarSyed.SeaCraft.Boats.EntityBoatFurnace;
import com.github.AbrarSyed.SeaCraft.client.models.ModelBoatKayak;

public class RenderBoatFurnace extends Render
{
	private ModelBoatKayak	model;

	public RenderBoatFurnace()
	{
		shadowSize = 0.5F;
		model = new ModelBoatKayak();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(EntityBoatFurnace entity, double x, double y, double z, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);

		// rotate arround y...
		GL11.glRotatef(180 - par8, 0.0F, 1.0F, 0.0F);

		float f2 = entity.getTimeSinceHit() - par9;
		float f3 = entity.getDamageTaken() - par9;

		if (f3 < 0.0F)
		{
			f3 = 0.0F;
		}

		if (f2 > 0.0F)
		{
			// render roll
			GL11.glRotatef(MathHelper.sin(f2) * f2 * f3 / 10.0F, 1.0F, 0.0F, 0.0F);
		}

		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		//loadTexture("/item/boat.png");
		GL11.glScalef(-1.0F, -1.0F, 1.0F);

		// scaling
		//GL11.glScalef(1.5F, 1.0F, .8F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glColor3f(0.0F, 0.34F, 0.73F);

		model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        
		GL11.glPopMatrix();
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		render((EntityBoatFurnace) par1Entity, par2, par4, par6, par8, par9);
	}
}
