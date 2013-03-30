package com.github.AbrarSyed.SeaCraft.client;

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
		boatSides[0] = new ModelRenderer(this, 0, 0);
		boatSides[1] = new ModelRenderer(this, 0, 0);
		boatSides[2] = new ModelRenderer(this, 0, 0);
		boatSides[3] = new ModelRenderer(this, 0, 0);
		boatSides[4] = new ModelRenderer(this, 0, 0);

		int length = 40;
		int height = 6;
		int thicknessFront = 12;
		int thicknessBack = 10;
		int thicknessSide = 1;
		int width = 20;
		int rotation = 4;

		boatSides[0].addBox(-length / 2, -width / 2 + 2, -3.0F, length, width - 4, 4, 0.0F);
		boatSides[0].setRotationPoint(0.0F, rotation, 0.0F);

		// front
		boatSides[1].addBox(-width / 2, -height - 1, -thicknessFront, width, height, thicknessFront + 1, 0.0F);
		boatSides[1].setRotationPoint(-length / 2 + 1, rotation, 0.0F);

		// back
		boatSides[2].addBox(-width / 2, -height - 1, -thicknessBack, width, height, thicknessBack + 1, 0.0F);
		boatSides[2].setRotationPoint(length / 2 - 1, rotation, 0.0F);

		// left
		boatSides[3].addBox(-length / 2 + 2, -height - 1, -thicknessSide, length - 4, height, thicknessSide + 1, 0.0F);
		boatSides[3].setRotationPoint(0.0F, rotation, -width / 2 + 1);

		// back
		boatSides[4].addBox(-length / 2 + 2, -height - 1, -thicknessSide, length - 4, height, thicknessSide + 1, 0.0F);
		boatSides[4].setRotationPoint(0.0F, rotation, width / 2 - 1);

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
