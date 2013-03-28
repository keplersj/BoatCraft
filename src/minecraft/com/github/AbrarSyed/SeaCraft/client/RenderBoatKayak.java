package com.github.AbrarSyed.SeaCraft.client;

import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import com.github.AbrarSyed.SeaCraft.EntityBoatKayak;

public class RenderBoatKayak extends Render
{
	private ModelBoat	model;

	public RenderBoatKayak()
	{
		this.shadowSize = 0.5F;
		model = new ModelBoat();
	}

	/**
	 * The render method used in RenderBoat that renders the boat model.
	 */
	public void render(EntityBoatKayak entity, double x, double y, double z, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);

		this.loadTexture("/terrain.png");
		float f4 = 0.75F;
		GL11.glScalef(f4, f4, f4);
		GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.loadTexture("/item/boat.png");
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.render((EntityBoatKayak) par1Entity, par2, par4, par6, par8, par9);
	}
}
