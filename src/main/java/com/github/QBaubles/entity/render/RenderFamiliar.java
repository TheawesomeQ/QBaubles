package com.github.QBaubles.entity.render;

import com.github.QBaubles.QBaublesMod;
import com.github.QBaubles.entity.EntityFamiliar;
import com.github.QBaubles.entity.model.ModelFamiliar;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFamiliar extends RenderLiving<EntityFamiliar> {
	public static final ResourceLocation FAMILIAR_TEXTURES = new ResourceLocation(QBaublesMod.MODID + ":textures/entity/familiar.png");
	
	public RenderFamiliar(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFamiliar(), 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFamiliar entity) {
		// TODO Auto-generated method stub
		return FAMILIAR_TEXTURES;
	}

}
