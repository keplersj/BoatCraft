package com.github.AbrarSyed.SeaCraft.client.models;

import com.github.AbrarSyed.SeaCraft.SeaCraft;
import com.overminddl1.minecraft.libs.NMT.NMTModelBase;
import com.overminddl1.minecraft.libs.NMT.NMTModelRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelBoatKayak extends ModelBase
{
    public ModelRenderer[] boatSides = new ModelRenderer[5];
    private NMTModelRenderer render;

    public ModelBoatKayak()
    {
    	render = new NMTModelRenderer(this, "Kayak");
    	render.addModelOBJ("all", "mods/"+SeaCraft.MODID+"/models/Kayak-Canoe.obj");
    	
    	/*
        this.boatSides[0] = new ModelRenderer(this, 0, 8);
        this.boatSides[1] = new ModelRenderer(this, 0, 0);
        this.boatSides[2] = new ModelRenderer(this, 0, 0);
        this.boatSides[3] = new ModelRenderer(this, 0, 0);
        this.boatSides[4] = new ModelRenderer(this, 0, 0);
        
        int b0 = 24;
        int b1 = 6;
        int b2 = 20;
        int b3 = 4;
        
        //bottom
        this.boatSides[0].addBox((float)(-b0 / 2), (float)(-b2 / 2 + 2), -3.0F, b0, b2 - 4, 4, 0.0F);
        this.boatSides[0].setRotationPoint(0.0F, (float)b3, 0.0F);
        
        // front
        this.boatSides[1].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[1].setRotationPoint((float)(-b0 / 2 + 1), (float)b3, 0.0F);
        
        // right
        this.boatSides[2].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[2].setRotationPoint((float)(b0 / 2 - 1), (float)b3, 0.0F);
        
        // left
        this.boatSides[3].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[3].setRotationPoint(0.0F, (float)b3, (float)(-b2 / 2 + 1));
        
        // back
        this.boatSides[4].addBox((float)(-b0 / 2 + 2), (float)(-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
        this.boatSides[4].setRotationPoint(0.0F, (float)b3, (float)(b2 / 2 - 1));
        
        this.boatSides[0].rotateAngleX = ((float)Math.PI / 2F);
        this.boatSides[1].rotateAngleY = ((float)Math.PI * 3F / 2F);
        this.boatSides[2].rotateAngleY = ((float)Math.PI / 2F);
        this.boatSides[3].rotateAngleY = (float)Math.PI;
        */
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
    	render.render(par7);
    	/*
        for (int i = 0; i < 5; ++i)
        {
            this.boatSides[i].render(par7);
        }
        */
    }
}
