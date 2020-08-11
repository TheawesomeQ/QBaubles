package com.github.QBaubles.entity.render;

import com.github.QBaubles.QBaublesMod;
import com.github.QBaubles.entity.EntityStickbug;
import com.github.QBaubles.entity.model.ModelStickbug;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStickbug extends RenderLiving<EntityStickbug> {
	public static final ResourceLocation STICKBUG_TEXTURES = new ResourceLocation(QBaublesMod.MODID +":textures/entity/stickbug.png");
	public RenderStickbug(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelStickbug(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStickbug entity) {
		// TODO Auto-generated method stub
		return STICKBUG_TEXTURES;
	}

	@Override
	protected void applyRotations(EntityStickbug entityLiving, float p_77043_2_, float rotationYaw,
			float partialTicks) {
		// TODO Auto-generated method stub
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
}
