package com.github.AbrarSyed.SeaCraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import com.overminddl1.minecraft.libs.NMT.NMTModelRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBoatKayak extends ModelBase
{
	public ModelRenderer[]		boatSides	= new ModelRenderer[5];
	private NMTModelRenderer	render;

	public ModelBoatKayak()
	{
		/*
		 * render = new NMTModelRenderer(this, "Kayak");
		 * render.setTextureOffset(0, 0);
		 * this.setTextureOffset("Kayak.all", 0, 0);
		 * File file = new File("mods/"+SeaCraft.MODID+"/models/Kayak-Canoe.obj");
		 * try
		 * {
		 * render.addModelOBJ("all", file.toURI().toURL().toString());
		 * }
		 * catch (MalformedURLException e)
		 * {
		 * e.printStackTrace();
		 * }
		 */

		boatSides[0] = new ModelRenderer(this, 0, 8);
		boatSides[1] = new ModelRenderer(this, 0, 0);
		boatSides[2] = new ModelRenderer(this, 0, 0);
		boatSides[3] = new ModelRenderer(this, 0, 0);
		boatSides[4] = new ModelRenderer(this, 0, 0);

		int b0 = 24;
		int b1 = 6;
		int b2 = 20;
		int b3 = 4;

		boatSides[0].addBox(-b0 / 2, -b2 / 2 + 2, -3.0F, b0, b2 - 4, 4, 0.0F);
		boatSides[0].setRotationPoint(0.0F, b3, 0.0F);

		// front
		boatSides[1].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
		boatSides[1].setRotationPoint(-b0 / 2 + 1, b3, 0.0F);

		// right
		boatSides[2].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
		boatSides[2].setRotationPoint(b0 / 2 - 1, b3, 0.0F);

		// left
		boatSides[3].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
		boatSides[3].setRotationPoint(0.0F, b3, -b2 / 2 + 1);

		// back
		boatSides[4].addBox(-b0 / 2 + 2, -b1 - 1, -1.0F, b0 - 4, b1, 2, 0.0F);
		boatSides[4].setRotationPoint(0.0F, b3, b2 / 2 - 1);

		boatSides[0].rotateAngleX = (float) Math.PI / 2F;
		boatSides[1].rotateAngleY = (float) Math.PI * 3F / 2F;
		boatSides[2].rotateAngleY = (float) Math.PI / 2F;
		boatSides[3].rotateAngleY = (float) Math.PI;

	}

	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		for (int i = 0; i < 5; ++i)
		{
			boatSides[i].render(par7);
		}
	}
}
