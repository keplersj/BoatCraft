package k2b6s9j.BoatCraft.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBuoy extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Shape1;
    ModelRenderer TopBase;
    ModelRenderer CornorPole1;
    ModelRenderer CornorPole2;
    ModelRenderer CornorPole3;
    ModelRenderer CornorPole4;
  
  public ModelBuoy()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 16, 6, 16);
      Base.setRotationPoint(-8F, 18F, -8F);
      Base.setTextureSize(64, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 22);
      Shape1.addBox(0F, 0F, 0F, 2, 20, 2);
      Shape1.setRotationPoint(-1F, -2F, -1F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      TopBase = new ModelRenderer(this, 8, 22);
      TopBase.addBox(0F, 0F, 0F, 10, 6, 10);
      TopBase.setRotationPoint(-5F, -8F, -5F);
      TopBase.setTextureSize(64, 64);
      TopBase.mirror = true;
      setRotation(TopBase, 0F, 0F, 0F);
      CornorPole1 = new ModelRenderer(this, 0, 22);
      CornorPole1.addBox(-1F, 0F, 0F, 2, 21, 2);
      CornorPole1.setRotationPoint(4F, -2F, -5F);
      CornorPole1.setTextureSize(64, 64);
      CornorPole1.mirror = true;
      setRotation(CornorPole2, -0.0698132F, 0F, -0.0698132F);
      CornorPole2 = new ModelRenderer(this, 0, 22);
      CornorPole2.addBox(0F, 0F, 0F, 2, 21, 2);
      CornorPole2.setRotationPoint(3F, -2F, 3F);
      CornorPole2.setTextureSize(64, 64);
      CornorPole2.mirror = true;
      setRotation(CornorPole1, 0.0872665F, 0F, -0.0872665F);
      CornorPole3 = new ModelRenderer(this, 0, 22);
      CornorPole3.addBox(0F, 0F, 0F, 2, 21, 2);
      CornorPole3.setRotationPoint(-5F, -2F, 3F);
      CornorPole3.setTextureSize(64, 64);
      CornorPole3.mirror = true;
      setRotation(CornorPole1, 0.0872665F, 0F, 0.0872665F);
      CornorPole4 = new ModelRenderer(this, 0, 22);
      CornorPole4.addBox(0F, 0F, 0F, 2, 21, 2);
      CornorPole4.setRotationPoint(-5F, -3F, -5F);
      CornorPole4.setTextureSize(64, 64);
      CornorPole4.mirror = true;
      setRotation(CornorPole1, -0.0872665F, 0F, 0.0872665F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Base.render(f5);
    Shape1.render(f5);
    TopBase.render(f5);
    CornorPole2.render(f5);
    CornorPole1.render(f5);
    CornorPole1.render(f5);
    CornorPole1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
