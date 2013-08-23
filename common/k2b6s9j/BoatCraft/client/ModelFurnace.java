package k2b6s9j.BoatCraft.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

import com.overminddl1.minecraft.libs.NMT.NMTModelRenderer;

public class ModelFurnace extends ModelBase
{
	NMTModelRenderer	render;

	public ModelFurnace()
	{
		render = new NMTModelRenderer(this);
		render.addBox(0f, 0f, 0f, 16, 16, 16);
		
		render.mirror = false;
	}
	
	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		render.render(par7);
	}

}
